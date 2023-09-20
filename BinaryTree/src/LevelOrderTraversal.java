

import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int data) {
        this.val =data ;
        left = right = null;
    }
}
public class LevelOrderTraversal {
    public static List<List<Integer>> preorderTraversal(TreeNode root) {
    	Queue<TreeNode> q=new LinkedList<TreeNode>();
    	Stack<Integer> s=new Stack<Integer>();
    
    	List<List<Integer>> resList=new LinkedList<List<Integer>>();
    	if(root==null) {
    		return resList;
    	}
    	q.add(root);
    	while(!q.isEmpty()) {
    		int queueSize=q.size();
    		List<Integer> subList=new ArrayList<Integer>();
    		for(int i=0;i<queueSize;i++) {
    			
    			if(q.peek().left!=null) {
    				q.add(q.peek().left);
    				
    			}
    			if(q.peek().right!=null) {
    				q.add(q.peek().right);
    			}
    			subList.add(q.poll().val);
    		}
    		resList.add(subList);
    	}
		return resList;
    	
        
    }
   


    public static void main(String args[]) {


        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.right.left = new TreeNode(8);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.right.right.left = new TreeNode(9);
        root.right.right.right = new TreeNode(10);




        List<List<Integer>> preOrder = new ArrayList < > ();
        preOrder = preorderTraversal(root);
        System.out.println(preOrder);
    }
    

}
/*
 public List<Integer> levelOrder(TreeNode root) {
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<Integer> wrapList = new List<Integer>();
        
        if(root == null) return wrapList;
        
        queue.offer(root);
        while(!queue.isEmpty()){
            
            if(queue.peek().left != null) 
                queue.offer(queue.peek().left);
                
            if(queue.peek().right != null) 
                queue.offer(queue.peek().right);
                
            wrapList.add(queue.poll().val);
        }
        return wrapList;
    }*/
 