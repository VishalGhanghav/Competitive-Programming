package com.BinarySearch;
//{ Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;

class NextCharacterInCharArray
{
  public static void main (String[] args)throws IOException {

      BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
      int t = Integer.parseInt(read.readLine());
      
      while(t-- > 0)
      {
         char[] letters= {'a','c','d'};
         int target='a';
         System.out.println(new FindChar().findFloor(letters,target));
        
      }
  }
  
}
//} Driver Code Ends


class FindChar{
  
  // Function to find floor of x
  // arr: input array
  // n is the size of array
  static char findFloor(char[] letters,int target)
  {
	   int start=0,end=letters.length-1;
       char res=letters[0];
       while(start<=end){
           int mid=start+(end-start)/2;
           if(letters[mid]==target){
               start=mid+1;
           }
           else if(letters[mid]<target)
               start=mid+1;
           else if(letters[mid]>target){
               res=letters[mid];
               end=mid-1;
           }
       }
       
       return res; 
      
  }
  
}

