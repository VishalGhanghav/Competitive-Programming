package com.Reflektive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Problem1 {

	public static void main(String[] args) {
		String s1="cbacba";//return l
		//aabbcc return a 
		//aabbccc return c
		
		
		HashMap<Character, Integer> hm=new HashMap<Character, Integer>();
		ArrayList<Character> orderedCharList=new ArrayList<Character>();
		for(int i=0;i<s1.length();i++) {
			if(hm.containsKey(s1.charAt(i))) {
				hm.put(s1.charAt(i), hm.get(s1.charAt(i))+1);
			}else {
				hm.put(s1.charAt(i), 1);
				orderedCharList.add(s1.charAt(i));
			}
		}

		
		System.out.println(hm);
		int greaterVal=0;
		Character ch=null;
		for(Map.Entry<Character, Integer> es:hm.entrySet()){
			if(es.getValue()>greaterVal) {
				greaterVal=es.getValue();
				ch=es.getKey();
				
			}
		}
		//cba
		//c 2
		for(int i=0;i<orderedCharList.size();i++) {
			if(hm.get(orderedCharList.get(i))==greaterVal) {
				ch=orderedCharList.get(i);
				break;
			}
		}
		
		System.out.println(ch);
		
		
		
		
/*
a 2 b 2 c 3


 */
	}
	
	

}
