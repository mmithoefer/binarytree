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
        this.root.right = treeRight.root;
    }

    public boolean isEmpty(){
        return this.root == null;
    }

    public binaryTree getLeft(){
        return new binaryTree(this.root.left);
    }

    public binaryTree getRight(){
        return new binaryTree(this.root.right);
    }

    public void setVal(int val){
        this.root.val = val;
    }

    public int getVal(){
        return this.root.val;
    }

    public boolean isLeaf(){
        return this.root.left == null && this.root.right == null;
    }

    public String Inorder(binaryTree tree){
        String ausgabe = "";
        if (!tree.isEmpty()){
            ausgabe = ausgabe.concat(Inorder(tree.getLeft()));
            ausgabe = ausgabe.concat(Integer.toString(tree.getVal()));
            ausgabe = ausgabe.concat(Inorder(tree.getRight()));
        }
        return ausgabe;
    }

    public String Preorder(binaryTree tree){
        String ausgabe = "";
        if (!tree.isEmpty()){
            ausgabe = ausgabe.concat(Integer.toString(tree.getVal()));
            ausgabe = ausgabe.concat(Inorder(tree.getLeft()));
            ausgabe = ausgabe.concat(Inorder(tree.getRight()));
        }
        return ausgabe;
    }

    public String Postorder(binaryTree tree){
        String ausgabe = "";
        if (!tree.isEmpty()){
            ausgabe = ausgabe.concat(Inorder(tree.getLeft()));
            ausgabe = ausgabe.concat(Inorder(tree.getRight()));
            ausgabe = ausgabe.concat(Integer.toString(tree.getVal()));
        }
        return ausgabe;
    }

    public static void main(String[] args) {
        binaryTree a = new binaryTree(1);
        binaryTree b = new binaryTree(2);
        binaryTree c = new binaryTree(3);
        binaryTree d = new binaryTree(4);
        binaryTree e = new binaryTree(5);
        a.setLeft(b);
        a.setRight(c);
        b.setRight(d);
        b.setLeft(e);
        System.out.println("Inorder: " + a.Inorder(a));
        System.out.println("Preorder: " + a.Preorder(a));
        System.out.println("Postorder: "+ a.Postorder(a));
    }
}

