package stack;

import java.util.Stack;

public class nearestSmallerToRight {
   public static void main(String[] args) {
      int[] set = new int[]{1, 3, 2, 4, 3};
      int n = set.length;
      int[] a = new int[n];
      a = NSR(set, n);

      for(int i = 0; i < a.length; ++i) {
         System.out.println(a[i]);
      }

   }

   private static int[] NSR(int[] set, int size) {
      int[] arr = new int[size];
      Stack<Integer> s = new Stack();

      for(int i = size - 1; i >= 0; --i) {
         if (s.size() == 0) {
            arr[i] = -1;
         } else if (s.size() > 0 && (Integer)s.peek() < set[i]) {
            arr[i] = (Integer)s.peek();
         } else if (s.size() > 0 && (Integer)s.peek() >= set[i]) {
            while(s.size() > 0 && (Integer)s.peek() >= set[i]) {
               s.pop();
            }

            if (s.size() == 0) {
               arr[i] = -1;
            } else {
               arr[i] = (Integer)s.peek();
            }
         }

         s.push(set[i]);
      }

      return arr;
   }
}
