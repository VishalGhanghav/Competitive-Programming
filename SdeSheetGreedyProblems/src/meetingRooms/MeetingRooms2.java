package meetingRooms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/*
 Problem Description



Given an 2D integer array A of size N x 2 denoting time intervals of different meetings.



Where:



A[i][0] = start time of the ith meeting.
A[i][1] = end time of the ith meeting.


Find the minimum number of conference rooms required so that all meetings can be done.

Note :- If a meeting ends at time t, another meeting starting at time t can use the same conference room





Problem Constraints
1 <= N <= 105



0 <= A[i][0] < A[i][1] <= 2 * 109





Input Format
The only argument given is the matrix A.



Output Format
Return the minimum number of conference rooms required so that all meetings can be done.



Example Input
Input 1:

 A = [      [0, 30]
            [5, 10]
            [15, 20]
     ]

Input 2:

 A =  [     [1, 18]
            [18, 23]
            [15, 29]
            [4, 15]
            [2, 11]
            [5, 13]
      ]


Example Output
Output 1:

 2
Output 2:

 4


Example Explanation
Explanation 1:

 Meeting one can be done in conference room 1 form 0 - 30.
 Meeting two can be done in conference room 2 form 5 - 10.
 Meeting three can be done in conference room 2 form 15 - 20 as it is free in this interval.
Explanation 2:

 Meeting one can be done in conference room 1 from 1 - 18.
 Meeting five can be done in conference room 2 from 2 - 11.
 Meeting four can be done in conference room 3 from 4 - 15.
 Meeting six can be done in conference room 4 from 5 - 13.
 Meeting three can be done in conference room 2 from 15 - 29 as it is free in this interval.
 Meeting two can be done in conference room 4 from 18 - 23 as it is free in this interval.
 */
public class MeetingRooms2 {

	public static void main(String[] args) {
		/*
		 Approach:Sort on basis of start time. 
		Now each time add on the basis of end time in priority queue.
		Smallest end time on top
		 */
		int [][]intervals= {{1, 18},
				{18, 23},
				{15, 29},
				{4, 15},
				{2, 11},
				{5, 13}};
		System.out.println(meetingsHard(intervals));

	}

	private static int meetings(int[][] intervals) {
		Arrays.sort(intervals,(a,b)->Integer.compare(a[0],b[0]));


		/* 
		for(int i=0;i<3;i++) {
			for(int j=0;j<2;j++) {
				System.out.print(intervals[i][j]);
			}
			System.out.println();
		}*/

		PriorityQueue<Integer> minH=new PriorityQueue<>();
		minH.add(intervals[0][1]);
		for(int i=1;i<intervals.length;i++) {
			int sp2=intervals[i][0];
			int ep2=intervals[i][1];

			int ep1=minH.peek();
			if(ep1<=sp2) {
				System.out.println("if");
				minH.poll();
				minH.add(ep2);
				System.out.println(minH.size());
			}else {
				System.out.println("else");
				minH.add(ep2);
				System.out.println(minH.size());
			}
		}
		return minH.size();
	}


	private static int meetingsHard(int[][] intervals) {
		Arrays.sort(intervals,(a,b)->Integer.compare(a[0],b[0]));


		/* 
		for(int i=0;i<3;i++) {
			for(int j=0;j<2;j++) {
				System.out.print(intervals[i][j]);
			}
			System.out.println();
		}*/



		PriorityQueue<Pair> minH=new PriorityQueue<>((a,b)->{
			if(a.end<b.end) {
				return a.end-b.end; //it will give negative ie. similar to -1
			}else {
				return a.end-b.end;
			}/*or
				 if(a.end<b.end) {
					return -1; 
				}else {
					return 1;
				}
			 */
		});
		minH.add(new Pair(intervals[0][0], intervals[0][1]));
		for(int i=1;i<intervals.length;i++) {
			int sp2=intervals[i][0];
			int ep2=intervals[i][1];
			int ep1=minH.peek().end;
			if(ep1<=sp2) {
				minH.poll();
				minH.add(new Pair(sp2, ep2));
			}else {
				minH.add(new Pair(sp2, ep2));
			}
		}
		return minH.size();
	}

}
/*

List<Pair> list=new ArrayList<>();
for(int i=0;i<intervals.length;i++){
    list.add(new Pair(intervals[i][0],intervals[i][1]));
}
System.out.println("Before Sort");
for(int i=0;i<intervals.length;i++){
       System.out.println(list.get(i).start+" "+list.get(i).end);
   }
Collections.sort(list,(p,q)->{
   if(p.end<q.end){
       return -1;
   }else{
       return 1;
   }
});
System.out.println("After Sort");
for(int i=0;i<intervals.length;i++){
System.out.println(list.get(i).start+" "+list.get(i).end);
}
 */