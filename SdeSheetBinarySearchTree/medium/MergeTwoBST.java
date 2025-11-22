import SdeSheetBinaryTree.TreeNode;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

public class MergeTwoBST {

    // TC: O(N + M) - where N and M are nodes in root1 and root2 respectively
    // SC: O(N + M) - to store the inorder lists and final merged list
    private List<Integer> mergeBSTs(TreeNode root1, TreeNode root2) {
        List<Integer> mergedBst = new ArrayList<>();
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        inorder(root1, l1);
        inorder(root2, l2);

        int i=0,j=0;
        while(i< l1.size() && j<l2.size()) {
            if(l1.get(i) < l2.get(j)) {
                mergedBst.add(l1.get(i));
                i++;
            } else {
                mergedBst.add(l2.get(j));
                j++;
            }
        }

        //If l1 not ended
        while (i< l1.size()) {
            mergedBst.add(l1.get(i));
            i++;
        }

        while(j< l2.size()) {
            mergedBst.add(l2.get(j));
            j++;
        }
        return mergedBst;
    }

    // TC: O(N)
    // SC: O(H) - Recursion stack
    private void inorder(TreeNode root, List<Integer> list) {
        if(root == null) {
            return;
        }
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    // --- Helper Methods (Main & Printing) ---

    public static void main(String[] args) {
        MergeTwoBST sol = new MergeTwoBST();

        // --- Test Case 1 ---
        System.out.println("--- Test Case 1 ---");
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(5);

        TreeNode root2 = new TreeNode(4);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(6);

        System.out.print("Tree 1: "); printLevelOrder(root1);
        System.out.print("Tree 2: "); printLevelOrder(root2);

        List<Integer> result1 = sol.mergeBSTs(root1, root2);
        System.out.println("Merged Sorted List: " + result1);

        // --- Test Case 2 ---
        System.out.println("\n--- Test Case 2 ---");
        // root1 = [5, 3, 6, 2, 4]
        TreeNode t2_root1 = new TreeNode(5);
        t2_root1.left = new TreeNode(3);
        t2_root1.right = new TreeNode(6);
        t2_root1.left.left = new TreeNode(2);
        t2_root1.left.right = new TreeNode(4);

        // root2 = [2, 1, 3, null, null, null, 7, 6]
        TreeNode t2_root2 = new TreeNode(2);
        t2_root2.left = new TreeNode(1);
        t2_root2.right = new TreeNode(3);
        // 1's children are null
        // 3's left is null, right is 7
        t2_root2.right.right = new TreeNode(7);
        // 7's left is 6
        t2_root2.right.right.left = new TreeNode(6);

        System.out.print("Tree 1: "); printLevelOrder(t2_root1);
        System.out.print("Tree 2: "); printLevelOrder(t2_root2);

        List<Integer> result2 = sol.mergeBSTs(t2_root1, t2_root2);
        System.out.println("Merged Sorted List: " + result2);
    }

    // Helper to print Level Order Traversal (to verify input trees)
    private static void printLevelOrder(TreeNode root) {
        if (root == null) {
            System.out.println("[]");
            return;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        List<Integer> level = new ArrayList<>();
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node != null) {
                level.add(node.val);
                q.offer(node.left);
                q.offer(node.right);
            } else {
                level.add(null);
            }
        }
        // Clean up trailing nulls for cleaner display
        int i = level.size() - 1;
        while (i >= 0 && level.get(i) == null) {
            level.remove(i);
            i--;
        }
        System.out.println(level);
    }
}