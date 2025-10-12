package SdeSheetBinarySearchTree.medium;

import SdeSheetBinaryTree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class ConstructBSTFromPreorder {

    static int ind; // global index to track position in preorder array

    //  Converts preorder array to BST
    private TreeNode convertPreorderToBST(int[] preorder) {
        ind = 0; // reset index for each run
        return createBST(preorder, Integer.MAX_VALUE);
    }

    private TreeNode createBST(int[] preorder, int upperBound) {
        // Stop if we've used all elements or current element exceeds bound
        /*We don't need to care about lower bound. When we construct the tree,
        we try to create left node first. If the condition fails (i.e.
        current number is greater than the parent node value), then we try to create
         the right node which automatically satisfies the condition, hence no lower bound is needed*/
        if (ind == preorder.length || preorder[ind] > upperBound) {
            return null;
        }

        // Create root node from current preorder element
        TreeNode root = new TreeNode(preorder[ind++]);

        // Left subtree ? elements smaller than root.val
        root.left = createBST(preorder, root.val);

        // Right subtree ? elements smaller than current upperBound but > root.val
        root.right = createBST(preorder, upperBound);

        return root;
    }

    // ? Helper: Print Level Order Traversal (BFS)
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
            System.out.println(); // print each level on new line
        }
    }

    // ? Main method to test
    public static void main(String[] args) {
        ConstructBSTFromPreorder obj = new ConstructBSTFromPreorder();

        int[] preorder = {8, 5, 1, 7, 10, 12};
        TreeNode root = obj.convertPreorderToBST(preorder);

        System.out.println("Level Order Traversal of Constructed BST:");
        obj.printLevelOrder(root);
    }
}
