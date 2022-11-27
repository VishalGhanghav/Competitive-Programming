

import java.util.*;

 class Nd {
    int val;
    Nd left, right;
    Nd(int data) {
        this.val = data;
        left = right = null;
    }
}
public class InorderTraversal {
	private static List<Integer> ans = new ArrayList<Integer>();
    public static List<Integer> preorderTraversal(Nd root) {
    	Queue<Nd> q=new LinkedList<Nd>();
    	
        if(root == null) 
            return ans;
        
        ans = preorderTraversal(root.left);
        ans.add(root.val);
        ans = preorderTraversal(root.right);
        return ans;
    }
   


    public static void main(String args[]) {


        Nd root = new Nd(1);
        root.left = new Nd(2);
        root.right = new Nd(3);
        root.left.left = new Nd(4);
        root.left.right = new Nd(5);
        root.left.right.left = new Nd(8);
        root.right.left = new Nd(6);
        root.right.right = new Nd(7);
        root.right.right.left = new Nd(9);
        root.right.right.right = new Nd(10);




        List<Integer> preOrder = new ArrayList < > ();
        preOrder = preorderTraversal(root);

        System.out.print("The preOrder Traversal is : ");
        for (int i = 0; i < preOrder.size(); i++) {
            System.out.print(preOrder.get(i) + " ");
        }
    }
    

}
/*Iterative
 import java.util.*;
In stack we push left Nd last so we can access it first

class Nd {
    int data;
    Nd left, right;
    Nd(int data) {
        this.data = data;
        left = right = null;
    }
}
public class TUF {
    static ArrayList < Integer > preOrderTrav(Nd curr) {
        ArrayList < Integer > preOrder = new ArrayList < Integer > ();
        if (curr == null)
            return preOrder;

        Stack < Nd > s = new Stack < > ();
        s.push(curr);

        while (!s.isEmpty()) {
            Nd topNd = s.peek();
            preOrder.add(topNd.data);
            s.pop();
            if (topNd.right != null)
                s.push(topNd.right);
            if (topNd.left != null)
                s.push(topNd.left);
        }
        return preOrder;

    }


    public static void main(String args[]) {


        Nd root = new Nd(1);
        root.left = new Nd(2);
        root.right = new Nd(3);
        root.left.left = new Nd(4);
        root.left.right = new Nd(5);
        root.left.right.left = new Nd(8);
        root.right.left = new Nd(6);
        root.right.right = new Nd(7);
        root.right.right.left = new Nd(9);
        root.right.right.right = new Nd(10);




        ArrayList < Integer > preOrder = new ArrayList < > ();
        preOrder = preOrderTrav(root);

        System.out.print("The preOrder Traversal is : ");
        for (int i = 0; i < preOrder.size(); i++) {
            System.out.print(preOrder.get(i) + " ");
        }

    }
}*/
 