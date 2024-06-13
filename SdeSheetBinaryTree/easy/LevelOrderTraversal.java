package SdeSheetBinaryTree.easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {

    /*Using DFS
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res=new ArrayList<>();
        getLevelOrder(res,root,0);
        return res;
    }

    public void getLevelOrder(List<List<Integer>> res,TreeNode root,int ht){
        if(root==null){
            return;
        }
        //If ht>=res.size() it means we have got a new level
        if(ht>=res.size()){
            res.add(new ArrayList<>());
        }
        //add values in list based on ht they are at
        res.get(ht).add(root.val);
        getLevelOrder(res,root.left,ht+1);
        getLevelOrder(res,root.right,ht+1);
    }*/
    //Using BFS
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res=new ArrayList<>();
        Queue<TreeNode> q=new LinkedList<>();
        if(root==null){
            return new ArrayList<>();
        }
        q.add(root);
        while(!q.isEmpty()){
            int size=q.size();
            List<Integer> temp=new ArrayList<>();
            for(int i=0;i<size;i++){
                TreeNode node=q.poll();
                temp.add(node.val);
                if(node.left!=null){
                    q.add(node.left);
                }
                if(node.right!=null){
                    q.add(node.right);
                }
            }
            res.add(temp);
        }
        return res;
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.left.right.left = new TreeNode(8);
        root.right.right.left = new TreeNode(9);
        root.right.right.right = new TreeNode(10);
        LevelOrderTraversal levelOrderTraversal=new LevelOrderTraversal();
        List<List<Integer>> resList=levelOrderTraversal.levelOrder(root);
        System.out.println(resList);

    }
}
