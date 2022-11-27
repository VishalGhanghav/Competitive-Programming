package fixedSizeWindow;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class LongestSubArrayOfSumK {

	public static void main(String[] args) {
		int arr[]=new int[] {4,1,1,1 ,2, 3, 5};
		int k=5;
		int res=solve(arr,k);
		System.out.println(res);
	}

	private static int solve(int[] arr, int k) {
		int i=0,j=0,ans=0;
		int sum=0;
		while(j<arr.length) {
			sum+=arr[j];
			if(sum>k) {
				while(sum>k) {
					sum-=arr[i];
					i++;
				}
				if(sum==k) {
					ans=Math.max(ans,j-i+1 );//Maximise the window size
				}
				j++;
			}else if(sum==k) {
				ans=Math.max(ans,j-i+1 );
				j++;
			}else {
				j++;
			}
		}
		return ans;
	}

}	
/*For negative numbers
	private static void solveWithNegativeIntegers(int[] array, int k) {
	HashMap<Integer,Integer> map = new HashMap<>();

        int sum=0;

        map.put(0,-1);

        int max_len=0;

        for(int i=0;i<N;i++){
            sum += A[i];



            if(!map.containsKey(sum)){
                map.put(sum,i);
            }

            int key_to_find = sum - K;

            if(map.containsKey(key_to_find)){
                max_len  = Math.max(max_len , i- map.get(key_to_find));
            }
        }

        return max_len;

}*/