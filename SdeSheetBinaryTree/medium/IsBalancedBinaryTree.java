package SdeSheetBinaryTree.medium;

public class IsBalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        //I will return -1 if not balanced else return height of bt.
        return getHeight(root)!=-1;
    }

    public int getHeight(TreeNode root){
        if(root==null){
            return 0;
        }
        int lh=getHeight(root.left);
        //At any point of time if lh==-1 it means tree is not balanced
        //Simply return -1 and dont even check further right.
        if(lh==-1){
            return -1;
        }
        int rh=getHeight(root.right);
        //if rh==-1 not balanced
        if(rh==-1){
            return -1;
        }
        //If lh-rh>1 its not balanced
        if(Math.abs(lh-rh)>1){
            return -1;
        }
        return 1+Math.max(lh,rh);
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

        //failure case
        //root.left.right.left.left=new TreeNode(11);

        Boolean res=new IsBalancedBinaryTree().isBalanced(root);
        System.out.println(res);
    }

/*
O(N^2) Approach
  // Function to check if a binary tree is balanced
    public boolean isBalanced(Node root) {
        // If the tree is empty, it's balanced
        if (root == null) {
            return true;
        }

        // Calculate the height of left and right subtrees
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        // Check if the absolute difference in heights
        // of left and right subtrees is <= 1
        if (Math.abs(leftHeight - rightHeight) <= 1 &&
            isBalanced(root.left) &&
            isBalanced(root.right)) {
            return true;
        }

        // If any condition fails, the tree is unbalanced
        return false;
    }

    // Function to calculate the height of a subtree
    public int getHeight(Node root) {
        // Base case: if the current node is NULL,
        // return 0 (height of an empty tree)
        if (root == null) {
            return 0;
        }

        // Recursively calculate the height
        // of left and right subtrees
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        // Return the maximum height of left and right subtrees
        // plus 1 (to account for the current node)
        return Math.max(leftHeight, rightHeight) + 1;
    }
 */
}
