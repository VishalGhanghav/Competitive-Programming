package SdeSheetBinarySearchTree.medium;

import SdeSheetBinaryTree.TreeNode;
import java.util.*;

public class LcaInBST {

    private TreeNode getLca(TreeNode root, TreeNode left, TreeNode right) {
        if (root == null) {
            return null;
        }

        //Plan: plan is to return the LCA.
        //If both on smaller than root go left.If both greater go right
        //If there's a split it means we found so return that node
        if (root.val > left.val && root.val > right.val) {
            return getLca(root.left, left, right);
        } else if (root.val < left.val && root.val < right.val) {
            return getLca(root.right, left, right);
        } else {
            return root;
        }
    }

    public static void main(String[] args) {
        // Construct a BST manually
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(4);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(6);
        root.right.right = new TreeNode(20);

        LcaInBST obj = new LcaInBST();

        TreeNode left = root.left.left;   // 2
        TreeNode right = root.left.right; // 6

        TreeNode lca = obj.getLca(root, left, right);
        System.out.println("\nLCA of " + left.val + " and " + right.val + " = " + (lca != null ? lca.val : "null"));

    }
}
