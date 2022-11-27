package com.Recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SortStackUsingRecursion {
	public static void main(String[] args) {
	    Stack<Integer> s=new Stack<>();
	    s.push(1);
	    s.push(0);
	    s.push(5);
	    s.push(2);

	    System.out.println("Array before sorting:");
	    for (Integer i : s) {
	      System.out.print(i + " ");
	      
	    }
	    System.out.println();

	    
	    Stack<Integer> sorted = sortStack(s);
	    System.out.println("\nArray after sorting:");
	    for (Integer p : sorted) {
	      System.out.print(p + " ");
	      
	    }
	}

	private static Stack<Integer> sortStack(Stack<Integer> s) {
		//base condition
		if(s.size()==1) {
			return s;
		}
		int temp=s.pop();
		//Hypothesis
		sortStack(s);
		//now insert temp element
		//Induction
		return insertIntoStack(s,temp);
		
	}

	private static Stack<Integer> insertIntoStack(Stack<Integer> s, int key) {
		//Base Condition
		if(s.isEmpty() || s.peek()<=key) {
			s.push(key);
			return s;
		}
		int topEle=s.pop();
		//Hypothesis
		insertIntoStack(s, key);
		s.push(topEle);
		//Induction
		return s;
		
	}
}
