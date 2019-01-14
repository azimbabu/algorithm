package bst;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BinaryTreeQuery {

    public TreeNode treeSearch(TreeNode root, int key) {
        if (root == null || root.key == key) {
            return root;
        } else if (key < root.key) {
            return treeSearch(root.left, key);
        } else {
            return treeSearch(root.right, key);
        }
    }

    public TreeNode iterativeTreeSearch(TreeNode root, int key) {
        TreeNode current = root;
        while (current != null && current.key != key) {
            if (key < current.key) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return current;
    }

    public TreeNode minimum(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    public TreeNode maximum(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current;
    }

    public TreeNode successor(TreeNode node) {
        if (node.right != null) {
            return minimum(node.right);
        } else {
            TreeNode current = node;
            TreeNode parent = current.parent;
            while (parent != null && parent.right == current) {
                current = parent;
                parent = current.parent;
            }
            return parent;
        }
    }

    public TreeNode predecessor(TreeNode node) {
        if (node.left != null) {
            return maximum(node.left);
        } else {
            TreeNode current = node;
            TreeNode parent = current.parent;
            while (parent != null && parent.left == current) {
                current = parent;
                parent = current.parent;
            }
            return parent;
        }
    }

    public static void main(String[] args) {
        treeSearchDemo();
        treeMinMaxDemo();
        treePredecessorSuccessorDemo();
    }

    private static void treePredecessorSuccessorDemo() {
        BinaryTreeQuery query = new BinaryTreeQuery();
        TreeNode root = buildTree();
        List<Integer> keys = Arrays.asList(2, 3, 4, 6, 7, 9, 13, 15, 17, 18, 20);

        for (int key : keys) {
            TreeNode current = query.iterativeTreeSearch(root, key);
            TreeNode successor = query.successor(current);
            TreeNode predecessor = query.predecessor(current);
            System.out.println(String.format("Key = %s, Predecessor = %s, Successor = %s", current.key ,
                                             predecessor != null ? predecessor.key : "null",
                                             successor != null ? successor.key : "null"));
        }
    }

    private static void treeMinMaxDemo() {
        TreeNode root = buildTree();
        BinaryTreeQuery query = new BinaryTreeQuery();

        System.out.println("Minimum = " + query.minimum(root).key);
        System.out.println("Maximum = " + query.maximum(root).key);
    }

    private static void treeSearchDemo() {
        BinaryTreeQuery query = new BinaryTreeQuery();
        TreeNode root = buildTree();
        System.out.println("Recursive search demo");
        System.out.println("Search 13 : " + (query.treeSearch(root, 13) != null ? "found" : "not found"));
        System.out.println("Search 2 : " + (query.treeSearch(root, 2) != null ? "found" : "not found"));
        System.out.println("Search 20 : " + (query.treeSearch(root, 20) != null ? "found" : "not found"));
        System.out.println("Search 15 : " + (query.treeSearch(root, 15) != null ? "found" : "not found"));
        System.out.println("Search 17 : " + (query.treeSearch(root, 17) != null ? "found" : "not found"));
        System.out.println("Search 5 : " + (query.treeSearch(root, 5) != null ? "found" : "not found"));
        System.out.println("Search 1 : " + (query.treeSearch(root, 1) != null ? "found" : "not found"));
        System.out.println("Search 16 : " + (query.treeSearch(root, 16) != null ? "found" : "not found"));

        System.out.println("\nIterative search demo");
        System.out.println("Search 13 : " + (query.iterativeTreeSearch(root, 13) != null ? "found" : "not found"));
        System.out.println("Search 2 : " + (query.iterativeTreeSearch(root, 2) != null ? "found" : "not found"));
        System.out.println("Search 20 : " + (query.iterativeTreeSearch(root, 20) != null ? "found" : "not found"));
        System.out.println("Search 15 : " + (query.iterativeTreeSearch(root, 15) != null ? "found" : "not found"));
        System.out.println("Search 17 : " + (query.iterativeTreeSearch(root, 17) != null ? "found" : "not found"));
        System.out.println("Search 5 : " + (query.iterativeTreeSearch(root, 5) != null ? "found" : "not found"));
        System.out.println("Search 1 : " + (query.iterativeTreeSearch(root, 1) != null ? "found" : "not found"));
        System.out.println("Search 16 : " + (query.iterativeTreeSearch(root, 16) != null ? "found" : "not found"));
    }

    private static TreeNode buildTree() {
        TreeNode fifteen = new TreeNode(15);
        TreeNode six = new TreeNode(6);
        TreeNode eighteen = new TreeNode(18);
        TreeNode three = new TreeNode(3);
        TreeNode seven = new TreeNode(7);
        TreeNode seventeen = new TreeNode(17);
        TreeNode twenty = new TreeNode(20);
        TreeNode two = new TreeNode(2);
        TreeNode four = new TreeNode(4);
        TreeNode thirteen = new TreeNode(13);
        TreeNode nine = new TreeNode(9);

        fifteen.left = six;
        fifteen.right = eighteen;
        six.parent = fifteen;
        eighteen.parent = fifteen;

        six.left = three;
        six.right = seven;
        three.parent = six;
        seven.parent = six;

        eighteen.left = seventeen;
        eighteen.right = twenty;
        seventeen.parent = eighteen;
        twenty.parent = eighteen;

        three.left = two;
        three.right = four;
        two.parent = three;
        four.parent = three;

        seven.right = thirteen;
        thirteen.parent = seven;

        thirteen.left = nine;
        nine.parent = thirteen;

        return fifteen;
    }
}
