package BinarySearchTree;

public class SearchInBST {

	public static void main(String args[])
	{
		
		 Node root = new Node(10);
	        root.left = new Node(9);
	        root.right = new Node(13);
	        root.left.left = new Node(8);
	        root.right.left = new Node(11);
	        root.right.right = new Node(14);
		int key=11;
		while(root!=null && root.val!=key) {
			if(root.val<key) {
				root=root.right;
			}else {
				root=root.left;
			}
		}
		System.out.println(root.val);
	}
	
	}
