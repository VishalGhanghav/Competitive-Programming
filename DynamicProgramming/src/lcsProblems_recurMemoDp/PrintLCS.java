package lcsProblems_recurMemoDp;

public class PrintLCS {
	public static void main(String args[]) {
		String a="abcdaf";
		String b="acbcf";
		String sb=new StringBuilder(a).reverse().toString();
		System.out.println(sb);
		String res=longestCommonSubsequenceRecursion(a,b,a.length(),b.length());
		System.out.println(res);
	}
	private static String longestCommonSubsequenceRecursion(String a, String b,int m,int n) {
		if(m==0 || n==0) {
			return "";
		}
		
		if(a.charAt(m-1)==b.charAt(n-1)) {
			return longestCommonSubsequenceRecursion(a, b, m-1, n-1)+a.charAt(m-1);
		}else {
			return maxByLength(longestCommonSubsequenceRecursion(a, b, m, n-1), 
					longestCommonSubsequenceRecursion(a, b, m-1, n));
		}
	}
	private static String maxByLength(String s1,
			String s2) {
		if(s1.length()>=s2.length()) {
			return s1;
		}else {
			return s2;
		}
	}
}
