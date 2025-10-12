package SdeSheetBinarySearchTree.medium;

import SdeSheetBinaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class KthSmallestInBST {
    public static void main(String[] args) {
        // Create BST manually using existing TreeNode class
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        System.out.println(getKthSmallestBrute(root,3));
        System.out.println(getKthSmallestOptimal(root,3));
    }

    private static int getKthSmallestBrute(TreeNode root, int k) {
        //Do inorder.It will give sorted return the kth element
        List<Integer> inorder = new ArrayList<>();
        getInorder(root,inorder);
        return inorder.get(k-1);
    }


    private static void getInorder(TreeNode root,List<Integer> list) {
        if(root == null) {
            return ;
        }
        getInorder(root.left,list);
        list.add(root.val);
        getInorder(root.right,list);
    }

    static int count =0;
    static int kthSmallest = -1;
    private static int getKthSmallestOptimal(TreeNode root, int k) {

        inorder(root,k);
        return kthSmallest;
    }

    private static void inorder(TreeNode root, int k) {
        //While doing inorder itself get the kth smallest
        if(root == null || count >= k) {
            return ;
        }
        getKthSmallestOptimal(root.left,k);

        count ++;
        if(count == k) {
            kthSmallest = root.val;
        }
        getKthSmallestOptimal(root.right,k);
    }
}
