package com.Recursion;

//{ Driver Code Starts
//Initial Template for Java

/*package whatever //do not write package name here */

import java.io.*;
import java.util.*;
class TowerOfHanoi {
 public static void main(String[] args) {
     Scanner sc = new Scanner(System.in);

     int T = sc.nextInt();//total testcases
     while (T-- > 0) {
         pq obj = new pq();
         int N;
         
         //taking input N
         N = sc.nextInt();

         //calling toh() method 
         System.out.println(obj.toh(N, 1, 3, 2));
     }
 }
}






//} Driver Code Ends


//User function Template for Java


//avoid space at the starting of the string in "move disk....."
class pq {

 public long toh(int n, int s, int dest, int help) {
     // Your code here
     if(n==1){
         System.out.println("move disk "+n+" from rod "+s +" to rod "+dest);
         return 1;
     }
     
     //move n-1 disk from source->helper
     long steps1=toh(n-1,s,help,dest);
     //move last disk from s->dest
     System.out.println("move disk "+n+" from rod "+s+" to rod "+dest);
     //move helper->dest
     long steps2=toh(n-1,help,dest,s);
     //Extra +1 is for n==1
     return steps1+steps2+1;
     
 }
}

