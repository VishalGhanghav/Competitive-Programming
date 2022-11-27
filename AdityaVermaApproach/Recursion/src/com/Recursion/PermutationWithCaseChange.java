package com.Recursion;

public class PermutationWithCaseChange {
	public static void change(String ip) {
		String op="";
		change(ip,op);
	}
	private static void change(String ip,String op) {
		if(ip.isEmpty()) {
			System.out.println(op);
			return;
		}
		String op1=op;
		String op2=op;
		String ip_="";
		ip_+=ip.charAt(0);
		op1+=ip_.toLowerCase();
		op2+=ip_.toUpperCase();
		ip=ip.substring(1);
		change(ip,op1);
		change(ip,op2);
	}
	public static void main(String[] args) {
		change("abcde");
	}
}
