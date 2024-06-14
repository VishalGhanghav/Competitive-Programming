package SdeSheetBinaryTree.easy;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class IterativePostorderUsingOneStack {

    //Iterative
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> postorder=new ArrayList<>();
        if(root==null)
            return postorder;
        Stack<TreeNode> st=new Stack<>();
        st.push(root);
        while(!st.empty())
        {
            root=st.peek();
            st.pop();
            postorder.add(root.val);
            if(root.left!=null)
                st.push(root.left);
            if(root.right!=null)
                st.push(root.right);
        }
        Collections.reverse(postorder);
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
        IterativePostorderUsingOneStack iterativePostorderUsingOneStack=new IterativePostorderUsingOneStack();
        List<Integer> res=iterativePostorderUsingOneStack.postorderTraversal(root);
        System.out.println("postorder:"+res);

    }
}