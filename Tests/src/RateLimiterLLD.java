package Tests.src;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimiterLLD {

    // ==========================================
    // Approach 1: Fixed Window Rate Limiter
    // ==========================================
    static class FixedWindowRateLimiter {

        // Maximum requests allowed per window
        private final int maxRequests;
        // Size of the window in milliseconds (e.g., 1000ms)
        private final long windowSizeInMillis;

        // Map to store state for each user. Thread-safe map.
        private final ConcurrentHashMap<String, UserCounter> userCounters = new ConcurrentHashMap<>();

        // Constructor to set the rules
        public FixedWindowRateLimiter(int maxRequests, long windowSizeInMillis) {
            this.maxRequests = maxRequests;
            this.windowSizeInMillis = windowSizeInMillis;
        }

        // Inner class to hold the state for a single user
        // We use this object to lock on for thread safety
        private static class UserCounter {
            int count;          // Current request count
            long startTime;     // When the current window started

            UserCounter() {
                this.count = 0;
                this.startTime = System.currentTimeMillis();
            }
        }

        /**
         * TC: O(1) - Constant time operations.
         * SC: O(U) - Stores 1 small object per user.
         */
        public boolean allowRequest(String userId) {
            // Get the user's counter object, or create a new one if not present
            UserCounter userObj = userCounters.computeIfAbsent(userId, k -> new UserCounter());

            // Lock ONLY this specific user object to ensure thread safety
            synchronized (userObj) {
                // Get the current system time
                long now = System.currentTimeMillis();

                // Check if the current window has expired
                if (now - userObj.startTime >= windowSizeInMillis) {
                    // Reset the window start time to now
                    userObj.startTime = now;
                    // Reset the request count to 0
                    userObj.count = 0;
                }

                // Check if the count is within the limit
                if (userObj.count < maxRequests) {
                    // Increment the count
                    userObj.count++;
                    // Return true (Allowed)
                    return true;
                } else {
                    // Limit reached, return false (Blocked)
                    return false;
                }
            }
        }
    }

    // ==========================================
    // Approach 2: Token Bucket (Optimal - Industry Standard)
    // ==========================================
    // Explanation:
    // - Imagine a bucket that holds tokens.
    // - Tokens are added lazily based on how much time passed since the last request.
    // - Each request consumes 1 token.
    // - Pros: Memory efficient and handles "bursts" of traffic well.
    // ==========================================
    static class TokenBucketRateLimiter {

        // Maximum tokens the bucket can hold (Burst Capacity)
        private final int capacity;
        // Refill rate: tokens per second
        private final double tokensPerSecond;

        // Map to store bucket state for each user
        private final ConcurrentHashMap<String, UserBucket> userBuckets = new ConcurrentHashMap<>();

        public TokenBucketRateLimiter(int capacity, int tokensPerSecond) {
            this.capacity = capacity;
            this.tokensPerSecond = tokensPerSecond;
        }

        // Inner class to hold the state for a single user
        private static class UserBucket {
            double tokens;          // Current tokens available
            long lastRefillTime;    // Timestamp of the last refill

            UserBucket(int capacity) {
                this.tokens = capacity; // Start with full capacity
                this.lastRefillTime = System.currentTimeMillis();
            }
        }

        /**
         * TC: O(1) - Math calculations are constant.
         * SC: O(U) - Stores 1 small object per user.
         */
        public boolean allowRequest(String userId) {
            // Get user's bucket or create new
            UserBucket userObj = userBuckets.computeIfAbsent(userId, k -> new UserBucket(capacity));

            // Lock ONLY this user's bucket
            synchronized (userObj) {
                // Get current time
                long now = System.currentTimeMillis();

                // Calculate time passed since last refill
                long timePassed = now - userObj.lastRefillTime;

                // Calculate tokens to add: (seconds passed) * (rate)
                double tokensToAdd = (timePassed / 1000.0) * tokensPerSecond;

                // If we have tokens to add, update the bucket
                if (tokensToAdd > 0) {
                    // Add tokens but clamp at max capacity
                    userObj.tokens = Math.min(capacity, userObj.tokens + tokensToAdd);
                    // Update last refill time to now
                    userObj.lastRefillTime = now;
                }

                // Check if we have at least 1 token to spend
                if (userObj.tokens >= 1) {
                    // Deduct 1 token
                    userObj.tokens -= 1;
                    // Return true (Allowed)
                    return true;
                } else {
                    // Not enough tokens, return false (Blocked)
                    return false;
                }
            }
        }
    }

    // ==========================================
    // Approach 3: Sliding Window Log (Optimal - Precise)
    // ==========================================
    // Explanation:
    // - We store the timestamp of EVERY request in a list (Queue).
    // - When a new request comes, we remove timestamps that are too old (expired).
    // - If the remaining list size is smaller than the limit, we allow it.
    // - Pros: 100% Accurate (no edge spikes).
    // - Cons: Uses more memory (stores many timestamps per user).
    // ==========================================
    static class SlidingWindowLogRateLimiter {

        // Max requests allowed in the window
        private final int maxRequests;
        // Window size in ms
        private final long windowSizeInMillis;

        // Map to store the log (Queue) for each user
        private final ConcurrentHashMap<String, UserLog> userLogs = new ConcurrentHashMap<>();

        public SlidingWindowLogRateLimiter(int maxRequests, long windowSizeInMillis) {
            this.maxRequests = maxRequests;
            this.windowSizeInMillis = windowSizeInMillis;
        }

        // Inner class to hold the queue of timestamps
        private static class UserLog {
            Queue<Long> logs = new LinkedList<>();
        }

        /**
         * TC: O(N) - Worst case we remove N expired timestamps.
         * SC: O(U * N) - Stores N timestamps per user.
         */
        public boolean allowRequest(String userId) {
            // Get user's log object or create new
            UserLog userObj = userLogs.computeIfAbsent(userId, k -> new UserLog());

            // Lock ONLY this user's log object
            synchronized (userObj) {
                // Get current time
                long now = System.currentTimeMillis();

                // Remove timestamps that are older than the window
                // (current time - timestamp > window size)
                while (!userObj.logs.isEmpty() && (now - userObj.logs.peek() > windowSizeInMillis)) {
                    userObj.logs.poll(); // Remove old timestamp
                }

                // Check if the current size is less than max allowed
                if (userObj.logs.size() < maxRequests) {
                    // Add current timestamp to log
                    userObj.logs.offer(now);
                    // Return true (Allowed)
                    return true;
                } else {
                    // Limit reached, return false (Blocked)
                    return false;
                }
            }
        }
    }

    // ==========================================
    // Main Method to Run All Approaches
    // ==========================================
    public static void main(String[] args) throws InterruptedException {

        // --- Test 1: Fixed Window ---
        System.out.println("=== 1. Fixed Window (2 req/sec) ===");
        // Allow 2 requests per 1000ms
        FixedWindowRateLimiter fixed = new FixedWindowRateLimiter(2, 1000);

        // Loop 5 times
        for (int i = 1; i <= 5; i++) {
            boolean allowed = fixed.allowRequest("User1");
            System.out.println("Req " + i + ": " + (allowed ? "Allowed" : "Blocked"));
            Thread.sleep(300); // Wait 300ms
        }


        // --- Test 2: Token Bucket ---
        System.out.println("\n=== 2. Token Bucket (Cap 3, Refill 1/sec) ===");
        // Burst 3, Refill 1 per sec
        TokenBucketRateLimiter bucket = new TokenBucketRateLimiter(3, 1);

        // Loop 5 times
        for (int i = 1; i <= 5; i++) {
            boolean allowed = bucket.allowRequest("User2");
            System.out.println("Req " + i + ": " + (allowed ? "Allowed" : "Blocked"));
            Thread.sleep(300); // Wait 300ms
        }


        // --- Test 3: Sliding Window Log ---
        System.out.println("\n=== 3. Sliding Window Log (2 req/sec) ===");
        // Allow 2 requests per 1000ms
        SlidingWindowLogRateLimiter sliding = new SlidingWindowLogRateLimiter(2, 1000);

        // Loop 5 times
        for (int i = 1; i <= 5; i++) {
            boolean allowed = sliding.allowRequest("User3");
            System.out.println("Req " + i + ": " + (allowed ? "Allowed" : "Blocked"));
            Thread.sleep(300); // Wait 300ms
        }
    }
}