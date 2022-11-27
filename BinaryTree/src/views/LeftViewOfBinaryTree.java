package views;

import java.util.ArrayList;
import java.util.List;

public class LeftViewOfBinaryTree {

	public static void main(String[] args) {
		Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.left.right.left = new Node(6);
        root.right.right = new Node(7);

        List < Integer > boundaryTraversal;
        boundaryTraversal = leftView(root);

        System.out.println("The left view Traversal is : ");
        for (int i = 0; i < boundaryTraversal.size(); i++) {
            System.out.print(boundaryTraversal.get(i) + " ");
        }

	}

	private static List<Integer> leftView(Node root) {
		
		List<Integer> al=new ArrayList<Integer>();
		//al=leftSideView(root,0,al);or
		leftSideView(root,0,al);
		return al;
	}

	private static void leftSideView(Node root, int level, List<Integer> al) {
		if(root==null) {
			return;
		}
		if(level==al.size()) {
			al.add(root.val);
		}
		leftSideView(root.left, level+1, al);
		leftSideView(root.right, level+1, al);
	}

}
