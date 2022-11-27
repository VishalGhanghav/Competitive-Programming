package com.Recursion;

import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.stream.events.Characters;

public class LetterCasePermutation {
	public static void main(String[]args) {
		Scanner sc=new Scanner(System.in);
		String st="a1b2";
		String op="";
		ArrayList<String> as=new ArrayList<>();
		as=solve(st,op,as);
		as.forEach(System.out::println);
    }

	private static ArrayList<String> solve(String ip, String op, ArrayList<String> as) {
		if(ip.isEmpty()) {
			as.add(op);
			return as;
		}
		if(Character.isAlphabetic(ip.charAt(0))){
		String op1=op+ip.substring(0,1).toLowerCase();
		String op2=op+ip.substring(0,1).toUpperCase();
		ip=ip.substring(1);
		solve(ip,op1,as);
		solve(ip,op2,as);
		}else {
			String op1=op;
			op1=op+ip.charAt(0);
			ip=ip.substring(1);
			solve(ip,op1,as);
		}
		return as;
		
	}
}
