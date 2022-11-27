import java.util.Collections;
import java.util.PriorityQueue;

public class KthLargestElementInAnArray {
	public static void main(String[] args) {
		int arr[]=new int[] {3,2,3,1,2,4,5,5,6};
		int k=4;
		int res=solve(arr,k);
		System.out.println(res);
	}

	private static int solve(int[] arr, int k) {
		PriorityQueue<Integer> maxH=new PriorityQueue<Integer>();
		for(int i=0;i<arr.length;i++) {
			maxH.add(arr[i]);
			if(maxH.size()>k) {
				maxH.poll();
			}
		}
		System.out.println(maxH);
		return maxH.peek();
	}
}
