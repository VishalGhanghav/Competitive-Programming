package SdeSheetBinaryTree.hard;


import java.util.LinkedList;
import java.util.Queue;

public class MaximumWidthOfBInaryTree {
    static  class Pair{
        TreeNode node;
        int index;

        public Pair(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }
    private int getMaxWidth(TreeNode root) {
        //We will go level by level so need queue which will hold node and index
        Queue<Pair> q=new LinkedList<>();
        q.add(new Pair(root,0));
        int ans=0;
        while(!q.isEmpty()){
            int size=q.size();
            int minIndexAtLevel=q.peek().index;
            int firstIndex=0;
            int lastIndex=0;
            for(int i=0;i<size;i++){
                Pair pair=q.poll();
                TreeNode node=pair.node;
                //To stop overflow we subtract with min At that level
                //currentIdToCalculateIndexOfNextLeftOrRight=
                //currentIndex(stored in queue)-prevIndex(parent-stored in )
                int currIndex=pair.index-minIndexAtLevel;
                //to find maxWidth we need first and last index at particular level
                if(i==0){
                    firstIndex=currIndex;
                }
                if(i==size-1){
                    lastIndex=currIndex;
                }
                if(node.left!=null){
                    q.add(new Pair(node.left,2*currIndex+1));
                }
                if(node.right!=null){
                    q.add(new Pair(node.right,2*currIndex+2));
                }
            }
            //Now I need to find maxWidth
            ans=Math.max((lastIndex-firstIndex+1),ans);
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
        MaximumWidthOfBInaryTree levelOrderTraversal=new MaximumWidthOfBInaryTree();
        int res=levelOrderTraversal.getMaxWidth(root);
        System.out.println(res);
        /*
                    1
                2       3
             4    5   6    7
                8        9    10
         */

    }


}
