import org.jetbrains.annotations.NotNull;

import java.util.Stack;

public class binaryTree {

    private static class Node {
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
        if (isEmpty()) throw new RuntimeException("cannot get left of empty tree");
        else return new binaryTree(this.root.left);
    }

    public binaryTree getRight(){
        if (isEmpty()) throw new RuntimeException("cannot get right of empty tree");
        else return new binaryTree(this.root.right);
    }

    public void setVal(int val){
        if (isEmpty()) new binaryTree(val);
        else this.root.val = val;
    }

    public int getVal(){
        if (isEmpty()) throw new RuntimeException("cannot get val of empty tree");
        return this.root.val;
    }

    public boolean isLeaf(){
        return this.root.left == null && this.root.right == null;
    }

    public String Inorder(binaryTree tree){
        String ausgabe = "";
        if (!tree.isEmpty()){
            ausgabe += Inorder(tree.getLeft());
            ausgabe += Integer.toString(tree.getVal());
            ausgabe += Inorder(tree.getRight());
        }
        return ausgabe;
    }

    public String Preorder(binaryTree tree){
        String ausgabe = "";
        if (!tree.isEmpty()){
            ausgabe += Integer.toString(tree.getVal());
            ausgabe += Inorder(tree.getLeft());
            ausgabe += Inorder(tree.getRight());
        }
        return ausgabe;
    }

    public String Postorder(binaryTree tree){
        String ausgabe = "";
        if (!tree.isEmpty()){
            ausgabe += Inorder(tree.getLeft());
            ausgabe += Inorder(tree.getRight());
            ausgabe += Integer.toString(tree.getVal());
        }
        return ausgabe;
    }

    public int getNodeCount(binaryTree tree){
        Stack<binaryTree> stack = new Stack<>();
        int count = 0;
        while(!stack.isEmpty() || !tree.isEmpty()){
            if (!tree.isEmpty()){
                stack.push(tree);
                tree = tree.getLeft();
            }
            else{
                tree = stack.peek();
                stack.pop();
                count++;
                tree = tree.getRight();
            }
        }
        return count;
    }

    public int search(int val){
        if (isEmpty()) return -1;
        if (val < getVal()){
            return getLeft().search(val);
        }
        if (val > getVal()){
            return getRight().search(val);
        }
        else
            return getVal();
    }

    public void insertTree(@NotNull binaryTree tree){
        if (!tree.isEmpty() && !isEmpty()) {
            if (tree.getVal() < getVal()) {
                if (getLeft().isEmpty()) {
                    setLeft(tree);
                    return;
                } else {
                    getLeft().insertTree(tree);
                }
            } else if (tree.getVal() > getVal()) {
                if (getRight().isEmpty()) {
                    setRight(tree);
                    return;
                } else {
                    getRight().insertTree(tree);
                }
            } else {
                throw new RuntimeException("cannot insert element twice");
            }
        }
        else{
            root = tree.root;
        }
    }

    public void insertVal(int val){
        insertTree(new binaryTree(val));
    }

    public static void main(String[] args) {

        binaryTree a = new binaryTree();
        binaryTree b = new binaryTree(2);
        binaryTree c = new binaryTree(3);
        binaryTree d = new binaryTree(1);
        binaryTree e = new binaryTree(5);
        binaryTree f = new binaryTree(6);
        /*
        a.setLeft(b);
        a.setRight(c);
        b.setRight(d);
        b.setLeft(e);
        System.out.println("Inorder: " + a.Inorder(a));
        System.out.println("Preorder: " + a.Preorder(a));
        System.out.println("Postorder: "+ a.Postorder(a));
        System.out.println(a.getNodeCount(a));

         */
        a.insertTree(b);
        a.insertTree(c);
        a.insertTree(d);
        a.insertTree(e);
        a.insertTree(f);
        a.insertVal(7);
        System.out.println("Inorder: " + a.Inorder(a));
        System.out.println(a.search(30));
        System.out.println(a.search(3));
    }

}

