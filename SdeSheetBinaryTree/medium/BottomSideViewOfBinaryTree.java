package SdeSheetBinaryTree.medium;

import java.util.*;


public class BottomSideViewOfBinaryTree {
    static class Pair{
        TreeNode node;
        int vert;

        public Pair(TreeNode node, int vert) {
            this.node = node;
            this.vert = vert;
        }
    }
    private List<Integer> bottomView(TreeNode root) {
        List<Integer> bottomViewList=new ArrayList<>();
        if(root==null){
            return bottomViewList;
        }
        //We will go level by level and maintain a vertical line.
        //If we go left vert-1 and right vert+1.We will store the first element of each
        //vertical in TreeMap(because we need vert and node.val both mapped and ordered).
        //We will add in map for each vert and at last .Only bottom elements
        // of vert will remain in map which is our ans
        //Later just traverse map and add in res list
        Map<Integer,Integer> verticalNodeMap=new TreeMap<>();
        Queue<Pair> levelQueue=new LinkedList<>();
        levelQueue.add(new Pair(root, 0));
        while(!levelQueue.isEmpty()){
            Pair nodeVertPair=levelQueue.poll();
            TreeNode node=nodeVertPair.node;
            int vert=nodeVertPair.vert;
            verticalNodeMap.put(vert,node.val);
            if(node.left!=null){
                levelQueue.add(new Pair(node.left, vert - 1));
            }
            if(node.right!=null){
                levelQueue.add(new Pair(node.right, vert + 1));
            }
        }
        for(Map.Entry<Integer,Integer> vertNodeEntry:verticalNodeMap.entrySet()){
            bottomViewList.add(vertNodeEntry.getValue());
        }
        return bottomViewList;
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

        List<Integer> res=new BottomSideViewOfBinaryTree().bottomView(root);
        System.out.println(res);
    }

}
