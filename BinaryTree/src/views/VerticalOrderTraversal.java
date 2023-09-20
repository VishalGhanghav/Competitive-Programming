package views;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

class vNode{
	int val;
	vNode left,right;
	
	vNode(int data){
		this.val=data;
		left=right=null;
	}
}

class Tuple{
	vNode node;
	int vert;
	int level;
	public Tuple(vNode node, int vert, int level) {
	
		this.node = node;
		this.vert = vert;
		this.level = level;
	}
	
}
public class VerticalOrderTraversal {
	public static List<List<Integer>> findVertical(vNode root)
	{
		List<List<Integer>> ans=new ArrayList<>();
		TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> parentMap=new TreeMap<>();
		Queue<Tuple> q=new LinkedList<>();
		q.offer(new Tuple(root, 0, 0));
		while(!q.isEmpty()) {
			Tuple tuple=q.poll();//I got the tuple
			vNode node=tuple.node;
			int vert=tuple.vert;
			int level=tuple.level;
			//if vertical already present
			if(!parentMap.containsKey(vert)) {
				parentMap.put(vert, new TreeMap<>());//didnt add values of inner map.will add later
			}
			//If level already present
			if(!parentMap.get(vert).containsKey(level)) {
				parentMap.get(vert).put(level, new PriorityQueue<>());
			}
			//adding piority queue as well
			parentMap.get(vert).get(level).offer(node.val);
			
			//left->vert-1,level+1
			if(node.left!=null) {
				q.offer(new Tuple(node.left, vert-1, level+1));
			}
			if(node.right!=null) {
				q.offer(new Tuple(node.right, vert+1, level+1));
			}
			
			
		}
		System.out.println(parentMap);
		//parentMap:{-2={2=[4]}, -1={1=[2]}, 0={0=[1], 2=[9, 10]}, 1={1=[3]}, 2={2=[10]}}
		for(TreeMap<Integer, PriorityQueue<Integer>> pm:parentMap.values()) {
			ans.add(new ArrayList<Integer>());
			for(PriorityQueue<Integer> cm:pm.values()) {
				//for level 0 got 1,9,10
				System.out.println(cm);
				while(!cm.isEmpty()) {
					ans.get(ans.size()-1).add(cm.poll());//getlast element
				}
			}
		}
		return ans;
	    
	}

	
	
	public static void main(String args[]) {

		vNode root = new vNode(1);
        root.left = new vNode(2);
        root.left.left = new vNode(4);
        root.left.right = new vNode(10);
        root.right = new vNode(3);
        root.right.right = new vNode(10);
        root.right.left = new vNode(9);

        List < List < Integer >> list = new ArrayList < > ();
        list = findVertical(root);

        System.out.println("The Vertical Traversal is : ");
        for (List < Integer > it: list) {
            for (int nodeVal: it) {
                System.out.print(nodeVal + " ");
            }
            System.out.println();
        }
    }
}
