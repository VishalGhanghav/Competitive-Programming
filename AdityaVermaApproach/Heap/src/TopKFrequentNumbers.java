import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class TopKFrequentNumbers {
	public static void main(String[] args) {
		int arr[]=new int[] {1,1,1,3,3,2,4};
		int k=2;
		int[] res=new kf().findClosestElements(arr,k);
		for(int i=0;i<k;i++) {
			System.out.print(res[i]+" ");
		}
	}
}


class kf{
	static class Pair{
		int freq;
		int num;
		
		Pair(int freq,int num){
			this.freq=freq;
			this.num=num;
		}
	}
	public int[] findClosestElements(int[] arr, int k) {
		int []res=new int[k];
		HashMap<Integer,Integer> map=new HashMap<>();
		for(int i=0;i<arr.length;i++) {
			int currentVal=map.getOrDefault(arr[i], 0);
			map.put(arr[i], currentVal+1);
		}
		System.out.println(map);
		PriorityQueue<Pair> minH=new PriorityQueue<>((a,b)->
			a.freq-b.freq
		);
	
		for(int key:map.keySet()) {
			minH.add(new Pair(map.get(key),key));
			
		if(minH.size()>k) {				
			minH.poll();
		}
		}
		int i=0;
		while(minH.size()!=0) {
		res[i++]=minH.poll().num;	
		}
		Arrays.sort(res);
		return res;
	}
	
}
