package SdeSheetBinaryTree.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RightSideViewOfBinaryTree {
    //Whenever we hit last node of level we add in result.
    public List<Integer> rightViewQueue(TreeNode root) {
        Queue<TreeNode> q=new LinkedList<>();

        q.add(root);
        List<Integer> rightSideView=new ArrayList<>();
        if(root==null){
            return rightSideView;
        }
        while(!q.isEmpty()){
            TreeNode node=null;
            int size=q.size();
            for(int i=0;i<size;i++){
                node=q.poll();
                if(node.left!=null){
                    q.add(node.left);
                }
                if(node.right!=null){
                    q.add(node.right);
                }
            }
            rightSideView.add(node.val);
        }
        return rightSideView;
    }

    public List<Integer> rightViewRecursion(TreeNode root) {
        List<Integer> rightView=new ArrayList<>();
        //Whenever we complete the level add that
        getRightView(root,0,rightView);
        return rightView;
    }

    private void getRightView(TreeNode node, int level, List<Integer> rightView) {
        if(node==null){
            return;
        }
        //Whenever we get upto rightmost element .List size would be same as that level
        if(level==rightView.size()){
            rightView.add(node.val);
        }
        getRightView(node.right,level+1,rightView);
        getRightView(node.left,level+1,rightView);
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

        List<Integer> res=new RightSideViewOfBinaryTree().rightViewQueue(root);
        System.out.println(res);
        List<Integer> res2=new RightSideViewOfBinaryTree().rightViewRecursion(root);
        System.out.println(res2);
    }

}
