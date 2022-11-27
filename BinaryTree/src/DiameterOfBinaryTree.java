import java.util.ArrayList;
import java.util.List;
class Tree {
    int val;
    Tree left, right;
    Tree(int data) {
        this.val = data;
        left = right = null;
    }
}
public class DiameterOfBinaryTree {
	public static void main(String args[]) {


		Tree root = new Tree(1);
        root.left = new Tree(2);
        root.right = new Tree(3);
        root.left.left = new Tree(4);
        root.left.right = new Tree(5);
        root.left.right.left = new Tree(8);
        root.right.left = new Tree(6);
        root.right.right = new Tree(7);
        root.right.right.left = new Tree(9);
        root.right.right.right = new Tree(10);

        int preOrder = getDiameter(root);

        System.out.print("The Diameter  is : "+preOrder);
       
    }
	static int max;
	public static  int getDiameter(Tree root) {
		max=Integer.MIN_VALUE;
		height(root);
        return max;
    }
    static int height(Tree root){
        if(root==null){
            return 0;
        }
        int lh=height(root.left);
       
        int rh=height(root.right);
        max=Math.max(max, lh+rh);
        return 1+Math.max(lh,rh);
    }
}

/*
 * Striver code
 * public int diameterOfBinaryTree(TreeNode root) {
        int[] diameter = new int[1];
        height(root, diameter);
        return diameter[0];        
    }

    private int height(TreeNode node, int[] diameter) {
        if (node == null) {
            return 0;
        }
        int lh = height(node.left, diameter);
        int rh = height(node.right, diameter);
        diameter[0] = Math.max(diameter[0], lh + rh);
        return 1 + Math.max(lh, rh);
    }*/
