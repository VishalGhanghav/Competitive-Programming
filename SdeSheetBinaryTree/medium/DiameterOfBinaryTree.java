package SdeSheetBinaryTree.medium;

public class DiameterOfBinaryTree {
    int maxi=0;
    public int diameterOfBinaryTree(TreeNode root) {
        getMaxi(root);
        return maxi;
    }

    public int getMaxi(TreeNode root){
        if(root==null){
            return 0;
        }
        int lh=getMaxi(root.left);
        int rh=getMaxi(root.right);
        maxi=Math.max(lh+rh,maxi);
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

        int res=new DiameterOfBinaryTree().diameterOfBinaryTree(root);
        System.out.println(res);
    }
}
