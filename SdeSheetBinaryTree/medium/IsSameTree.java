package SdeSheetBinaryTree.medium;

public class IsSameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        //If either of them is null both should be null or else not same
        if(p==null || q==null){
            return (p==q);
        }
        //If all nodes return true its same else false
        return p.val==q.val
                && isSameTree(p.left,q.left)
                && isSameTree(p.right,q.right);

    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(4);
        root2.left.right = new TreeNode(5);


        Boolean res=new IsSameTree().isSameTree(root,root2);
        System.out.println(res);
    }
}
