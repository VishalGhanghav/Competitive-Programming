package SdeSheetBinaryTree.medium;

public class IsSymmetricBinaryTree {
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
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);

        Boolean res=new IsSymmetricBinaryTree().isSymmetric(root);
        System.out.println(res);
    }
}
