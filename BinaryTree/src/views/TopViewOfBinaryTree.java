package BinaryTree.src.views;

import BinaryTree.src.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.TreeMap;

public class TopViewOfBinaryTree {
	public static ArrayList<Integer> topView(Node root)
	{
		ArrayList<Integer> ans=new ArrayList<Integer>();
		
		if(root==null) {
			return ans;
		}
		Map<Integer,Integer> map=new TreeMap<>();
		Queue<Pair> q=new LinkedList<>();
		q.add(new Pair(root,0));//node,line
		while(!q.isEmpty()) {
			Pair pair=q.poll();
			int line=pair.line;
			Node temp=pair.node;
			if(map.get(line)==null) {
				map.put(line, temp.val);
			}
			if(temp.left!=null) {
				q.add(new Pair(temp.left,line-1));
			}
			if(temp.right!=null) {
				q.add(new Pair(temp.right,line+1));
			}
		}
		
		for(Map.Entry<Integer,Integer> entry : map.entrySet()) {
			ans.add(entry.getValue());
		}
		
		
		return ans;
	    
	}

	
	
	public static void main(String args[]) {

        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(3);
        root.left.right = new Node(4);
        root.right = new Node(7);
        root.right.right = new Node(8);

        ArrayList < Integer > boundaryTraversal;
        boundaryTraversal = topView(root);

        System.out.println("The top view Traversal is : ");
        for (int i = 0; i < boundaryTraversal.size(); i++) {
            System.out.print(boundaryTraversal.get(i) + " ");
        }

    }
}
