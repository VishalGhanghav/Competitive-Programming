package SdeSheetBinaryTree.medium;

import java.util.*;

public class ZigZagLevelOrderTraversal {
    public List<List<Integer>> zigzag(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<List<Integer>> wrapList = new ArrayList<>();

        if (root == null)
            return wrapList;

        queue.offer(root);
        boolean flag = true;
        while (!queue.isEmpty()) {
            int levelNum = queue.size();
            List<Integer> subList = new ArrayList<Integer>(levelNum);
            for (int i = 0; i < levelNum; i++) {
                TreeNode node=queue.poll();
                int index = i;
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
                if (flag == true){
                    //If flag true simply add
                    subList.add(node.val);
                }else{
                    //if flag fase we need to add reverse.
                    //eg. 1 2 3 .It will add 1 first at index0.Then 2 at index0.List:2 1.Then 3 at index0 List:3 2 1
                    subList.add(0, node.val);
                }
            }
            flag = !flag;
            wrapList.add(subList);
        }
        return wrapList;
       /* 
       Collections.reverse takes another O(N) Time Complexity
       List<List<Integer>> resultList=new ArrayList<>();
        Queue<TreeNode> q=new LinkedList<>();
        if(root==null){
            return resultList;
        }
        q.add(root);
        Boolean flag=false;
        while(!q.isEmpty()){
            int size=q.size();
            List<Integer> innerList=new ArrayList<>();
            for(int i=0;i<size;i++){

                TreeNode node=q.poll();

                innerList.add(node.val);
                if(node.left!=null){
                    q.add(node.left);
                }
                if(node.right!=null){
                    q.add(node.right);
                }
            }
            if(flag==true){
                Collections.reverse(innerList);
            }
            flag=!flag;
            resultList.add(innerList);
        }
        return resultList;
        */
        
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
        ZigZagLevelOrderTraversal levelOrderTraversal=new ZigZagLevelOrderTraversal();
        List<List<Integer>> resList=levelOrderTraversal.zigzag(root);
        System.out.println(resList);

    }
}
