package SdeSheetBinaryTree.hard;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class RootToAllLeafPathInBinaryTree {
    ArrayList<ArrayList<Integer>> path=new ArrayList<>();
    private ArrayList<ArrayList<Integer>> rootToLeafPath(TreeNode root) {
        ArrayList<Integer> tmpList=new ArrayList<>();
        getPath(root,tmpList);
        return path;
    }

    private void getPath(TreeNode node, ArrayList<Integer> tmpList) {
        //If we reach node==null ,return false
        if(node==null){
            return;
        }
        tmpList.add(node.val);
        //If the current node is a leaf (both left and right children are null),
        // add the current path to the result.
        if(node.left==null && node.right==null){
            path.add(new ArrayList<>(tmpList));
        }

        getPath(node.left,tmpList);
        getPath(node.right,tmpList);
        //As we have added path upto end .While going back we will need to remove from back of list
        tmpList.removeLast();
        return;
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
        RootToAllLeafPathInBinaryTree levelOrderTraversal=new RootToAllLeafPathInBinaryTree();
        ArrayList<ArrayList<Integer>> resList=levelOrderTraversal.rootToLeafPath(root);
        System.out.println(resList);
        /*
                    1
                2       3
             4    5   6    7
                8        9    10
         */

    }


}
