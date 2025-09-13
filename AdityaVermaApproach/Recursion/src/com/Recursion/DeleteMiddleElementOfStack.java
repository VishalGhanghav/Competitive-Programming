package AdityaVermaApproach.Recursion.src.com.Recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DeleteMiddleElementOfStack {
	public static void main(String[] args) {
	    Stack<Integer> s=new Stack<>();
	    s.push(1);
	    s.push(8);
	    s.push(0);
	    s.push(5);
	    s.push(2);

	    System.out.println("Array before sorting:");
	    for (Integer i : s) {
	      System.out.print(i + " ");
	      
	    }
	    System.out.println();

	    
	    Stack<Integer> sorted = deleteMid(s);
	    System.out.println("\nArray after sorting:");
	    for (Integer p : sorted) {
	      System.out.print(p + " ");
	      
	    }
	}

	private static Stack<Integer> deleteMid(Stack<Integer> s) {
		int k=s.size()/2+1;
		solve(s,k);
		return s;
		
	}

	private static void solve(Stack<Integer> s, int k) {
		//Base conduction
		if(k==1) {
			s.pop();
			return;
		}
		int temp=s.pop();
		solve(s,k-1);
		//Induction
		s.push(temp);
	}

	
}
