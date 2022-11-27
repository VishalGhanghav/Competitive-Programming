package views;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class BottomViewOfBinaryTree {

	public static void main(String[] args) {
		Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(3);
        root.left.right = new Node(4);
        root.right = new Node(7);
        root.right.right = new Node(8);

        ArrayList < Integer > boundaryTraversal;
        boundaryTraversal = bottomView(root);

        System.out.println("The bottom view Traversal is : ");
        for (int i = 0; i < boundaryTraversal.size(); i++) {
            System.out.print(boundaryTraversal.get(i) + " ");
        }

	}

	private static ArrayList<Integer> bottomView(Node root) {
		ArrayList<Integer> al=new ArrayList<Integer>();
		Queue<Pair> queue=new LinkedList<>();
		TreeMap<Integer,Integer> map=new TreeMap<>();
		if(root==null) {
			return al;
		}
		queue.add(new Pair(root,0));
		while(!queue.isEmpty()) {
			Pair pair=queue.poll();
			int line=pair.line;
			Node tempNode=pair.node;
			map.put(line, tempNode.val);
			
			if(tempNode.left!=null) {
				queue.add(new Pair(tempNode.left,line-1));
			}
			
			if(tempNode.right!=null) {
				queue.add(new Pair(tempNode.right,line+1));
			}
		}
		
		for(Map.Entry<Integer, Integer> entry:map.entrySet()) {
			al.add(entry.getValue());
		}
		return al;
	}

}
