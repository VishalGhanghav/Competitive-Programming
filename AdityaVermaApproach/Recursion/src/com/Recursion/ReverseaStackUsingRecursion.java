package AdityaVermaApproach.Recursion.src.com.Recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ReverseaStackUsingRecursion {
	/*
	reverse([1,0,5,2])
	 ?? Pop 2
		 ?? reverse([1,0,5])
			 ?? Pop 5
				 ?? reverse([1,0])
					 ?? Pop 0
						 ?? reverse([1])
							 ?? Base case [1]
						 ?? insert([1],0)
							 ?? Pop 1
							 ?   ?? insert([],0) ? push 0 ? [0]
							 ?? push 1 ? [0,1]
					 ?? insert([0,1],5)
						 ?? Pop 1
						 ?   ?? insert([0],5)
						 ?       ?? Pop 0
						 ?       ?   ?? insert([],5) ? push 5 ? [5]
						 ?       ?? push 0 ? [5,0]
						 ?? push 1 ? [5,0,1]
				 ?? insert([5,0,1],2)
					 ?? Pop 1
					 ?   ?? insert([5,0],2)
					 ?       ?? Pop 0
					 ?       ?   ?? insert([5],2)
					 ?       ?       ?? Pop 5
					 ?       ?       ?   ?? insert([],2) ? push 2 ? [2]
					 ?       ?       ?? push 5 ? [2,5]
					 ?       ?? push 0 ? [2,5,0]
					 ?? push 1 ? [2,5,0,1]
	 */
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

	    
	    reverse(s);
	    System.out.println("\nArray after reverse:");
	    for (Integer p : s) {
	      System.out.print(p + " ");
	      
	    }
	}

	private static void reverse(Stack<Integer> st) {

		if (st.isEmpty() || st.size() == 1)
			return;

		Integer temp = st.pop();
		reverse(st);
		insert(st, temp);
	}

	private static void insert(Stack<Integer> st, Integer temp) {

		if (st.size() == 0) {
			st.push(temp);
			return;
		}
		int val = st.pop();
		insert(st, temp);
		st.push(val);
	}

	
}
