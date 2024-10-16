package SdeSheetBinaryTree;
/*
Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).



Example 1:


Input: root = [1,2,2,3,4,4,3]
Output: true
Example 2:


Input: root = [1,2,2,null,3,null,3]
Output: false


Constraints:

The number of nodes in the tree is in the range [1, 1000].
-100 <= Node.val <= 100


Follow up: Could you solve it both recursively and iteratively?
 */
public class IsSymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        if(root==null){
            return true;
        }
        return isSymm(root.left,root.right);
    }

    public boolean isSymm(TreeNode leftRoot,TreeNode rightRoot){
        //if left is null and right is null then true ,if left null and right not null then false
        if(leftRoot==null || rightRoot==null){
            return leftRoot==rightRoot;
        }
        //if left and right is not null.then check if val is same
        if(leftRoot.val!=rightRoot.val){
            return false;
        }
        //go left tree left and right tree right simulatneously and viceversa
        return isSymm(leftRoot.left,rightRoot.right) &&
                isSymm(leftRoot.right,rightRoot.left);



    }

}
