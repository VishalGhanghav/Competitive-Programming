

import java.util.*;

class Node {
    int val;
    Node left, right;
    Node(int data) {
        this.val = data;
        left = right = null;
    }
}
public class PreOrderTraversal {
	private static List<Integer> ans = new ArrayList<Integer>();
    public static List<Integer> preorderTraversal(Node root) {
    	
        if(root == null) 
            return ans;
        ans.add(root.val);
        ans = preorderTraversal(root.left);
        ans = preorderTraversal(root.right);
        return ans;
    }
   


    public static void main(String args[]) {


        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.left.right.left = new Node(8);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.right.right.left = new Node(9);
        root.right.right.right = new Node(10);




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
In stack we push left node last so we can access it first

class Node {
    int data;
    Node left, right;
    Node(int data) {
        this.data = data;
        left = right = null;
    }
}
public class TUF {
    static ArrayList < Integer > preOrderTrav(Node curr) {
        ArrayList < Integer > preOrder = new ArrayList < Integer > ();
        if (curr == null)
            return preOrder;

        Stack < Node > s = new Stack < > ();
        s.push(curr);

        while (!s.isEmpty()) {
            Node topNode = s.peek();
            preOrder.add(topNode.data);
            s.pop();
            if (topNode.right != null)
                s.push(topNode.right);
            if (topNode.left != null)
                s.push(topNode.left);
        }
        return preOrder;

    }


    public static void main(String args[]) {


        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.left.right.left = new Node(8);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.right.right.left = new Node(9);
        root.right.right.right = new Node(10);




        ArrayList < Integer > preOrder = new ArrayList < > ();
        preOrder = preOrderTrav(root);

        System.out.print("The preOrder Traversal is : ");
        for (int i = 0; i < preOrder.size(); i++) {
            System.out.print(preOrder.get(i) + " ");
        }

    }
}*/
 