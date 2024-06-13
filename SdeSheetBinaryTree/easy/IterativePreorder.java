package SdeSheetBinaryTree.easy;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class IterativePreorder {
    //Iterative preorder
    public List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> stack=new Stack<>();
        List<Integer> res=new ArrayList<>();
        if(root==null){
            return res;
        }
        stack.add(root);
        while(!stack.isEmpty()){
            TreeNode node=stack.pop();
            //add the polled value in the list as we are doing preorder:root left right.So add root
            res.add(node.val);
            //stack goes last-in-first-out .So we will insert right first and then left
            //so that left stays on top
            if(node.right!=null){
                stack.add(node.right);
            }
            if(node.left!=null){
                stack.add(node.left);
            }
        }
        return res;
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
        IterativePreorder inorder=new IterativePreorder();
        List<Integer> res=inorder.preorderTraversal(root);
        System.out.println("preorder:"+res);

    }
}