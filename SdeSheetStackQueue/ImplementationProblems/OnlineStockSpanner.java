package SdeSheetStackQueue.ImplementationProblems;

import java.util.Stack;

public class OnlineStockSpanner {

    class Pair {
        int ind;
        int val;

        Pair(int first, int second) {
            this.ind = first;
            this.val = second;
        }
    }

    Stack<Pair> st;
    int ind;

    public OnlineStockSpanner() {
        st = new Stack<>();
        ind = -1;
    }

    public int next(int price) {
        ind = ind + 1;
        // I will get my ans at currInd - pge
        // SO during pge process we remove elements < currentPrice
        while (!st.isEmpty() && st.peek().val <= price) {
            st.pop();
        }
        // my ans would be currentIndex - IndexOfPge
        int ans = ind - (st.isEmpty() ? -1 : st.peek().ind);
        st.push(new Pair(ind, price));
        return ans;
    }

    public static void main(String[] args) {
        OnlineStockSpanner spanner = new OnlineStockSpanner();

        // Example usage
        System.out.println(spanner.next(100)); // Output: 1
        System.out.println(spanner.next(80));  // Output: 1
        System.out.println(spanner.next(60));  // Output: 1
        System.out.println(spanner.next(70));  // Output: 2
        System.out.println(spanner.next(60));  // Output: 1
        System.out.println(spanner.next(75));  // Output: 4
        System.out.println(spanner.next(85));  // Output: 6
    }
}
