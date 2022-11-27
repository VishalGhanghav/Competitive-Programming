import java.util.Collections;
import java.util.PriorityQueue;

public class KthSmallestInArray {
	public static void main(String[] args) {
		int arr[]=new int[] {4,1,1,2, 3, 5};
		int k=3;
		int res=solve(arr,k);
		System.out.println(res);
	}

	private static int solve(int[] arr, int k) {
		PriorityQueue<Integer> maxH=new PriorityQueue<Integer>(Collections.reverseOrder());
		for(int i=0;i<arr.length;i++) {
			maxH.add(arr[i]);
			if(maxH.size()>k) {
				maxH.poll();
			}
		}
		return maxH.peek();
	}
}
