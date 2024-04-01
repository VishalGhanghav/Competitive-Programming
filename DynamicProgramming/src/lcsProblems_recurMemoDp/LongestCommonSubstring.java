package DynamicProgramming.src.lcsProblems_recurMemoDp;

public class LongestCommonSubstring {
	public static void main(String args[]) {
		String a="abcdaf";
		String b="abpcf";
		int length=0;
		int res=longestCommonSubsequenceRecursion(a,b,a.length(),b.length(),length);
		System.out.println(res);
	}
	private static int longestCommonSubsequenceRecursion(String a, String b,int m,int n,int length) {
		if(m==0 || n==0) {
			return length;
		}
		
		if(a.charAt(m-1)==b.charAt(n-1)) {
			return longestCommonSubsequenceRecursion(a, b, m-1, n-1,length+1);
		}else {
			return Math.max(length, Math.max(longestCommonSubsequenceRecursion(a, b, m, n-1,0), 
					longestCommonSubsequenceRecursion(a, b, m-1, n,0)));
		}
	}
	
}
