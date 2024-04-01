package BinaryTree.src.BinarySearchTree;

import BinaryTree.src.BinarySearchTree.Node;


public class CeilOfBST {
	public static void main(String args[])
	{
		
		    Node root = new Node(10);
	        root.left = new Node(9);
	        root.right = new Node(12);
	        root.left.left = new Node(8);
	        root.right.left = new Node(11);
	        root.right.right = new Node(14);
		int key=13;
		int ceil=-1;
		while(root!=null) {
			if(root.val==key) {
				ceil=root.val;
				System.out.println(ceil);;
			}
			if(root.val<key) {
				root=root.right;
			}else {
				ceil=root.val;
				root=root.left;
			}
		}
		System.out.println(ceil);
	}
}
