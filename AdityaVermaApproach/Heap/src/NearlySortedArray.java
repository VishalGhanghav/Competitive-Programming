
import java.util.*;
import java.io.*;
import java.lang.*;

class NearlySortedArray
{
  public static void main(String args[])
  {
      Scanner sc = new Scanner(System.in);
      int t = sc.nextInt();
      
      while(t-- > 0)
      {
          int num = sc.nextInt();
          int k = sc.nextInt();
          
          int arr[] = new int[num];
          for(int i = 0; i < num; i++)
              arr[i] = sc.nextInt();
          
          ArrayList <Integer> res = new pq().nearlySorted(arr, num, k);
          for (int i = 0; i < res.size (); i++)
              System.out.print (res.get (i) + " ");
          System.out.println();
      }
  }
}


//} Driver Code Ends


class pq
{
  //Function to return the sorted array.
  ArrayList <Integer> nearlySorted(int arr[], int size, int k)
  {
      // your code here
	  ArrayList<Integer> al=new ArrayList<Integer>();
	  PriorityQueue<Integer> minH=new PriorityQueue<Integer>();
	  for(int i=0;i<size;i++) {
		  minH.add(arr[i]);
		  if(minH.size()>k) {
			  al.add(minH.peek());
			  minH.poll();
		  }
	  }
	  System.out.println(al);
	  while(minH.size()!=0) {
		  al.add(minH.peek());
		  minH.poll();
	  }
	  return al;
  }
}
