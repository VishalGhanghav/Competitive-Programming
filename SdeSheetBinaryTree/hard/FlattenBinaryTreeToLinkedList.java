package SdeSheetBinaryTree.hard;

import com.sun.source.tree.Tree;

import java.util.List;

/*
Given the root of a binary tree, flatten the tree into a "linked list":

The "linked list" should use the same TreeNode class where the right child pointer points to the next node
 in the list and the left child pointer is always null.
The "linked list" should be in the same order as a pre-order traversal of the binary tree.


Example 1:


Input: root = [1,2,5,3,4,null,6]
Output: [1,null,2,null,3,null,4,null,5,null,6]
Example 2:

Input: root = []
Output: []
Example 3:

Input: root = [0]
Output: [0]


Constraints:

The number of nodes in the tree is in the range [0, 2000].
-100 <= Node.val <= 100


Follow up: Can you flatten the tree in-place (with O(1) extra space)?
 */
public class FlattenBinaryTreeToLinkedList {
    public void flatten(TreeNode node) {
        TreeNode curr=node;
        while(curr!=null){
            if(curr.left!=null){
                TreeNode prev=curr.left;
                //connect rightmost node of roots left to roots right
                while(prev.right!=null){
                    prev=prev.right;
                }
                prev.right=curr.right;
                curr.right=curr.left;
                curr.left=null;
            }
            curr=curr.right;
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
        FlattenBinaryTreeToLinkedList levelOrderTraversal=new FlattenBinaryTreeToLinkedList();
        System.out.print("Before:");
        TreeNode curr=root;
        while(curr!=null){
            System.out.print(STR."\{curr.val} ");
            curr=curr.right;
        }
        levelOrderTraversal.flatten(root);
        System.out.println("After:");
        TreeNode curr2=root;
        while(curr2!=null){
            System.out.print(STR."\{curr2.val} ");
            curr2=curr2.right;
        }
        /*
                    1
                2       3
             4    5   6    7
                8        9    10
         */

    }
}
