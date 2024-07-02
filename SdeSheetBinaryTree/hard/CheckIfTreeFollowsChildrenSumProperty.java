package SdeSheetBinaryTree.hard;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CheckIfTreeFollowsChildrenSumProperty {
    static boolean flag;
    //Function to check whether all nodes of a tree have the value 
    //equal to the sum of their child nodes.
    public int isSumProperty(TreeNode root)
    {
        // add your code here
        flag=true;
        solve(root);
        return flag?1:0;

    }

    public static void solve(TreeNode root){
        //if leaf node ,it is following children sum property
        if(root==null || (root.left==null && root.right==null)){
            return;
        }
        int child=0;
        if(root.left!=null){
            child+=root.left.val;
        }
        if(root.right!=null){
            child+=root.right.val;
        }
        if(child!=root.val){

            flag=false;
        }
        solve(root.left);
        solve(root.right);
        return;

    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(6);
        root.right = new TreeNode(4);
        root.left.left=new TreeNode(3);
        root.left.right=new TreeNode(3);
        root.right.right=new TreeNode(1);
        root.right.left=new TreeNode(3);

        CheckIfTreeFollowsChildrenSumProperty levelOrderTraversal=new CheckIfTreeFollowsChildrenSumProperty();
        int res=levelOrderTraversal.isSumProperty(root);
        //if 1 follows if 0 not
        if (res == 1) {
            System.out.println("follows");
        } else {
            System.out.println("not follow");
        }
        /*
                    1
                2       3
             4    5   6    7
                8        9    10
         */

    }


}
