package fixedSizeWindow;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class MaxOfAllSubArrayOfSizeK {

	public static void main(String[] args) {
		int arr[]=new int[] {1,3,-1,-3,5,3,6,7};
		int k=3;
		int []res=solve(arr,k);
		for(int i=0;i<res.length;i++) {
			System.out.println(res[i]);
		}
	}

	private static int[] solve(int[] arr, int k) {
		int ans[]=new int[arr.length-k+1];
		Deque<Integer> dq=new LinkedList<>();
		int i=0;int j=0;
		while(j<arr.length) {
			//basic calculation
			if(dq.size()==0) {
				dq.addLast(arr[j]);
			}else {
				while(dq.size()>0 && dq.peekLast()<arr[j]) {
					dq.pollLast();//just pop
				}
				dq.addLast(arr[j]);
			}
			
			if(j-i+1<k) {
				j++;
			}else if(j-i+1==k) {
				//ans calculation
				ans[i]=dq.peekFirst();
				//changes in i before slide
				if(arr[i]==dq.peekFirst()) {
					dq.pollFirst();
				}
				i++;
				j++;
			}
		}
		return ans;
	}
	
	
	/*		*/
	/* public static int findMaxSumSubArrayUsing For(int k, int[] arr) {
    int maxSum = 0, windowSum = 0;
    int windowStart = 0;

    for (int windowEnd=0; windowEnd < arr.length; windowEnd++) {
      windowSum += arr[windowEnd];
      if(windowEnd >= k-1) {
        maxSum = Math.max(maxSum, windowSum);
        windowSum -= arr[windowStart];
        windowStart++;
      }
    }
    return maxSum;
  }*/
	/* gfg
	 * class Solution{
    static long maximumSumSubarray(int k, ArrayList<Integer> arr,int n){
        // code here
        int start=0;
		int end=0;
		long maxSum=0;
		long currMax=0;
		while(end<n) {
			currMax+=arr.get(end);
			if(end-start+1<k) {
				end++;
			}else if(end-start+1==k) {
				maxSum=Math.max(maxSum, currMax);
				currMax-=arr.get(start);
				start++;
				end++;
			}

		}
		return maxSum;
    }
}*/

}
