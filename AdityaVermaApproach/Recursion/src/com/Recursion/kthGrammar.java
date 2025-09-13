package AdityaVermaApproach.Recursion.src.com.Recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class kthGrammar {
	public static void main(String[] args) {
		int res= kthGrammar(2, 2);
		System.out.println(res);
	}

	 public static int kthGrammar(int n, int k) {
	        //Base Condition
	        if(n==1 && k==1){
	            return 0;
	        }
	        //Induction.Find mid
	        int mid=(int)Math.pow(2,n-1)/2;
	        if(k<=mid){
	          return  kthGrammar(n-1,k);
	        }else {
	            //compliment
	            return 1-kthGrammar(n-1,k-mid);
	        }
	    }

	
}
