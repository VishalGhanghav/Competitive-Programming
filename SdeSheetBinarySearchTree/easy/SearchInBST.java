package SdeSheetBinarySearchTree.easy;

import SdeSheetBinaryTree.TreeNode;

public class SearchInBST {

    // Recursive search in BST
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
        }
        if (val < root.val) {
            return searchBST(root.left, val);
        } else {
            return searchBST(root.right, val);
        }
    }

    // Main method to test
    public static void main(String[] args) {
        SearchInBST obj = new SearchInBST();

        // Create BST manually using existing TreeNode class
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        // Search for a value
        int val = 2;
        TreeNode result = obj.searchBST(root, val);

        if (result != null) {
            System.out.println("Found node with value: " + result.val);
        } else {
            System.out.println("Value not found in BST.");
        }
    }
}
