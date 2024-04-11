package SdeSheetBinaryTree;

import java.util.HashMap;
import java.util.Map;
/*
Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree, construct and return the binary tree.



Example 1:


Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
Output: [3,9,20,null,null,15,7]
Example 2:

Input: inorder = [-1], postorder = [-1]
Output: [-1]


Constraints:

1 <= inorder.length <= 3000
postorder.length == inorder.length
-3000 <= inorder[i], postorder[i] <= 3000
inorder and postorder consist of unique values.
Each value of postorder also appears in inorder.
inorder is guaranteed to be the inorder traversal of the tree.
postorder is guaranteed to be the postorder traversal of the tree.
 */
public class BinaryTreeFromInAndPostorder {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer,Integer> inMap=new HashMap<>();
        for(int i=0;i<inorder.length;i++){
            inMap.put(inorder[i],i);
        }
        TreeNode root=createTree(postorder,0,postorder.length-1,inorder,0,inorder.length-1,inMap);
        return root;
    }

    public TreeNode createTree(int[] postorder,int postStart,int postEnd,int[] inorder,
                               int inStart,int inEnd,Map<Integer,Integer> inMap){
        //when we reach null or leaf nodes .There start will move ahead of end
        if(postStart>postEnd || inStart>inEnd){
            return null;
        }
        //I will first create root for each step
        TreeNode root=new TreeNode(postorder[postEnd]);
        //Find me this root in inorder.We will do it using map
        int inRoot=inMap.get(root.val);
        int numsLeft=inRoot-inStart;

        root.left=createTree(postorder,postStart,postStart+numsLeft-1,
                inorder,inStart,inRoot-1,inMap);
        root.right=createTree(postorder,postStart+numsLeft,postEnd-1,
                inorder,inRoot+1,inEnd,inMap);
        return root;

    }
}
