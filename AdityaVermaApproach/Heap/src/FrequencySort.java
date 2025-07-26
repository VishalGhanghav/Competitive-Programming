package AdityaVermaApproach.Heap.src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class FrequencySort {
	public static void main(String[] args) {
		int arr[]=new int[] {1,1,2,2,2,3};
		int k=2;
		int[] res=new fs().sortElements(arr);
		for(int i=0;i<res.length;i++) {
			System.out.print(res[i]+" ");
		}
	}
}


class fs{
	static class Pair{
		int freq;
		int num;
		
		Pair(int freq,int num){
			this.freq=freq;
			this.num=num;
		}
	}
	public int[] sortElements(int[] arr) {
		int []res=new int[arr.length];
		HashMap<Integer,Integer> map =new HashMap<Integer,Integer>();
		for(int i=0;i<arr.length;i++) {
			int currentValue=map.getOrDefault(arr[i],0);
			map.put(arr[i], currentValue+1);
		}
		
		PriorityQueue<Pair> minHeap=new PriorityQueue<>(
				(a,b)->{
					return a.freq-b.freq;
				});
		for(int key:map.keySet()) {
			minHeap.add(new Pair(map.get(key),key));
		}
		int i=0;
		while(!minHeap.isEmpty()) {
			Pair element=minHeap.poll();
			int count=element.freq;
			while(count>0) {
				res[i]=element.num;
				System.out.println(res[i]);
				i++;
				count--;
			}
		}
		System.out.println(res.length);
		return res;
	}
	
}
