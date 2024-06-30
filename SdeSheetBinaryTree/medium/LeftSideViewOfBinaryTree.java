package SdeSheetBinaryTree.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LeftSideViewOfBinaryTree {
    //Whenever we hit last node of level we add in result.
    public List<Integer> leftViewQueue(TreeNode root) {
        Queue<TreeNode> q=new LinkedList<>();

        q.add(root);
        List<Integer> leftSideView=new ArrayList<>();
        if(root==null){
            return leftSideView;
        }
        while(!q.isEmpty()){
            TreeNode node=null;
            int size=q.size();
            for(int i=0;i<size;i++){
                node=q.poll();
                //Always the first element in level is left view
                if(i==0){
                    leftSideView.add(node.val);
                }
                if(node.left!=null){
                    q.add(node.left);
                }
                if(node.right!=null){
                    q.add(node.right);
                }
            }
            
        }
        return leftSideView;
    }

    public List<Integer> leftViewRecursion(TreeNode root) {
        List<Integer> leftView=new ArrayList<>();
        //Whenever we complete the level add that
        getLeftView(root,0,leftView);
        return leftView;
    }

    private void getLeftView(TreeNode node, int level, List<Integer> leftView) {
        if(node==null){
            return;
        }
        //Whenever we get upto rightmost element .List size would be same as that level
        if(level==leftView.size()){
            leftView.add(node.val);
        }
        getLeftView(node.left,level+1,leftView);
        getLeftView(node.right,level+1,leftView);
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

        List<Integer> res=new LeftSideViewOfBinaryTree().leftViewQueue(root);
        System.out.println(res);
        List<Integer> res2=new LeftSideViewOfBinaryTree().leftViewRecursion(root);
        System.out.println(res2);
    }

}
