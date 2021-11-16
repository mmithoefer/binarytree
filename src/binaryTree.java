public class binaryTree {

    private class Node {
        public int val;
        private Node left, right;

        public Node(int val) {
            this.val = val;
        }
    }

    public Node root;

    public binaryTree() {
        root = null;
    }

    public binaryTree(int val) {
        root = new Node(val);
    }

    public binaryTree(Node node) {
        this.root = node;
    }

    public void setLeft(binaryTree treeLeft) {
        if (treeLeft.isEmpty() || isEmpty()) throw new RuntimeException();
        this.root.left = treeLeft.root;
    }
    public void setRight(binaryTree treeRight) {
        if (treeRight.isEmpty() || isEmpty()) throw new RuntimeException();
        this.root.left = treeRight.root;
    }

    public boolean isEmpty(){
        return this.root == null;
    }
}

