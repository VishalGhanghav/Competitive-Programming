package com.Recursion;

import java.util.ArrayList;
import java.util.List;

public class GenerateBalancedParenthesis {
	public static void main(String[] args) {
	    int n = 4;
	    int open = n, closed = n;
	    String op = "";
	    List<String> output = new ArrayList<>();
	    List<String> result = solve(open, closed, op, output);
	    result.forEach(System.out::println);
	  }

	private static List<String> solve(int open, int closed, String op, List<String> output) {
		if(open==0 && closed==0) {
			output.add(op);
			return output;
		}
		String op1,op2;
		//if open!=0
		if(open!=0) {
			op1=op;
			op1+="(";
			solve(open-1,closed,op1,output);
		}
		
		//if closed>open
		if(closed>open) {
			op2=op;
			op2+=")";
			solve(open,closed-1,op2,output);
		}
		
		return output;
	}
}
