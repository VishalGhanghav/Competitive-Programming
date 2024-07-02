package SdeSheetBinaryTree.hard;

import java.util.*;

public class NodesAtKDistanceInBinartTree {
    public void setparent(TreeNode root,Map<TreeNode,TreeNode> map){
        Queue<TreeNode> q=new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){

            int size=q.size();

            TreeNode temp=q.poll();
            if(temp.left!=null){
                map.put(temp.left,temp);
                q.add(temp.left);
            }
            if(temp.right!=null){
                map.put(temp.right,temp);
                q.add(temp.right);
            }


        }

    }
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        Map<TreeNode,TreeNode> map=new HashMap<>();
        setparent(root,map);
        Map<TreeNode,Boolean> visited=new HashMap<>();
        visited.put(target,true);
        Queue<TreeNode> q=new LinkedList<>();
        q.offer(target);
        int level=0;
        while(!q.isEmpty()){
            int size=q.size();
            if(level==k){
                break;
            }
            level++;


            for(int i=0;i<size;i++){
                TreeNode temp=q.poll();
                if(temp.left!=null&&visited.get(temp.left)==null){
                    q.add(temp.left);
                    visited.put(temp.left,true);

                }
                if(temp.right!=null&&visited.get(temp.right)==null){
                    q.add(temp.right);
                    visited.put(temp.right,true);
                }
                if(map.get(temp)!=null&&visited.get(map.get(temp))==null){
                    q.add(map.get(temp));
                    visited.put(map.get(temp),true);

                }
            }
        }
        List<Integer> mc=new ArrayList<>();
        while(!q.isEmpty()){
            TreeNode help=q.poll();
            mc.add(help.val);
        }

        return mc;
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

        NodesAtKDistanceInBinartTree levelOrderTraversal=new NodesAtKDistanceInBinartTree();
        List<Integer> res=levelOrderTraversal.distanceK(root,root.left,2);
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
