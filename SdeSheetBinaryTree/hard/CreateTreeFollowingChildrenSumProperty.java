package SdeSheetBinaryTree.hard;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CreateTreeFollowingChildrenSumProperty {
    private void childrenSum(TreeNode root) {
        if(root==null){
            return;
        }
        int childSum=0;
        //Add up the sum of left and right
        if(root.left!=null){
            childSum+=root.left.val;
        }
        if(root.right!=null){
            childSum+=root.right.val;
        }
        //if childSum>root.val .Atleast change root.val to sum
        if(childSum>= root.val){
            root.val=childSum;
        }else{
            //If childSum is less.Change the left or right to root.val
            if(root.left!=null){
                root.left.val=root.val;
            }
            if(root.right!=null){
                root.right.val=root.val;
            }
        }
        childrenSum(root.left);
        childrenSum(root.right);

        //while returning back(backtracking)
        int total=0;
        if(root.left!=null){
            total+=root.left.val;
        }
        if(root.right!=null){
            total+=root.right.val;;
        }
        //If not leaf add up left and right and store in root
        if(root.left!=null || root.right!=null){
            root.val=total;
        }

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
        CreateTreeFollowingChildrenSumProperty levelOrderTraversal=new CreateTreeFollowingChildrenSumProperty();
        levelOrderTraversal.childrenSum(root);
        System.out.println(levelOrderTraversal.printLevelorder(root));
        /*
                    1
                2       3
             4    5   6    7
                8        9    10
         */

    }

    private List<List<Integer>> printLevelorder(TreeNode root) {
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


}
