package SdeSheetBinaryTree.hard;

import java.util.*;

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
public class BinaryTreeFromInAndPostorder {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer,Integer> inMap=new HashMap<>();
        for(int i=0;i<inorder.length;i++){
            inMap.put(inorder[i],i);
        }
        TreeNode root=createTree(postorder,0,postorder.length-1,inorder,0,inorder.length-1,inMap);
        return root;
    }

    public TreeNode createTree(int[] postorder, int postStart, int postEnd, int[] inorder,
                                                  int inStart, int inEnd, Map<Integer,Integer> inMap){
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
    public static void main(String[] args) {
        BinaryTreeFromInAndPostorder binaryTreeFromInAndPreorder=new BinaryTreeFromInAndPostorder();
        int[] inorder={9,3,15,20,7};
        int[] postorder={9,15,7,20,3};
        TreeNode root=binaryTreeFromInAndPreorder.buildTree(inorder,postorder);
        System.out.println(binaryTreeFromInAndPreorder.levelOrder(root));
    }
    public List<List<Integer>> levelOrder(TreeNode root) {
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
