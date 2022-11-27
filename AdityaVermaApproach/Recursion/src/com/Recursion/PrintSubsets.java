package com.Recursion;

import java.util.Scanner;

public class PrintSubsets {
	 public static void main(String[] args) {
	     Scanner sc = new Scanner(System.in);

	         
	         //taking input N
	        String N = sc.nextLine();
	         String op="";
	         //calling toh() method 
	         printSubsets(N, op);
	     }
	 

	private static void printSubsets(String ip, String op) {
		if(ip.length()==0) {
			System.out.println(op);
			return ;
		}
		String op1=op;
		String op2=op+ip.charAt(0);
		//all element after 1 index including 1
		ip=ip.substring(1);
		printSubsets(ip, op1);
		printSubsets(ip, op2);
	}
}
