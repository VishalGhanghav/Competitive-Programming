

import java.util.*;

 class NodeInv {
    int val;
    NodeInv left, right;
    NodeInv(int data) {
        this.val = data;
        left = right = null;
    }
}
public class InvertBinaryTree {
	private static List<Integer> ans = new ArrayList<Integer>();
    public static List<Integer> preorderTraversal(NodeInv root) {
    	
        if(root == null) 
            return ans;
        System.out.println(root.val);
        preorderTraversal(root.left);
        preorderTraversal(root.right);
        return ans;
    }
   


    public static void main(String args[]) {


    	NodeInv root = new NodeInv(1);
        root.left = new NodeInv(2);
        root.right = new NodeInv(3);
        root.left.left = new NodeInv(4);
        root.left.right = new NodeInv(5);
        root.right.left = new NodeInv(6);
        root.right.right = new NodeInv(7);
/*			
		1
	2		3
 4	 5	 6	  7

 */



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
In stack we push left NodeInv last so we can access it first

class NodeInv {
    int data;
    NodeInv left, right;
    NodeInv(int data) {
        this.data = data;
        left = right = null;
    }
}
public class TUF {
    static ArrayList < Integer > preOrderTrav(NodeInv curr) {
        ArrayList < Integer > preOrder = new ArrayList < Integer > ();
        if (curr == null)
            return preOrder;

        Stack < NodeInv > s = new Stack < > ();
        s.push(curr);

        while (!s.isEmpty()) {
            NodeInv topNodeInv = s.peek();
            preOrder.add(topNodeInv.data);
            s.pop();
            if (topNodeInv.right != null)
                s.push(topNodeInv.right);
            if (topNodeInv.left != null)
                s.push(topNodeInv.left);
        }
        return preOrder;

    }


    public static void main(String args[]) {


        NodeInv root = new NodeInv(1);
        root.left = new NodeInv(2);
        root.right = new NodeInv(3);
        root.left.left = new NodeInv(4);
        root.left.right = new NodeInv(5);
        root.left.right.left = new NodeInv(8);
        root.right.left = new NodeInv(6);
        root.right.right = new NodeInv(7);
        root.right.right.left = new NodeInv(9);
        root.right.right.right = new NodeInv(10);




        ArrayList < Integer > preOrder = new ArrayList < > ();
        preOrder = preOrderTrav(root);

        System.out.print("The preOrder Traversal is : ");
        for (int i = 0; i < preOrder.size(); i++) {
            System.out.print(preOrder.get(i) + " ");
        }

    }
}*/
 