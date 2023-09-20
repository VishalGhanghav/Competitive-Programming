package fixedSizeWindow;

import java.util.HashMap;
import java.util.Map;

public class LongestSubSTringwithNonRepeatingCharacters {

	
	
	
	
	public static void main(String[] args) {
		String str="pwwkew";
		int res=solve(str);
		System.out.println(res);
	}

	private static int solve(String str) {
		int i=0,j=0;
		int ans=-1;
		Map<Character,Integer> map=new HashMap<>();
		
		while(j<str.length()) {
			//calculation
			//Add element in map
			int currentCharValue=map.getOrDefault(str.charAt(j), 0);
			map.put(str.charAt(j), currentCharValue+1);
			
			if(map.size()==(j-i+1)) {
				//ALl non repeating present in string.So potential result
				ans=Math.max(ans, j-i+1);
				j++;
			}else if (map.size()<j-i+1) {
				//Some repeating present
				while(map.size()<j-i+1) {
					map.put(str.charAt(i), map.get(str.charAt(i))-1);
					if(map.get(str.charAt(i))==0) {
						map.remove(str.charAt(i));
					}
					i++;
				}
				//less chances of happening 
				if(map.size()==j-i+1){
					ans=Math.max(ans,j-i+1);
	                }
	                j++;
			}
		
		}
		return ans;
	}
}

	