package views;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RightViewOfBinaryTree {

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
        /*
          		1
        	2		3
        4	   5		7
            6
         */
        System.out.println("The right view Traversal is : ");
        for (int i = 0; i < boundaryTraversal.size(); i++) {
            System.out.print(boundaryTraversal.get(i) + " ");
        }

	}

	private static List<Integer> leftView(Node root) {
		
		List<Integer> al=new ArrayList<Integer>();
		//for recursive
		//leftSideView(root,0,al);
		
		//for level wise
		Queue<Node> q=new LinkedList<>();
		q.offer(root);
		//if root is null, node.val in future gives null pointer exception
				if(root==null){
				    return al;
				}
		while(!q.isEmpty()) {
			int size=q.size();
			Node node=null;
			for(int i=0;i<size;i++) {
				node=q.poll();
				if(node.left!=null) {
					q.offer(node.left);
				}
				if(node.right!=null) {
					q.offer(node.right);
				}
				
			}
			al.add(node.val);
			//For each level ,first node is left view
		
		}
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
