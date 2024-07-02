package SdeSheetBinaryTree.hard;

import com.sun.source.tree.Tree;

import java.util.ArrayList;

public class LowestCommonAncestor {

    private TreeNode lca(TreeNode root,int p1,int p2) {
        //if root is null we return null
        //if p/q found we return it
        if(root==null || root.val==p1 || root.val==p2){
            return root;
        }
        //do traversal
        TreeNode left=lca(root.left,p1,p2);
        TreeNode right=lca(root.right,p1,p2);

        //while coming back/backtracking below logic is executed.
        //if we found null on left take right and vice versa.If both null eventually it will return null

        if(left==null){
            return right;
        }else if(right==null){
            return left;
        }else{
            //both left and right are not null.We found our lca
            return root;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.left.right.left = new TreeNode(8);
        root.right.right.left = new TreeNode(9);
        root.right.right.right = new TreeNode(10);
        LowestCommonAncestor levelOrderTraversal=new LowestCommonAncestor();
        TreeNode res=levelOrderTraversal.lca(root,6,10);
        System.out.println(res.val);
        /*
                    1
                2       3
             4    5   6    7
                8        9    10
         */

    }




}
