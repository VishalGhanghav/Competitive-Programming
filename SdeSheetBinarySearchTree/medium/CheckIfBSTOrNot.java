package SdeSheetBinarySearchTree.medium;

import SdeSheetBinaryTree.TreeNode;
import java.util.*;

public class CheckIfBSTOrNot {

    private boolean checkIfBST(TreeNode node) {
        //Plan: For a node to be valid in BST.
        //on left it should be smaller than parent and on right greater than parent on right
        return isBST(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBST(TreeNode node, int lowerBound, int upperBound) {
        //if null its following bounds
        if(node == null) {
            return true;
        }
        if(node.val <= lowerBound || node.val >= upperBound) {
            return false;
        }

        //On left lb remains same but up will be the parent
        boolean left = isBST(node.left, lowerBound, node.val);
        //on right lb will have parents value and ub remains same
        boolean right = isBST(node.right, node.val, upperBound);

        return left && right;
    }

    // --- Utility function to print tree in Level Order ---
    private static void printLevelOrder(TreeNode root) {
        if(root == null) return;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                System.out.print(curr.val + " ");
                if(curr.left != null) q.add(curr.left);
                if(curr.right != null) q.add(curr.right);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Constructing a BST
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(4);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(6);
        root.right.right = new TreeNode(20);

        CheckIfBSTOrNot obj = new CheckIfBSTOrNot();

        System.out.println("Level Order of Tree:");
        printLevelOrder(root);

        System.out.println("\nIs the tree a valid BST? " + obj.checkIfBST(root));

        // Now modify to make it invalid
        root.right.left = new TreeNode(5);
        System.out.println("\nAfter adding invalid node (5 in right subtree):");
        System.out.println("\nIs the tree a valid BST? " + obj.checkIfBST(root));
    }
}
