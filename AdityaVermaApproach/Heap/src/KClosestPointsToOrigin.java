import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class KClosestPointsToOrigin {
	public static void main(String[] args) {
		int arr[][]=new int[][] {{3,3},{5,-1},{2,-4}};
		int k=2;
		int[][] res=new kc().KclosestToOrigin(arr,k);
		for(int i=0;i<res.length;i++) {
			for(int j=0;j<res.length;j++) {
			System.out.print(res[i][j]+" ");
			}
		}
	}
}


class kc{
	public int[][] KclosestToOrigin(int[][] points, int k) {
		int [][]res=new int[k][];
		PriorityQueue<int[]> maxH=new PriorityQueue<>((a,b)->{
			return getDistance(b)-getDistance(a);
		});
		
		for(int[] i:points) {
			maxH.add(i);
			
			if(maxH.size()>k) {
				maxH.poll();
			}
		}
		int i=0;
		while(maxH.size()!=0) {
			res[i++]=maxH.poll();
		}
		return res;
		
	}
	public int getDistance(int[] p) {
		return p[0]*p[0]+p[1]*p[1];
	}
	
}
