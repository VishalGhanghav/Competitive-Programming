package fixedSizeWindow;

import java.util.HashMap;

public class Return1stIndexOfAllAnagramsInAString {
	public static void main(String[] args) 
	{
		String input="aabaabaa";
		String pattern="abaa";
		System.out.println("Count OF Anagrams"+countOfAnagrams(input,pattern));
	}

	private static int countOfAnagrams(String ip, String p) {
		int ans=0;
		HashMap<Character,Integer> map=new HashMap<>();
		
		for(int i=0;i<p.length();i++) {
			if(map.containsKey(p.charAt(i))) {
				map.put(p.charAt(i), map.get(p.charAt(i))+1);
			}else {
			map.put(p.charAt(i), 1);
			}
		}
		System.out.println(map);
		int count=map.size();
		int i=0,j=0;
		while(j<ip.length()) {
			//calculations
			if(map.containsKey(ip.charAt(j))) {
				map.put(ip.charAt(j), map.get(ip.charAt(j))-1);
				if(map.get(ip.charAt(j))==0) {
					count--;
				}
			}
			//window size less than k 
			if(j-i+1<p.length()) {
				j++;
			}//window size =k
			else if(j-i+1==p.length()) {
				//ans calculation
				if(count==0) {
					ans++;
				}
				//before making window slide lets set everything for current first element of window
				if(map.containsKey(ip.charAt(i))) {
					map.put(ip.charAt(i), map.get(ip.charAt(i))+1);
					//In case some element was a:0 and now increased to a:1 
					//Count must be increamented
					if(map.get(ip.charAt(i))==1) {
						count++;
					}
					
				}
				i++;
				j++;	
			}
		}
		return ans;
	}
	
}
