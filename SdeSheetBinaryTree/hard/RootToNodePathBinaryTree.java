package SdeSheetBinaryTree.hard;

import java.util.*;

public class RootToNodePathBinaryTree {
    private List<Integer> rootToNodePath(TreeNode root,int target) {
        List<Integer> path=new ArrayList<>();
        getPath(root,path,target);
        return path;
    }

    private Boolean getPath(TreeNode node, List<Integer> path,int target) {
        //If we reach node==null ,return false
        if(node==null){
            return false;
        }
        path.add(node.val);
        //if node.value==target we return true
        if(node.val==target){
            return true;
        }
        //if either on left or on right subtree we get true we will always return true till the end.
        //ie.we need to use OR(||)
        if(getPath(node.left,path,target) ||
                getPath(node.right,path,target)){
            return true;
        }
        //If we are getting false, while going back rollback our pathList
        path.removeLast();
        return false;
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
        RootToNodePathBinaryTree levelOrderTraversal=new RootToNodePathBinaryTree();
        List<Integer> resList=levelOrderTraversal.rootToNodePath(root,9);
        System.out.println(resList);
        /*
                    1
                2       3
             4    5   6    7
                8        9    10
         */

    }


}
