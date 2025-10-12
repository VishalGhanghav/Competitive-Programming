package SdeSheetBinarySearchTree.easy;

import SdeSheetBinaryTree.TreeNode;
import com.sun.source.tree.Tree;

public class MinMaxInBST {

    public static void main(String[] args) {
        MinMaxInBST obj = new MinMaxInBST();

        // Create BST manually using existing TreeNode class
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        // Search for a value
        int val = 2;
        TreeNode max = obj.getMax(root);

        TreeNode min = obj.getMin(root);
        System.out.println("max is: " + max.val + " min is : "+ min.val);
    }

    private TreeNode getMin(TreeNode root) {
        TreeNode curr = root;

        while(curr.left!=null) {
            curr = curr.left;
        }
        return curr;
    }

    private TreeNode getMax(TreeNode root) {
        TreeNode curr = root;

        while(curr.right!=null) {
            curr = curr.right;
        }
        return curr;
    }
}
