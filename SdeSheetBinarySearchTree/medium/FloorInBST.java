package SdeSheetBinarySearchTree.medium;

import SdeSheetBinarySearchTree.easy.SearchInBST;
import SdeSheetBinaryTree.TreeNode;

public class FloorInBST {

    private int getFloor(TreeNode root, int key) {
        if (root == null) {
            return -1;
        }

        // If key equals root's value, that's the exact floor
        if (root.val == key) {
            return root.val;
        }

        // If root is greater than key, floor must lie in left subtree
        if (root.val > key) {
            return getFloor(root.left, key);
        }

        // Otherwise, root < key ? root is a potential floor
        int rightFloor = getFloor(root.right, key);
        return (rightFloor != -1 && rightFloor <= key) ? rightFloor : root.val;
    }

    private int getFloorIerative(TreeNode root, int key) {
        int ans = -1;
        while (root != null) {
            if (root.val == key) {
                // Exact match found ? floor is key itself
                return root.val;
            } else if (root.val > key) {
                // Go left to find smaller values
                root = root.left;
            } else {
                // root.val < key ? potential floor
                ans = root.val;
                root = root.right; // try to find closer floor
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        FloorInBST obj = new FloorInBST();

        // Create BST manually using existing TreeNode class
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        // Search for a value
        int val = 6;
        int result = obj.getFloorIerative(root, val);
        System.out.println("floor is : " + result);

    }
}
