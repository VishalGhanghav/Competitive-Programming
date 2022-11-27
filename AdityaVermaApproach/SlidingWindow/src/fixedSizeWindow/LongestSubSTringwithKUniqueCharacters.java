package fixedSizeWindow;

import java.util.HashMap;
import java.util.Map;

public class LongestSubSTringwithKUniqueCharacters {

	public static void main(String[] args) {
		String str="aabacbebebe";
		int k=3;
		int res=solve(str,k);
		System.out.println(res);
	}

	private static int solve(String str, int k) {
		int i=0,j=0;
		int ans=-1;
		Map<Character,Integer> map=new HashMap<>();
		
		while(j<str.length()) {
			//calculation
			//Add element in map
			int currentCharValue=map.getOrDefault(str.charAt(j), 0);
			map.put(str.charAt(j), currentCharValue+1);
			
			if(map.size()<k) {
				j++;
			}else if (map.size()==k) {
				//ans
				ans=Math.max(ans, j-i+1);
				j++;
			}else if (map.size()>k) {
				//remove cal of i
				while(map.size()>k) {
					char currChar=str.charAt(i);
					int freqOfCurrentChar=map.get(currChar);
					map.put(currChar, freqOfCurrentChar-1);
					if(map.get(currChar)==0) {
					map.remove(str.charAt(i));
					}
					i++;
				}
				j++;
			}
		
		}
		return ans;
	}
}

	