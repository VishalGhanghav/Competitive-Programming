package DynamicProgramming.src.lcsProblems_recurMemoDp;

public class LongestCommonSubsequence {
	public static void main(String args[]) {
		String a="abcdaf";
		String b="acbcf";
		int res=longestCommonSubsequenceRecursion(a,b,a.length(),b.length());
		System.out.println(res);
	}
	private static int longestCommonSubsequenceRecursion(String a, String b,int m,int n) {
		if(m==0 || n==0) {
			return 0;
		}
		
		if(a.charAt(m-1)==b.charAt(n-1)) {
			return longestCommonSubsequenceRecursion(a, b, m-1, n-1)+1;
		}else {
			return Math.max(longestCommonSubsequenceRecursion(a, b, m, n-1), 
					longestCommonSubsequenceRecursion(a, b, m-1, n));
		}
	}
	
}
