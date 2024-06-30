package SdeSheetBinaryTree.medium;
import java.util.*;

public class BoundaryTraversalOfBinaryTree {

    private List<Integer> boundaryTraversal(TreeNode root) {
        ArrayList<Integer> boundaryList=new ArrayList<>();
        if(root==null){
            return boundaryList;
        }
        if(!isLeaf(root)){
            boundaryList.add(root.val);
        }
        addLeftBoundary(root,boundaryList);
        addLeaves(root,boundaryList);
        addRightBoundary(root,boundaryList);
        return boundaryList;
    }

    private void addLeftBoundary(TreeNode root, List<Integer> boundaryList) {
        //add only when you are at left boundary
        TreeNode curr=root.left;
        while(curr!=null && !isLeaf(curr)){
            boundaryList.add(curr.val);
            if(curr.left!=null){
                curr=curr.left;
            }else if(curr.right!=null){
                curr=curr.right;
            }
        }

    }


    private boolean isLeaf(TreeNode curr) {
        return curr.left==null && curr.right==null;
    }

    private void addRightBoundary(TreeNode root, List<Integer> boundaryList) {
        TreeNode curr=root.right;
        //We want to display right boundary in reverse .So reverse the list and also dont take root again
        List<Integer> tmpList=new ArrayList<>();
        while(curr!=null && !isLeaf(curr)){
            tmpList.add(curr.val);
            if(curr.right!=null){

                curr=curr.right;
            }else if(curr.left!=null){
                curr=curr.left;
            }
        }
        for(int i=tmpList.size()-1;i>=0;i--){
            boundaryList.add(tmpList.get(i));
        }
    }

    private void addLeaves(TreeNode node, List<Integer> boundaryList) {
        //DO any traversal and when leaf found add in boundaryList
        if(node==null){
            return;
        }
        if(isLeaf(node)){
            boundaryList.add(node.val);
        }
        addLeaves(node.left,boundaryList);
        addLeaves(node.right,boundaryList);
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
        BoundaryTraversalOfBinaryTree levelOrderTraversal=new BoundaryTraversalOfBinaryTree();
        List<Integer> resList=levelOrderTraversal.boundaryTraversal(root);
        System.out.println(resList);

    }

}
