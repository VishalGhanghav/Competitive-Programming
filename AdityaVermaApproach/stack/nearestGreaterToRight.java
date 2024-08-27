package AdityaVermaApproach.stack;

import java.util.Stack;

public class nearestGreaterToRight {
   public static void main(String[] args) {
      int[] set = new int[]{1, 3, 2, 4};
      int n = set.length;
      int[] a = new int[n];
      a = GreaterToRight(set, n);

      for(int i = 0; i < a.length; ++i) {
         System.out.println(a[i]);
      }

   }

   private static int[] GreaterToRight(int[] set, int n) {
      int[] arr = new int[n];
      Stack<Integer> s = new Stack();

      for(int i = n - 1; i >= 0; --i) {
         if (s.size() == 0) {
            arr[i] = -1;
         } else if ((Integer)s.peek() > set[i]) {
            arr[i] = (Integer)s.peek();
         } else if ((Integer)s.peek() <= set[i]) {
            while(s.size() > 0 && (Integer)s.peek() <= set[i]) {
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
