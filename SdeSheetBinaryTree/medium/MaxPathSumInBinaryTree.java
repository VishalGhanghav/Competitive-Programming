package SdeSheetBinaryTree.medium;

public class MaxPathSumInBinaryTree {
    //Variables cannot be passed by reference in java so declaring global
    int maxi=Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root){
        getMaxSum(root);
        return maxi;
    }

    public int getMaxSum(TreeNode node){
        if(node==null){
            return 0;
        }
        //If we get negative path sum .We simply dont consider it as it will never give us our answer
        int leftSum=Math.max(0,getMaxSum(node.left));
        int rightSum=Math.max(0,getMaxSum(node.right));
        //Use leftSum+rightSum+node value to calculate if new maxi is bigger than existing
        //backtracking step
        maxi=Math.max(maxi,leftSum+rightSum+node.val);
        //we return node val with maximum of left or right sum
        return node.val+Math.max(leftSum,rightSum);

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

        int res=new MaxPathSumInBinaryTree().maxPathSum(root);
        System.out.println(res);
    }


}
