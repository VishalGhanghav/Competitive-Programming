package BinaryTree.src;

import java.util.ArrayList;
import java.util.List;

public class HeightOfBinaryTree {
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

        int preOrder = HeightOfBinaryTree(root);

        System.out.print("The Height  is : "+preOrder);
       
    }

	private static int HeightOfBinaryTree(Nd root) {
		if(root==null) {
			return 0;
		}
		int lh=HeightOfBinaryTree(root.left);
		
		int rh=HeightOfBinaryTree(root.right);
		return 1+Math.max(lh, rh);
	}
}
