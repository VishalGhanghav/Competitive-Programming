package AdityaVermaApproach.Recursion.src.com.Recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SortStackUsingRecursion {

	/*
	sortStack([1,0,5,2])
	??? Pop 2
	?   ??? sortStack([1,0,5])
	?       ??? Pop 5
	?       ?   ??? sortStack([1,0])
	?       ?       ??? Pop 0
	?       ?       ?   ??? sortStack([1])
	?       ?       ?       ??? Base case reached ? return [1]
	?       ?       ?
	?       ?       ??? insertIntoStack([1], 0)
	?       ?            ??? 1 > 0 ? pop 1
	?       ?            ??? insertIntoStack([], 0)
	?       ?                 ??? push 0 ? [0]
	?       ?            ??? push 1 ? [0,1]
	?       ?
	?       ??? insertIntoStack([0,1], 5)
	?            ??? push 5 ? [0,1,5]
	?
	??? insertIntoStack([0,1,5], 2)
		 ??? 5 > 2 ? pop 5
		 ??? insertIntoStack([0,1], 2)
			  ??? push 2 ? [0,1,2]
		 ??? push 5 ? [0,1,2,5]

	 */
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
