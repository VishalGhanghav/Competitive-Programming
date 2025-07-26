package AdityaVermaApproach.Heap.src;

import java.util.Collections;
import java.util.PriorityQueue;

public class DifferenceBetweenK1andK2Smallest {
	public static void main(String[] args) {
		int arr[]=new int[] {4,1,1,2, 3, 5};
		int k1=3;
		int k2=5;
		int res=solve(arr,k1,k2);
		System.out.println(res);
	}

	private static int solve(int[] arr, int k1, int k2) {
		PriorityQueue<Integer> maxH=new PriorityQueue<Integer>(Collections.reverseOrder());
		int larger=0;
		int smaller=0;
		if(k1>k2) {
			larger=k1;
			smaller=k2;
		}else {
			larger=k2;
			smaller=k1;
		}
		for(int i=0;i<arr.length;i++) {
			maxH.add(arr[i]);
			if(maxH.size()>larger) {
				maxH.poll();
			}
		}
		int k1thLArger=maxH.peek();
		System.out.println(k1thLArger);
		while(maxH.size()!=smaller) {
			maxH.poll();
		}
		int k2thSmallest=maxH.peek();
		System.out.println(k2thSmallest);
		int sum=0;
		for(int i=0;i<arr.length;i++) {
			if(arr[i]>k2thSmallest && arr[i]<k1thLArger) {
				sum+=arr[i];
			}
		}
		return sum;
	}
}
