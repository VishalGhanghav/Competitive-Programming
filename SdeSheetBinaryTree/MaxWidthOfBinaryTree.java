package SdeSheetBinaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class MaxWidthOfBinaryTree {
    class Pair{
        TreeNode node;
        int val;

        Pair(TreeNode node,int val){
            this.node=node;
            this.val=val;
        }

    }
    class Solution {
        public int widthOfBinaryTree(TreeNode root) {
            //Queue will be holding node and current index
            Queue<Pair> q=new LinkedList<>();
            //Following 0 based indexing.left child=2*prevIndex+1 rightChild=2*prevIndex+2
            //res=per level ,lastIndex-firstIndex+1
            q.add(new Pair(root,0));
            int result=0;
            while(!q.isEmpty()){
                int size=q.size();
                int perLevelFirstIndex=q.peek().val;
                int first = 0,last = 0;
                for(int i=0;i<size;i++){
                    //currentIdToCalculateIndexOfNextLeftOrRight=
                    //currentIndex(stored in queue)-prevIndex(parent-stored in )
                    int currIdToCalcIndex=q.peek().val-perLevelFirstIndex;
                    Pair p=q.poll();
                    TreeNode nd=p.node;
                    int val=p.val;
                    if(i==0){
                        first=val;
                    }
                    if(i==size-1){
                        last=val;
                    }
                    if(nd.left!=null){
                        q.add(new Pair(nd.left,2*currIdToCalcIndex+1));
                    }
                    if(nd.right!=null){
                        q.add(new Pair(nd.right,2*currIdToCalcIndex+2));
                    }

                }
                result=Math.max(last-first+1,result);
            }
            return result;
        }
    }
}
