package com.Recursion;

import java.util.HashSet;
import java.util.Set;

public class PrintUniqueSubsets {
	public static void main(String[] args) {
		Set<String> uniqueSubset=new HashSet<>();
		uniqueSubset=printUnique("aab","",uniqueSubset);
		System.out.println("Unique Subset");
		uniqueSubset.forEach(System.out::println);
	}

	private static Set<String> printUnique(String ip, String op, Set<String> uniqueSubset) {
		if(ip.isEmpty()) {
			uniqueSubset.add(op);
			return uniqueSubset;//or new Hashset<>()
		}
		String op1=op;
		String op2=op+ip.charAt(0);
		ip=ip.substring(1);
		printUnique(ip, op1, uniqueSubset);
		printUnique(ip, op2, uniqueSubset);
		return uniqueSubset;
	}
}
