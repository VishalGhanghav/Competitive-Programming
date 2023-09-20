

import java.util.*;

class Nde {
    int val;
    Nde left, right;
    Nde(int data) {
        this.val = data;
        left = right = null;
    }
}
public class PostOrderTraversal {
	private static List<Integer> ans = new ArrayList<Integer>();
    public static List<Integer> preorderTraversal(Nde root) {
    	
        if(root == null) 
            return ans;
        
        ans = preorderTraversal(root.left);
        ans = preorderTraversal(root.right);
        ans.add(root.val);
        return ans;
    }
   


    public static void main(String args[]) {


        Nde root = new Nde(1);
        root.left = new Nde(2);
        root.right = new Nde(3);
        root.left.left = new Nde(4);
        root.left.right = new Nde(5);
        root.left.right.left = new Nde(8);
        root.right.left = new Nde(6);
        root.right.right = new Nde(7);
        root.right.right.left = new Nde(9);
        root.right.right.right = new Nde(10);




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
In stack we push left Nde last so we can access it first

class Nde {
    int data;
    Nde left, right;
    Nde(int data) {
        this.data = data;
        left = right = null;
    }
}
public class TUF {
    static ArrayList < Integer > preOrderTrav(Nde curr) {
        ArrayList < Integer > preOrder = new ArrayList < Integer > ();
        if (curr == null)
            return preOrder;

        Stack < Nde > s = new Stack < > ();
        s.push(curr);

        while (!s.isEmpty()) {
            Nde topNde = s.peek();
            preOrder.add(topNde.data);
            s.pop();
            if (topNde.right != null)
                s.push(topNde.right);
            if (topNde.left != null)
                s.push(topNde.left);
        }
        return preOrder;

    }


    public static void main(String args[]) {


        Nde root = new Nde(1);
        root.left = new Nde(2);
        root.right = new Nde(3);
        root.left.left = new Nde(4);
        root.left.right = new Nde(5);
        root.left.right.left = new Nde(8);
        root.right.left = new Nde(6);
        root.right.right = new Nde(7);
        root.right.right.left = new Nde(9);
        root.right.right.right = new Nde(10);




        ArrayList < Integer > preOrder = new ArrayList < > ();
        preOrder = preOrderTrav(root);

        System.out.print("The preOrder Traversal is : ");
        for (int i = 0; i < preOrder.size(); i++) {
            System.out.print(preOrder.get(i) + " ");
        }

    }
}*/
 