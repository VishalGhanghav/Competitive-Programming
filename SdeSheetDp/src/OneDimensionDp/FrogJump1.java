package SdeSheetDp.src.OneDimensionDp;

import java.util.Arrays;

/*
 
 Given a number of stairs and a frog, the frog wants to climb from the 0th stair to the
  (N-1)th stair. At a time the frog can climb either one or two steps. 
  A height[N] array is also given. Whenever the frog jumps from a stair i to stair j, 
  the energy consumed in the jump is abs(height[i]- height[j]), where abs() means the absolute 
  difference. 
 We need to return the minimum energy that can be used by the frog to jump from stair 0 to
  stair N-1.
  Example:
Input:
n = 4
height = {10 20 30 10}
Output:
20
Explanation:
Geek jump from 1st to 2nd stair(|20-10| = 10 energy lost).
Then a jump from the 2nd to the last stair(|10-20| = 10 energy lost).
so, total energy lost is 20 which is the minimum.
 */
public class FrogJump1 {
    public static void main(String args[]) {
        int height[] = {30, 10, 60, 10, 60, 50};
        int n = height.length;
        //only index changing so 1d dp
        int dp[] = new int[n];
        Arrays.fill(dp, -1);
        System.out.println(solve(n - 1, height, dp));
        //op:40
    }

    private static int solve(int n, int[] height, int[] dp) {
        if (n == 0) {
            return 0;
        }
        int jumpTwo = 0;
        if (dp[n] != -1) {
            return dp[n];
        }
        int jumpOne = solve(n - 1, height, dp) + Math.abs(height[n] - height[n - 1]);
        if (n > 1) {
            jumpTwo = solve(n - 2, height, dp) + Math.abs(height[n] - height[n - 2]);
        }
        return dp[n] = Math.min(jumpOne, jumpTwo);

    }

	/* Since we are just checking
    public int solve(int[] arr,int n,int[] dp){
        dp[0] = 0;
        for(int i=1;i<=n;i++) {
            int first = dp[i-1] + Math.abs(arr[i] - arr[i-1]);
            int second = Integer.MAX_VALUE;
            if(i>1) {
                second = dp[i-2] +  Math.abs(arr[i] - arr[i-2]);
            }
            dp[i] = Math.min(first,second);
        }
        return dp[n];
    }*/

	/*
	public int minCost(int height[]){
        //code here

	int n=height.length;
	int prev=0;
	int prev2=0;
        for(int i=1;i<n;i++){

		int jumpTwo = Integer.MAX_VALUE;
		int jumpOne= prev + Math.abs(height[i]-height[i-1]);
		if(i>1)
			jumpTwo = prev2 + Math.abs(height[i]-height[i-2]);

		int cur_i=Math.min(jumpOne, jumpTwo);
		prev2=prev;
		prev=cur_i;

	}
        return prev;
}

	 */
}
