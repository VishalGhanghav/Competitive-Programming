package meetingRooms;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;
/*
 Given an array of meeting time intervals consisting of start and end times[[s1,e1],[s2,e2],...](si< ei),
  determine if a person could attend all meetings.
Example 1:
Input:
[[0,30],[5,10],[15,20]]
Output:
 false
Example 2:
Input:
 [[7,10],[2,4]]

Output:
 true

 */
public class MeetingRooms1 {
	public static void main(String args[]) {
		/*
		 Approach:Sort on basis of start time. 
		 if at anytime meetings conincide return false
		 */
		int [][]intervals= {{20,30},{5,10},{15,20}};
		System.out.println(meetings(intervals));
		
		
	}

	private static boolean meetings(int[][] intervals) {
		Arrays.sort(intervals, new Comparator<int[]>() {
		      public int compare(int[] i1, int[] i2) {
		        return i1[0] - i2[0];
		      }
		    });
		/*
		 Arrays.sort(intervals,(a,b)->Integer.compare(a[0], b[0])); 
		 
		 
		for(int i=0;i<3;i++) {
			for(int j=0;j<2;j++) {
				System.out.print(intervals[i][j]);
			}
			System.out.println();
		}*/
		
		Stack<int[]> stack=new Stack<int[]>();
		stack.add(intervals[0]);
		for(int i=1;i<intervals.length;i++) {
			int sp2=intervals[i][0];
			int ep2=intervals[i][1];
			
			int[] poppedArray=stack.pop();
			int sp1=poppedArray[0];
			int ep1=poppedArray[1];
			
			if(ep1<=sp2) {
				stack.push(intervals[i]);
			}else {
				return false;
			}
		}
		return true;
	}
	/*
	 *Time complexity: O(log(n))
	 */
}
