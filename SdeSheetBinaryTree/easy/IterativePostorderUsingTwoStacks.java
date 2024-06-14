package SdeSheetBinaryTree.easy;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class IterativePostorderUsingTwoStacks {

    //Iterative
    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> s1=new Stack<>();
        Stack<TreeNode> s2=new Stack<>();
        List<Integer> postorder=new ArrayList<>();
        if(root==null){
            return postorder;
        }
        s1.add(root);
        //We will keep adding elements in s1 and pop and add in s2
        //when we have checked both left and right of that node
        while(!s1.isEmpty()){
            TreeNode node=s1.pop();
            if(node.left!=null){
                s1.add(node.left);
            }
            if(node.right!=null){
                s1.add(node.right);
            }
            s2.add(node);
        }
        while(!s2.isEmpty()){
            postorder.add(s2.pop().val);
        }
        return postorder;
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
        IterativePostorderUsingTwoStacks iterativePostorderUsingTwoStacks=new IterativePostorderUsingTwoStacks();
        List<Integer> res=iterativePostorderUsingTwoStacks.postorderTraversal(root);
        System.out.println("postorder:"+res);

    }
}