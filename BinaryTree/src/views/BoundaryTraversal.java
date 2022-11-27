package views;

import java.util.ArrayList;

class Node{
	int val;
	Node left,right;
	
	Node(int data){
		this.val=data;
		left=right=null;
	}
}
public class BoundaryTraversal {
	public static ArrayList <Integer> boundary(Node root)
	{
		ArrayList<Integer> ans=new ArrayList<Integer>();
		if(root==null) {
			return ans;
		}
		//push root in res
		if(isLeaf(root)==false) {
			ans.add(root.val);
		}
		addLeftBoundary(root,ans);
		addLeafNodes(root,ans);
		addRightBoundary(root,ans);
		
		
		return ans;
	    
	}

	private static void addRightBoundary(Node root, ArrayList<Integer> ans) {
		Node curr=root.right;
		ArrayList < Integer > tmp = new ArrayList < Integer > ();
		while(curr!=null) {
			if(isLeaf(curr)==false){
				tmp.add(curr.val);
			}
			if(curr.right!=null) {
				curr=curr.right;
			}else {
				curr=curr.left;
			}
		}
		//right boundary in reverse
		int i;
        for (i = tmp.size() - 1; i >= 0; --i) {
            ans.add(tmp.get(i));
        }
		
	}

	private static void addLeafNodes(Node root, ArrayList<Integer> ans) {
		//preorder .if it's leaf add in ans.All nodes traversed
		
		if(isLeaf(root)) {
			ans.add(root.val);
			return;
		}
		if (root.left != null) addLeafNodes(root.left, ans);
        if (root.right != null) addLeafNodes(root.right, ans);
	}

	private static void addLeftBoundary(Node root, ArrayList<Integer> ans) {
		Node curr=root.left;
		while(curr!=null) {
			//start with root.left.if it's not leaf node then add in ans
			if(isLeaf(curr)==false){
				ans.add(curr.val);
			}
			//if left exist go to left if not then go right
			if(curr.left!=null) {
				curr=curr.left;
			}else {
				curr=curr.right;
			}
		}
		
	}

	private static boolean isLeaf(Node root) {
		return (root.left==null) && (root.right==null);
	}
	
	public static void main(String args[]) {

        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(3);
        root.left.left.right = new Node(4);
        root.left.left.right.left = new Node(5);
        root.left.left.right.right = new Node(6);
        root.right = new Node(7);
        root.right.right = new Node(8);
        root.right.right.left = new Node(9);
        root.right.right.left.left = new Node(10);
        root.right.right.left.right = new Node(11);

        ArrayList < Integer > boundaryTraversal;
        boundaryTraversal = boundary(root);

        System.out.println("The Boundary Traversal is : ");
        for (int i = 0; i < boundaryTraversal.size(); i++) {
            System.out.print(boundaryTraversal.get(i) + " ");
        }

    }
}
