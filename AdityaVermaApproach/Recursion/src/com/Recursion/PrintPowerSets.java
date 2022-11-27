package com.Recursion;

//{ Driver Code Starts
//Initial Template for Java

import java.util.*;
import java.lang.*;
import java.io.*;
class PrintPowerSets
{
  public static void main(String[] args) throws IOException
  {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int T = Integer.parseInt(br.readLine().trim());
      while(T-->0)
      {
          String s = br.readLine().trim();
          aps ob = new aps();
          List<String> ans = ob.AllPossibleStrings(s);
          for(String i: ans)
              System.out.print(i + " ");
          System.out.println();
          
      }
  }
}

//} Driver Code Ends


//User function Template for Java

class aps
{
  public List<String> AllPossibleStrings(String s)
  {
      // Code here
         ArrayList<String> list = new ArrayList<String>();
     findAllSubsets(s,"",list);
     //Remove " " from " " a b ab
     list.remove("");
		Collections.sort(list);
		return list;
  }
  
    public static void findAllSubsets(String str,String output, ArrayList<String> list) {
		if(str.length() == 0) {
			list.add(output.trim());
			return;
		}
		
		String output1 = output;
		String output2 = output;

		output2 = output2+ str.charAt(0);
		
		str = str.substring(1, str.length());
		findAllSubsets(str, output1,list);
		findAllSubsets(str, output2,list);
	}

}