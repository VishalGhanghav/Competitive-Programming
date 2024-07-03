package SdeSheetBinaryTree.hard;

import java.util.*;

public class NoOfNodesInCompleteBTLogN {
    private int getNoOfNodes(TreeNode root) {
        if(root==null){
            //total nodes=0
            return 0;
        }
        //Now traverse left and right and get their heights
        int lh=getLeftHeight(root);
        int rh=getRightHeight(root);
        //If lh==rh.It is a full binary tree and nodes=2^ht-1
        if(lh==rh){
            //We can use power function Math.pow(2,ht) or shift bits left 2<<ht-1
            return (2<<lh)-1;
        }else{
            return getNoOfNodes(root.left)+getNoOfNodes(root.right)+1;
        }
    }

    public int getLeftHeight(TreeNode root){
        TreeNode curr=root;
        int lh=0;
        //We are not going recursively because we know the format of tree is complete BinaryTree
        while(curr.left!=null){
            lh++;
            curr=curr.left;
        }
        return lh;
    }

    public int getRightHeight(TreeNode root){
        TreeNode curr=root;
        int rh=0;
        while(curr.right!=null){
            rh++;
            curr=curr.right;
        }
        return rh;
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.left.left.left = new TreeNode(8);

        NoOfNodesInCompleteBTLogN levelOrderTraversal=new NoOfNodesInCompleteBTLogN();
        int res=levelOrderTraversal.getNoOfNodes(root);
        //ans should be 8 and 3.As its distance 2 from node 2
        System.out.println(res);
        /*
                    1
                2       3
             4    5   6    7
           8
         */

    }


}
