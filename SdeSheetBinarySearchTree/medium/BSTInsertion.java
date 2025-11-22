package SdeSheetBinarySearchTree.medium;

import java.util.*;
import SdeSheetBinaryTree.TreeNode; // Imported as per instruction

public class BSTInsertion {

    // TC: O(H) - where H is the height of the tree
    // SC: O(1) - iterative approach uses constant extra space
    public TreeNode insertIntoBST(TreeNode root, int val) {
        //Plan:
        //I will go left or right based on the val.Then based on that place the new node on leaf
        if(root == null) {
            return new TreeNode(val);
        }

        TreeNode curr = root;
        while(true) {
            //if val > root then go right
            if(val > curr.val){
                if(curr.right != null) {
                    curr = curr.right;
                } else {
                    //If we reach here.We know val is greater and we reached leaf.
                    //So create node
                    curr.right = new TreeNode(val);
                    break;
                }
            } else {
                //val< curr.val so left
                if(curr.left != null) {
                    curr = curr.left;
                } else {
                    //If we reach here.We know val is less and we reached leaf.
                    //So create node
                    curr.left = new TreeNode(val);
                    break;
                }
            }
        }
        return root;
    }

    // --- Helper Methods (Main & Printing) ---

    public static void main(String[] args) {
        BSTInsertion solution = new BSTInsertion();

        // Create a sample BST:
        //       4
        //     /   \
        //    2     7
        //   / \
        //  1   3
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        System.out.println("Original Tree (Level Order):");
        printLevelOrder(root);

        int valToInsert = 5;
        System.out.println("\nInserting value: " + valToInsert);

        // Execute User Logic
        solution.insertIntoBST(root, valToInsert);

        System.out.println("Tree After Insertion (Level Order):");
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
