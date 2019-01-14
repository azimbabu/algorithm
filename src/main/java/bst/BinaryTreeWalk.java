package bst;

public class BinaryTreeWalk {

    public void inorderTreeWalk(TreeNode root) {
        if (root != null) {
            inorderTreeWalk(root.left);
            System.out.println(root.key);
            inorderTreeWalk(root.right);
        }
    }

    public void preorderTreeWalk(TreeNode root) {
        if (root != null) {
            System.out.println(root.key);
            preorderTreeWalk(root.left);
            preorderTreeWalk(root.right);
        }
    }

    public void postorderTreeWalk(TreeNode root) {
        if (root != null) {
            postorderTreeWalk(root.left);
            postorderTreeWalk(root.right);
            System.out.println(root.key);
        }
    }

    public static void main(String[] args) {
        testCase(buildTree1());
        testCase(buildTree2());
    }

    private static void testCase(TreeNode root) {
        BinaryTreeWalk treeWalk = new BinaryTreeWalk();

        System.out.println("Preorder demo :");
        treeWalk.preorderTreeWalk(root);

        System.out.println("Inorder demo :");
        treeWalk.inorderTreeWalk(root);

        System.out.println("Postorder demo :");
        treeWalk.postorderTreeWalk(root);
    }

    private static TreeNode buildTree1() {
        TreeNode six = new TreeNode(6);
        TreeNode five1 = new TreeNode(5);
        TreeNode seven = new TreeNode(7);
        TreeNode two = new TreeNode(2);
        TreeNode five2 = new TreeNode(5);
        TreeNode eight = new TreeNode(8);

        six.left = five1;
        six.right = seven;
        five1.left = two;
        five1.right = five2;
        seven.right = eight;

        return six;
    }

    private static TreeNode buildTree2() {
        TreeNode two = new TreeNode(2);
        TreeNode five1 = new TreeNode(5);
        TreeNode seven = new TreeNode(7);
        TreeNode six = new TreeNode(6);
        TreeNode eight = new TreeNode(8);
        TreeNode five2 = new TreeNode(5);

        two.right = five1;
        five1.right = seven;
        seven.left = six;
        seven.right = eight;
        six.left = five2;

        return two;
    }
}
