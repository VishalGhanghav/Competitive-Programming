package com.Recursion;

//{ Driver Code Starts
//Initial Template for Java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class PermutationWithSpaces
{
  public static void main(String args[])throws IOException
  {
      
      BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
      int t = Integer.parseInt(read.readLine());
      while(t-- > 0)
      {
          String S = read.readLine().trim();
          pws ob = new pws();
          ArrayList<String> ans = new ArrayList<String>();
          ans = ob.permutation(S);
          
          for(int i=0;i<ans.size();i++){
              System.out.print("("+ans.get(i)+")");
          }
          System.out.println();
      }
  }
}


//} Driver Code Ends


//User function Template for Java



class pws{
  
  ArrayList<String> permutation(String s){
	  ArrayList<String> as=new ArrayList<>();
	  String op="";
	  op=op+s.charAt(0);
	  s=s.substring(1);
	  as=getPermutationWithSpaces(s,op,as);
	return as;
      // Code Here
  }

private ArrayList<String> getPermutationWithSpaces(String ip, String op, ArrayList<String> as) {
	if(ip.isEmpty()) {
		as.add(op);
		return as;
	}
	String op1=op+" "+ip.charAt(0);
	String op2=op+ip.charAt(0);
	ip=ip.substring(1);
	getPermutationWithSpaces(ip, op1, as);
	getPermutationWithSpaces(ip, op2, as);
		return as;
	}
	

}
