package SdeSheetBinaryTree.medium;

import java.util.*;


public class VerticalTraversalOfBinaryTree {
    static class Tuple{
        TreeNode node;
        int vert;
        int level;
        public Tuple(TreeNode node, int vert, int level) {

            this.node = node;
            this.vert = vert;
            this.level = level;
        }

    }
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> ans=new ArrayList<>();
        Map<Integer,Map<Integer,PriorityQueue<Integer>>> verticalMap=new TreeMap<>();
        //Queue will store node,vert,level
        Queue<Tuple> q=new LinkedList<>();
        q.add(new Tuple(root,0,0));
        while(!q.isEmpty()){
            Tuple tuple=q.poll();
            TreeNode node=tuple.node;
            int vert=tuple.vert;
            int level=tuple.level;
            //Now we want to put data in verticalMap
            //If vertical not present add.
            if(!verticalMap.containsKey(vert)){
                verticalMap.put(vert,new TreeMap<>());
            }
            //if level not present over that vertical add
            if(!verticalMap.get(vert).containsKey(level)){
                verticalMap.get(vert).put(level,new PriorityQueue<>());
            }
            verticalMap.get(vert).get(level).add(node.val);
            if(node.left!=null){
                q.add(new Tuple(node.left,vert-1,level+1));
            }
            if(node.right!=null){
                q.add(new Tuple(node.right,vert+1,level+1));
            }
        }
        //parentMap:{-2={2=[4]}, -1={1=[2]}, 0={0=[1], 2=[9, 10]}, 1={1=[3]}, 2={2=[10]}}
        for(Map<Integer,PriorityQueue<Integer>> innerMap:verticalMap.values()){
            List<Integer> tmpList = new ArrayList<>();
            for (PriorityQueue<Integer> pq : innerMap.values()) {
                while (!pq.isEmpty()) {
                    tmpList.add(pq.poll());
                }
            }
            ans.add(tmpList);
        }
        return ans;
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

        List<List<Integer>> res=new VerticalTraversalOfBinaryTree().verticalTraversal(root);
        System.out.println(res);
    }

}
