package fixedSizeWindow;

import java.util.HashMap;

public class LongestSubSTringwithNonRepeatingCharacters {

	public static void main(String[] args) {
		String str="livetopractice";
		String t="toc";
		String res=solve(str,t);
		System.out.println(res);
	}

	private static String solve(String str, String t) {
		int ans=Integer.MAX_VALUE;
		String minS="";
		int i=0,j=0;
		HashMap<Character,Integer> map=new HashMap<>();
		for(int k=0;k<t.length();k++) {
			map.put(t.charAt(k), map.getOrDefault(t.charAt(k), 0)+1);
		}
		System.out.println(map);
		int count=map.size();
	
		while(j<str.length()) {
			char c=str.charAt(j);
			if(map.containsKey(c)) {
				map.put(c, map.get(c)-1);
				if(map.get(c)==0) {
					count--;
				}
			}
			if(count>0) {
				j++;
			}else if(count==0) {
				
				while(count==0) {
					if(ans>(j-i+1)) {
						minS=str.substring(i,j+1);
						ans=Math.min(ans, j-i+1);
					}
					
					
					char c1=str.charAt(i);
					if(map.containsKey(c1)) {
						map.put(c1, map.get(c1)+1);
						if(map.get(c1)>0) {
							count++;
						}
					}
					i++;
				}
				j++;
			}
			
		}

		return minS;
	}
}

	