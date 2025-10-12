package SdeSheetBinaryTree.hard;

import java.util.LinkedList;
import java.util.Queue;

public class SerializeAndDeserializeBinaryTree {
    // ? Serialize: Convert tree -> string
    public String serialize(TreeNode root) {
        if (root == null) return "";

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        StringBuilder sb = new StringBuilder();

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                sb.append("# ");
                continue;
            }
            sb.append(node.val).append(" ");
            queue.add(node.left);
            queue.add(node.right);
        }
        return sb.toString();
    }

    // ? Deserialize: Convert string -> tree
    public TreeNode deserialize(String data) {
        if (data.equals("")) return null;

        String[] nodes = data.split(" ");
        Queue<TreeNode> q = new LinkedList<>();

        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        q.add(root);

        int i = 1;
        while (!q.isEmpty() && i < nodes.length) {
            TreeNode node = q.poll();

            // Left child
            if (!nodes[i].equals("#")) {
                node.left = new TreeNode(Integer.parseInt(nodes[i]));
                q.add(node.left);
            }
            i++;

            // Right child
            if (i < nodes.length && !nodes[i].equals("#")) {
                node.right = new TreeNode(Integer.parseInt(nodes[i]));
                q.add(node.right);
            }
            i++;
        }
        return root;
    }

    // ? Print level-order traversal (BFS)
    public void printLevelOrder(TreeNode root) {
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node == null) {
                    System.out.print("# ");
                    continue;
                }
                System.out.print(node.val + " ");
                queue.add(node.left);
                queue.add(node.right);
            }
            System.out.println(); // New level
        }
    }

    // ? Main method for testing
    public static void main(String[] args) {
        SerializeAndDeserializeBinaryTree codec = new SerializeAndDeserializeBinaryTree();

        // Create a sample tree:
        //        1
        //       / \
        //      2   3
        //         / \
        //        4   5
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        // Serialize
        String serialized = codec.serialize(root);
        System.out.println("Serialized: " + serialized);

        // Deserialize
        TreeNode deserialized = codec.deserialize(serialized);

        // Print level order of both
        System.out.println("\nOriginal Tree (Level Order):");
        codec.printLevelOrder(root);

        System.out.println("\nDeserialized Tree (Level Order):");
        codec.printLevelOrder(deserialized);
    }
}
