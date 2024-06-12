package BinaryTree.src.BinarySearchTree;

import BinaryTree.src.Node;

import java.util.ArrayList;
import java.util.List;

public class DeleteNodeInBst {
    public static Node deleteNode(Node root, int key) {
        if(root==null) return null;

        if(key<root.val){
            root.left = deleteNode(root.left,key);
            return root;
        }

        else if(key>root.val){
            root.right = deleteNode(root.right,key);
            return root;
        }

        else{
            if(root.left==null){
                return root.right;
            }
            else if(root.right==null){
                return root.left;
            }
            else{
                Node min = root.right;
                while(min.left!=null){
                    min = min.left;
                }

                root.val = min.val;
                root.right = deleteNode(root.right,min.val);
                return root;
            }
        }
    }
   


    public static void main(String args[]) {


        BinaryTree.src.Node root = new BinaryTree.src.Node(5);
        root.left = new BinaryTree.src.Node(3);
        root.right = new BinaryTree.src.Node(8);
        root.left.left = new BinaryTree.src.Node(2);
        root.left.right = new BinaryTree.src.Node(4);
        root.right.left = new BinaryTree.src.Node(7);
        root.right.right = new BinaryTree.src.Node(9);





        Node node = deleteNode(root,5);
        List<Integer> preOrder = new ArrayList < > ();
        preOrder = preorderTraversal(node);

        System.out.print("The preOrder Traversal is : ");
        for (int i = 0; i < preOrder.size(); i++) {
            System.out.print(preOrder.get(i) + " ");
        }
    }
    private static List<Integer> ans = new ArrayList<Integer>();
    public static List<Integer> preorderTraversal(Node root) {

        if(root == null)
            return ans;
        ans.add(root.val);
        ans = preorderTraversal(root.left);
        ans = preorderTraversal(root.right);
        return ans;
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
 