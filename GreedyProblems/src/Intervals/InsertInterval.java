package Intervals;

import java.util.ArrayList;
import java.util.List;

/*
 57. Insert Interval
Medium
9K
643
Companies
You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent
 the start and the end of the ith interval and intervals is sorted in ascending order by starti. 
 You are also given an interval newInterval = [start, end] that represents the start and end of another interval.

Insert newInterval into intervals such that intervals is still sorted in ascending order by starting 
and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).

Return intervals after the insertion.

 

Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 

Constraints:
 */
public class InsertInterval {
	public static void main(String args[]) {
		int [][] intervals= {{1,2},{3,5},{6,7},{8,10},{12,16}};
		int []newInterval= {4,8};
		int output[][]=insert(intervals,newInterval);
		for(int i=0;i<output.length;i++) {
			for(int j=0;j<2;j++) {
				System.out.print(output[i][j]+ " ");
			}
			System.out.println();
		}
	}
	public static int[][] insert(int[][] intervals, int[] newInterval) {
		List<int[]> resultList=new ArrayList<>();
		
		for(int[] in:intervals) {
			if(in[1]<newInterval[0]) {
				//1 2  4 8
				resultList.add(in);
			}else if(newInterval[1]<in[0]) {
				//3 10  12 16
				resultList.add(newInterval);
				newInterval=in;
			}else {
				//merge
				newInterval[0]=Math.min(in[0], newInterval[0]);
				newInterval[1]=Math.max(in[1], newInterval[1]);
				
			}
		}
		//3 10  12 16
		//Last element yet to be added in list
		resultList.add(newInterval);
		return resultList.toArray(new int[resultList.size()][]);
	}
}
