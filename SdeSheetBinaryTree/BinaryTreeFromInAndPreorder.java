package SdeSheetBinaryTree;

import java.util.HashMap;
import java.util.Map;
/*
Given two integer arrays preorder and inorder where preorder is the preorder traversal
of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.



Example 1:


Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
Output: [3,9,20,null,null,15,7]
Example 2:

Input: preorder = [-1], inorder = [-1]
Output: [-1]


Constraints:

1 <= preorder.length <= 3000
inorder.length == preorder.length
-3000 <= preorder[i], inorder[i] <= 3000
preorder and inorder consist of unique values.
Each value of inorder also appears in preorder.
preorder is guaranteed to be the preorder traversal of the tree.
inorder is guaranteed to be the inorder traversal of the tree.
 */
public class BinaryTreeFromInAndPreorder {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer,Integer> inMap=new HashMap<>();
        for(int i=0;i<inorder.length;i++){
            inMap.put(inorder[i],i);
        }
        TreeNode root=createTree(preorder,0,preorder.length-1,inorder,0,inorder.length-1,inMap);
        return root;

    }

    public TreeNode createTree(int[] preorder,int preStart,int preEnd,int[] inorder,
                               int inStart,int inEnd,Map<Integer,Integer> inMap){
        //when we reach null or leaf nodes .There start will move ahead of end
        if(preStart>preEnd || inStart>inEnd){
            return null;
        }
        //I will first create root for each step
        TreeNode root=new TreeNode(preorder[preStart]);
        //Find me this root in inorder.We will do it using map
        int inRoot=inMap.get(root.val);
        int numsLeft=inRoot-inStart;

        root.left=createTree(preorder,preStart+1,preStart+numsLeft,
                inorder,inStart,inRoot-1,inMap);
        root.right=createTree(preorder,preStart+numsLeft+1,preEnd,
                inorder,inRoot+1,inEnd,inMap);
        return root;

    }
}
