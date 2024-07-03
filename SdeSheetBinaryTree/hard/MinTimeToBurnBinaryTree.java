package SdeSheetBinaryTree.hard;

import java.util.*;

public class MinTimeToBurnBinaryTree {
    public int minTime(TreeNode root, int target)
    {
        // Your code goes here
        Map<TreeNode,TreeNode> parentMap=new HashMap<>();
        //we will do bfs to map parentsand also find node from where to start burn
        TreeNode burnStart=bfsToMapParents(root,parentMap,target);
        return findMinTimeToBurn(parentMap,burnStart);
    }

    int findMinTimeToBurn(Map<TreeNode,TreeNode> parentMap,TreeNode burnStart){
        Queue<TreeNode> q=new LinkedList<>();
        q.add(burnStart);
        //Visited array to map visited nodes as we will even travel up(parent)
        //Better to use map as list.contains time complexity is O(N)
        List<TreeNode> visited=new ArrayList<>();
        //In case we have 1 2 N and 2 is target.1 is never visited but it should be.
        //So mark visited
        visited.add(burnStart);
        int time=0;
        while(!q.isEmpty()){
            int size=q.size();
            //I will only increase time when atleast left,right orparent burned someone.
            //If yes increse time else dont increase.SO using flag
            Boolean flag=false;
            for(int i=0;i<size;i++){
                TreeNode node=q.poll();
                if(node.left!=null && !visited.contains(node.left)){
                    visited.add(node.left);
                    q.add(node.left);
                    flag=true;
                }
                if(node.right!=null && !visited.contains(node.right)){
                    visited.add(node.right);
                    q.add(node.right);
                    flag=true;
                }
                if(parentMap.get(node)!=null && !visited.contains(parentMap.get(node))){
                    visited.add(parentMap.get(node));
                    q.add(parentMap.get(node));
                    flag=true;
                }
            }
            if(flag==true){
                time++;
            }
        }
        return time;
    }

    public TreeNode bfsToMapParents(TreeNode root,Map<TreeNode,TreeNode> parentMap,int target){
        Queue<TreeNode> q=new LinkedList<>();
        q.add(root);
        TreeNode burnStart=null;
        while(!q.isEmpty()){
            TreeNode node=q.poll();
            if(node.val==target){
                burnStart=node;
            }
            if(node.left!=null){
                q.add(node.left);
                parentMap.put(node.left,node);
            }
            if(node.right!=null){
                q.add(node.right);
                parentMap.put(node.right,node);
            }
        }
        return burnStart;
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

        MinTimeToBurnBinaryTree levelOrderTraversal=new MinTimeToBurnBinaryTree();
        int res=levelOrderTraversal.minTime(root,3);
        //ans should be 8 and 3.As its distance 2 from node 2
        System.out.println(res);
        /*
                    1
                2       3
             4    5   6    7
                8        9    10
         */

    }


}
