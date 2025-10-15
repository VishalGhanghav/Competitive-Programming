package SdeSheetBinarySearchTree.medium;

import SdeSheetBinaryTree.TreeNode;

public class InorderPredecessorSuccessorInBST {

    private TreeNode getPredecessor(TreeNode root,TreeNode key) {
        TreeNode curr = root;
        //Plan :I need to find inorder predecessor of key(just before key)
        TreeNode preDecessor = null;
        //If current value is less than key then it can be predecessor
        //But we can try to find smaller predecessor
        while(curr!=null) {
            if(curr.val < key.val) {
                //possible predecssor
                preDecessor = curr;
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }
        return preDecessor;
    }
    public static void main(String[] args) {
        // Construct a BST manually
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(4);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(6);
        root.right.right = new TreeNode(20);

        InorderPredecessorSuccessorInBST obj = new InorderPredecessorSuccessorInBST();

        TreeNode node = root.right;

        TreeNode predecessor = obj.getPredecessor(root, node);

        TreeNode succesor = obj.getSuccessor(root, node);
        /*
              8
           4    10
        2   6      20
         */

        System.out.println("\nInorder predecessor is " +  " = " + predecessor.val);
        System.out.println("\nInorder successor is " +  " = " + succesor.val);

    }

    private TreeNode getSuccessor(TreeNode root, TreeNode key) {
        TreeNode curr = root;
        //Plan :I need to find inorder successor of key(just after key)
        TreeNode successor = null;
        //If current value is less than key I wil go right
        //If it becomes greater than key it can be potential ans.We can find smaller successor by going left
        while(curr!=null) {
            if(curr.val <= key.val) {
                curr = curr.right;
            } else {
                //possible successfor
                successor = curr;
                curr = curr.left;
            }
        }
        return successor;
    }
}
