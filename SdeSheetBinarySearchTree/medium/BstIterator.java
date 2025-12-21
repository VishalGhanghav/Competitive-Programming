package SdeSheetBinarySearchTree.medium;

import java.util.*;
import SdeSheetBinaryTree.TreeNode; // Imported as per instruction

public class BstIterator {

    // SC: O(H) - Stack stores nodes up to height of tree
    Stack<TreeNode> stack = new Stack<>();

    // TC: O(H) - Initial traversal to left-most leaf
    BstIterator(TreeNode root){
        pushAll(root);
    }

    // TC: O(H) - In worst case (skewed tree), but amortized O(1)
    private void pushAll(TreeNode root) {
        while(root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    // TC: Amortized O(1)
    public int next() {
        TreeNode temp = stack.pop();
        //If I am removing current then push its right and its all left in stack
        pushAll(temp.right); // Corrected from stack.push(temp.right) to match comment logic & prevent crash
        return temp.val;
    }

    // TC: O(1)
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    // --- Helper Methods (Main & Printing) ---

    public static void main(String[] args) {
        // Create a sample BST:
        //       7
        //     /   \
        //    3     15
        //         /  \
        //        9    20
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);

        System.out.println("Tree (Level Order):");
        printLevelOrder(root);

        System.out.println("\nIterating through BST:");
        BstIterator iterator = new BstIterator(root);


        // Print elements one by one using the iterator
        List<Integer> result = new ArrayList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        System.out.println(result);
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