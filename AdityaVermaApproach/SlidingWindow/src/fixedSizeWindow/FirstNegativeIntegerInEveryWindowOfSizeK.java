package fixedSizeWindow;

import java.util.*;
import java.lang.*;
import java.io.*;

class FirstNegativeIntegerInEveryWindowOfSizeK {
	public static void main(String[] args) throws IOException
	{
	        BufferedReader br =
          new BufferedReader(new InputStreamReader(System.in));
      int t =
          Integer.parseInt(br.readLine().trim()); // Inputting the testcases
      while(t-->0)
      {
          int n = Integer.parseInt(br.readLine().trim());
          long a[] = new long[(int)(n)];
          // long getAnswer[] = new long[(int)(n)];
          String inputLine[] = br.readLine().trim().split(" ");
          for (int i = 0; i < n; i++) {
              a[i] = Long.parseLong(inputLine[i]);
          }
          int k = Integer.parseInt(br.readLine().trim());
          
          Compute obj = new Compute();
          long answer[] = obj.printFirstNegativeInteger(a, n, k);
          int sz = answer.length;
          
          StringBuilder output = new StringBuilder();
          for(int i=0;i<sz;i++)
              output.append(answer[i]+" ");
          System.out.println(output);
          
      }
	}
}


//} Driver Code Ends


//User function Template for Java


class Compute {
  
  public long[] printFirstNegativeInteger(long a[], int size, int k)
  {
  ArrayList<Long> al=new ArrayList<Long>();
  long[] ans = new long[size-k+1];
  int i=0,j=0,z=0;
  while(j<size){
      if(a[j]<0){
          al.add(a[j]);
      }
      if(j-i+1<k){
          j++;
      }else if(j-i+1==k){
          if(al.size()==0){
              ans[z]=0;
              z++;
          }else{
              ans[z]=al.get(0);
              z++;
              if(a[i]==al.get(0)){
                  al.remove(al.get(0));
              }
          }
          i++;
          j++;
      }
        
     
  }
  return ans;
  }
}