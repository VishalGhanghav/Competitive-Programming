package BinaryTree.src;

import BinaryTree.src.Nd;

import java.util.ArrayList;
import java.util.List;

public class isBalancedTree {
	public static void main(String args[]) {


        Nd root = new Nd(1);
        root.left = new Nd(2);
        root.right = new Nd(3);
        root.left.left = new Nd(4);
        root.left.right = new Nd(5);
        root.left.right.left = new Nd(8);
        root.right.left = new Nd(6);
        root.right.right = new Nd(7);
        root.right.right.left = new Nd(9);
        root.right.right.right = new Nd(10);

        Boolean preOrder = isBalanced(root);

        System.out.print("The Balanced  is : "+preOrder);
       
    }

	public static boolean isBalanced(Nd root) {
        return height(root)!=-1;
    }
    static int height(Nd root){
        if(root==null){
            return 0;
        }
        int lh=height(root.left);
        if(lh==-1){
            return -1;
        }
        int rh=height(root.right);
        if(rh==-1){
            return -1;
        }
        if(Math.abs(lh-rh)>1){
            return -1;
        }
        return 1+Math.max(lh,rh);
    }
}
