package SdeSheetBinaryTree.easy;


import java.util.ArrayList;
import java.util.List;

public class Inorder {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res=new ArrayList<>();
        traverse(root,res);
        return res;
    }

    public void traverse(TreeNode root, List<Integer> al){
        if(root==null){
            return;
        }
        traverse(root.left,al);
        al.add(root.val);
        traverse(root.right,al);
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
        Inorder inorder=new Inorder();
        List<Integer> res=inorder.inorderTraversal(root);
        System.out.println("inorder:"+res);

    }
}