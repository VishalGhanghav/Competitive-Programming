package SdeSheetBinarySearchTree.medium;

import SdeSheetBinaryTree.TreeNode;
import java.util.*;

public class ConvertSortedArrayToBST {

    //  Converts a sorted array to a balanced BST
    private TreeNode convertToBST(int[] arr) {
        // In sorted array: mid = root, left = smaller elements, right = larger elements
        return createBST(arr, 0, arr.length - 1);
    }

    private TreeNode createBST(int[] arr, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(arr[mid]);
        root.left = createBST(arr, start, mid - 1);
        root.right = createBST(arr, mid + 1, end);
        return root;
    }

    //  Helper: Print Level Order Traversal (BFS)
    private void printLevelOrder(TreeNode root) {
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                System.out.print(node.val + " ");
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            System.out.println(); // New line for each level
        }
    }

    //  Main method to test
    public static void main(String[] args) {
        ConvertSortedArrayToBST obj = new ConvertSortedArrayToBST();

        int[] sorted = {1, 2, 5, 7, 9};
        TreeNode bstRoot = obj.convertToBST(sorted);

        System.out.println("Level Order Traversal of BST:");
        obj.printLevelOrder(bstRoot);
    }
}
