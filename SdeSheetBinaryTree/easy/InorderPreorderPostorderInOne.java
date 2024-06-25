package SdeSheetBinaryTree.easy;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Pair{
    TreeNode node;
    int num;

    public Pair(TreeNode node, int num) {
        this.node = node;
        this.num = num;
    }
}
public class InorderPreorderPostorderInOne {

    //Iterative
    public void inPrePostorderTraversal(TreeNode root) {
        //I will have a num and node in stack .Based on num ,I will choose if we need to add in preorder
        //inorder or postorder
        List<Integer> preorder=new ArrayList<>();
        List<Integer> inorder=new ArrayList<>();
        List<Integer> postorder=new ArrayList<>();
        Stack<Pair> stack=new Stack<>();
        stack.add(new Pair(root,1));
        //We will keep adding elements in s1 and pop and add in s2
        //when we have checked both left and right of that node
        while(!stack.isEmpty()){
            Pair pair=stack.pop();
            TreeNode node=pair.node;
            //if num==1 it means its preorder.So add in preorder and increase num and go to left
            if(pair.num==1){
                preorder.add(node.val);
                pair.num++;
                stack.add(pair);
                if(node.left!=null){
                    stack.add(new Pair(node.left,1));
                }
            }else if(pair.num==2){
                inorder.add(node.val);
                pair.num++;
                stack.add(pair);
                if(node.right!=null){
                    stack.add(new Pair(node.right,1));
                }
            }else{
                postorder.add(node.val);
            }
        }
        System.out.println("preorder:"+preorder);
        System.out.println("inorder:"+inorder);
        System.out.println("postorder:"+postorder);

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
        InorderPreorderPostorderInOne iterativePostorderUsingTwoStacks=new InorderPreorderPostorderInOne();
        iterativePostorderUsingTwoStacks.inPrePostorderTraversal(root);

    }
}