package com.Recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ReverseaStackUsingRecursion {
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
