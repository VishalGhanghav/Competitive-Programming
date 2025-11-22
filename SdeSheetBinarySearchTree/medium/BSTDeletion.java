package SdeSheetBinarySearchTree.medium;

import java.util.*;
import SdeSheetBinaryTree.TreeNode; // Imported as per instruction

public class BSTDeletion {

    // TC: O(H) - searching and finding rightMost node both take O(H)
    // SC: O(H) - due to recursion stack in findRightMost
    public TreeNode deleteNode(TreeNode root, int key) {
        //Plan: When deleting a node.First search based on key val.Based on that go left or right
        //Then once found in normal case go left and find right most and connect it to to right subtree
        //edge case:If root.left is null connect root.right directly and vice versa

        if (root == null) {
            return null;
        }

        if (root.val == key) {
            //Since either left or right gonna be new root.We pass just root not its left or right
            return helper(root);
        }
        TreeNode curr = root;
        while (curr != null) {
            if (key < curr.val) {
                //check left
                if (curr.left != null && curr.left.val == key) {
                    //if elmt found go left and find right most
                    curr.left = helper(curr.left);
                    break;
                } else {
                    curr = curr.left;
                }
            } else {
                //check right
                if (curr.right != null && curr.right.val == key) {
                    //if elmt found go left and find right most
                    curr.right = helper(curr.right);
                    break;
                } else {
                    curr = curr.right;
                }
            }
        }
        return root;
    }

    // TC: O(H) in worst case (traversing down for rightMost)
    // SC: O(H) due to stack space of findRightMost
    private TreeNode helper(TreeNode root) {
        //If left is null direct connect right and vice versa
        if (root.left == null) {
            return root.right;
        } else if (root.right == null) {
            return root.left;
        } else {

            //If we reach here ,means both left and right present
            TreeNode rightChild = root.right;
            TreeNode leftChild = root.left;
            TreeNode rightMost = findRightMost(leftChild);
            rightMost.right = rightChild;
            return root.left;
        }
    }

    // TC: O(H)
    // SC: O(H) - Recursive stack
    private TreeNode findRightMost(TreeNode root) {
        if (root.right == null) {
            return root;
        }
        return findRightMost(root.right);
    }

    // --- Helper Methods (Main & Printing) ---

    public static void main(String[] args) {
        BSTDeletion solution = new BSTDeletion();

        // Create a sample BST:
        //       5
        //     /   \
        //    3     6
        //   / \     \
        //  2   4     7
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);

        System.out.println("Original Tree (Level Order):");
        printLevelOrder(root);

        int keyToDelete = 3;
        System.out.println("\nDeleting Node: " + keyToDelete);

        // Execute User Logic
        root = solution.deleteNode(root, keyToDelete);

        System.out.println("Tree After Deletion (Level Order):");
        printLevelOrder(root);
    }

    // Helper to print Level Order Traversal
    private static void printLevelOrder(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                level.add(node.val);
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            System.out.println(level);
        }
    }
}
