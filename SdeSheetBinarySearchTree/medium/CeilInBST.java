package SdeSheetBinarySearchTree.medium;

import SdeSheetBinaryTree.TreeNode;

public class CeilInBST {
    private int getCeilIterative(TreeNode root, int key) {
        int ans = -1;
        while (root != null) {
            if (root.val == key) {
                // Exact match found ? floor is key itself
                return root.val;
            } else if (root.val > key) {
                // root.val > key ? potential ceil
                ans = root.val;
                //see if smaller ceil possible
                root = root.left;
            } else {
                ans = root.val;
                root = root.right; // try to find closer floor
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        CeilInBST obj = new CeilInBST();

        // Create BST manually using existing TreeNode class
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        // Search for a value
        int val = 6;
        int result = obj.getCeilIterative(root, val);
        System.out.println("floor is : " + result);

    }
}
