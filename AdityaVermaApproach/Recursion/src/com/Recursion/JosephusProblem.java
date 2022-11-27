package com.Recursion;

import java.util.ArrayList;
import java.util.List;

public class JosephusProblem {
	public static void main(String args[]) {
		  int n =5, k = 3;
		    int index = 0, ans = -1;
		    List<Integer> soldiers = new ArrayList<>();

		    for (int i = 1; i <= n; i++) {
		      soldiers.add(i);
		    }
		    int res = solve(soldiers, k, index, ans);
		    System.out.println("Last soldier standing is: " + res);
	}

	private static int solve(List<Integer> soldiers, int k, int index, int ans) {
		if(soldiers.size()==1) {
			ans=soldiers.get(0);
			return ans;
		}
		index=((index+k-1)%soldiers.size());
		System.out.println("Soldier at "+soldiers.remove(index)+"Killed");//takes O(n) time
		
		return solve(soldiers,k,index,ans);
	}
}
