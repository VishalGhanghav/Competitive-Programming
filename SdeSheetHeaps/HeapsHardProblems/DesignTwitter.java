package SdeSheetHeaps.HeapsHardProblems;

import java.util.*;

class DesignTwitter {

    //Map to store tweets per user
    Map<Integer, List<Tweet>> userTweets;

    //Map to store followers:userId->people they follow
    Map<Integer, Set<Integer>> following;

    //Timestamp counter used for news feed and Tweet
    static int timestamp;

    //Tweets need timestamp to get most recent 10
    class Tweet {
        int tweetId;
        int time;

        Tweet(int tweetId, int time) {
            this.tweetId = tweetId;
            this.time = time;
        }
    }

    public DesignTwitter() {
        userTweets = new HashMap<>();
        following = new HashMap<>();
    }

    // TC: O(1), SC: O(1)
    public void postTweet(int userId, int tweetId) {
        //Put an entry in userTweets map
        userTweets.putIfAbsent(userId, new ArrayList<>());
        userTweets.get(userId).add(new Tweet(tweetId, timestamp++));
    }

    // TC: O(N log N), SC: O(N)
    // N is total number of tweets from user + followees
    public List<Integer> getNewsFeed(int userId) {
        //For the user id get all poeple he follows
        //FOr each person get all tweets and put in maxHeap
        //From maxHeap get recent 10
        List<Integer> newsFeed = new ArrayList<>();
        PriorityQueue<Tweet> maxHeap = new PriorityQueue<>(
                (a, b) -> b.time - a.time
        );
        //In case no tweets present add emptyList
        Set<Integer> people = following.getOrDefault(userId, new HashSet<>());
        //For current user also we check tweets
        people.add(userId);
        for (int person : people) {
            List<Tweet> tweets = userTweets.getOrDefault(person, new ArrayList<>());
            for (Tweet tweet : tweets) {
                maxHeap.add(tweet);
            }
        }
        int count = 0;
        while (!maxHeap.isEmpty() && count < 10) {
            newsFeed.add(maxHeap.poll().tweetId);
            count++;
        }
        return newsFeed;
    }

    // TC: O(1), SC: O(1)
    public void follow(int followerId, int followeeId) {
        //User with followerId started following followeeId
        //In following map add one new followee
        following.putIfAbsent(followerId, new HashSet<>());
        following.get(followerId).add(followeeId);
    }

    // TC: O(1), SC: O(1)
    public void unfollow(int followerId, int followeeId) {
        //If followee present in map remove him
        if (following.containsKey(followerId)) {
            following.get(followerId).remove(followeeId);
        }
    }

    public static void main(String[] args) {
        DesignTwitter obj = new DesignTwitter();

        obj.postTweet(1, 5); // User 1 posts tweetId 5
        obj.postTweet(1, 3); // User 1 posts tweetId 3

        System.out.println("NewsFeed for 1: " + obj.getNewsFeed(1)); // Should print [3, 5]

        obj.follow(1, 2); // User 1 follows user 2
        obj.postTweet(2, 6); // User 2 posts tweetId 6
        obj.postTweet(2, 7); // User 2 posts tweetId 7

        System.out.println("NewsFeed for 1 after following 2: " + obj.getNewsFeed(1)); // Should print [7,6,3,5]

        obj.unfollow(1, 2); // User 1 unfollows user 2

        System.out.println("NewsFeed for 1 after unfollowing 2: " + obj.getNewsFeed(1)); // Should print [3,5]
    }
}
