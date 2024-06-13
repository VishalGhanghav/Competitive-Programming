package SdeSheetBinaryTree.easy;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class IterativeInorder {

    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack=new Stack<>();
        List<Integer> res=new ArrayList<>();
        TreeNode node=root;
        while(true){
            //If node not null add in stack and move left like how recursion moves
            if(node!=null){
                stack.push(node);
                node=node.left;
            }else{
                //If we see that stack is empty .it means
                if(stack.isEmpty()){
                    break;
                }
                //when we find null ,we just add it in inorder and move right
                node=stack.pop();
                res.add(node.val);
                node=node.right;

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
        IterativeInorder inorder=new IterativeInorder();
        List<Integer> res=inorder.inorderTraversal(root);
        System.out.println("inorder:"+res);

    }
}