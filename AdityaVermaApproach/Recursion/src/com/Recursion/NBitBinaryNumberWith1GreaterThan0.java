package com.Recursion;

import java.util.ArrayList;

public class NBitBinaryNumberWith1GreaterThan0 {
	public static void main(String args[]) {
		int n=4;
		int one=0,zero=0;
		String op="";
		ArrayList<String> res=new ArrayList<String>();
		res=solve(n,one,zero,op,res);
		res.forEach(System.out::println);
	}

	private static ArrayList<String> solve(int n,int one, int zero, String op, ArrayList<String> res) {
		if(n==0) {
			res.add(op);
			return res;
		}
		//one can always be added
		String op1=op;
		op1+="1";
		solve(n-1,one+1,zero,op1,res);
		//If one>zeroes the only add zero
		if(one>zero) {
			String op2=op;
			op2+="0";
			solve(n-1,one,zero+1,op2,res);
		}
		return res;
	}
}
