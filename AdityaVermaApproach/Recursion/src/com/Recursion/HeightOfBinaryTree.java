package com.Recursion;

 class Node{
	int value;
	Node left;
	Node right;
	
	Node(int val){
		this.value=val;
	}
}

public class HeightOfBinaryTree {
	
	
	Node root;
	
	public static void main(String args[]){
	Node root=new Node(1);
	root.left=new Node(2);
	root.right=new Node(3);
	root.left.left=new Node(4);
	
	System.out.println("Height of Binary Tree:"+height(root));
		
	}

	private static int height(Node root) {
		if(root==null) {
			return 0;
		}
		int lh=height(root.left);
		int rh=height(root.right);
		return 1+Math.max(lh, rh);
	}
}


 

