package Intervals;

import java.util.Arrays;
import java.util.Stack;

/*
 56. Merge Intervals
Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

 

Example 1:

Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
Example 2:

Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 

Constraints:

1 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 104
 */
public class MergeIntervals {
	public static void main(String args[]) {
		int [][] intervals= {{5,6},{1,4},{2,3}};
		int output[][]=merge(intervals);
		for(int i=0;i<output.length;i++) {
			for(int j=0;j<2;j++) {
				System.out.print(output[i][j]);
			}
			System.out.println();
		}
	}
	public static int[][] merge(int[][] intervals) {
		//Sort on the basis of start
		Arrays.sort(intervals,(a,b)->Integer.compare(a[0], b[0]));
		//Stack will have <start,end>
		Stack<int[]> st=new Stack<>();
		//1st interval pushed
		st.add(intervals[0]);
		
		for(int i=1;i<intervals.length;i++) {
			//now second elememt
			int startPoint2=intervals[i][0];
			int endPoint2=intervals[i][1];
			
			
			int[] poppedArray=st.pop();
			int startPoint1=poppedArray[0];
			int endPoint1=poppedArray[1];
			//merge condition 1 3   2 6
			if(endPoint1>=startPoint2) {//3>=2
				//1 4    2 3
				int endMax=Math.max(endPoint1, endPoint2);
				
				int merge[]=new int[]{startPoint1,endMax};
				st.push(merge);
			}else {
				//no merge
				//Push both arrays
				st.push(poppedArray);
				st.push(intervals[i]);
			}
		}
		System.out.println(st.size());
		int output[][]=new int[st.size()][2];
		for(int i=output.length-1;i>=0;i--) {
			System.out.println(i+" i");
			int popArray[]=st.pop();
			output[i][0]=popArray[0];
			output[i][1]=popArray[1];
			System.out.println(output[i][0]+" "+output[i][1]);
		}
		
		
		return output;
		//
	}
}
