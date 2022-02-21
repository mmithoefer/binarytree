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
        if (treeLeft.isEmpty()) throw new RuntimeException("Cannot insert empty tree");
        this.root.left = treeLeft.root;
    }

    public void setRight(binaryTree treeRight) {
        if (treeRight.isEmpty() || isEmpty()) throw new RuntimeException("Cannot insert empty tree");
        this.root.right = treeRight.root;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public binaryTree getLeft() {
        if (isEmpty()) throw new RuntimeException("cannot get left of empty tree");
        else return new binaryTree(this.root.left);
    }

    public binaryTree getRight() {
        if (isEmpty()) throw new RuntimeException("cannot get right of empty tree");
        else return new binaryTree(this.root.right);
    }

    public void setVal(int val) {
        if (isEmpty()) root = new Node(val);
        else this.root.val = val;
    }

    public int getVal() {
        if (isEmpty()) throw new RuntimeException("cannot get val of empty tree");
        return this.root.val;
    }

    public binaryTree getMax() {
        if (!isEmpty()) {
            if (!getRight().isEmpty()) return getRight().getMax();
            else return this;
        } else throw new RuntimeException("cannot get max of empty tree");
    }

    public boolean isLeaf() {
        return this.root.left == null && this.root.right == null;
    }

    public String Inorder(binaryTree tree) {
        String ausgabe = "";
        if (!tree.isEmpty()) {
            ausgabe += Inorder(tree.getLeft());
            ausgabe += " | " + Integer.toString(tree.getVal());
            ausgabe += Inorder(tree.getRight());
        }
        return ausgabe;
    }

    public String Preorder(binaryTree tree) {
        String ausgabe = "";
        if (!tree.isEmpty()) {
            ausgabe += " | " + Integer.toString(tree.getVal());
            ausgabe += Preorder(tree.getLeft());
            ausgabe += Preorder(tree.getRight());
        }
        return ausgabe;
    }

    public String Postorder(binaryTree tree) {
        String ausgabe = "";
        if (!tree.isEmpty()) {
            ausgabe += Postorder(tree.getLeft());
            ausgabe += Postorder(tree.getRight());
            ausgabe += " | " + Integer.toString(tree.getVal());
        }
        return ausgabe;
    }

    public boolean search(int val) {
        if (isEmpty()) {
            return false;
        }
        else if (val < getVal()) {
            return getLeft().search(val);
        }
        else if (val > getVal()) {
            return getRight().search(val);
        }
        else return true;
        }

    public void insert(int val) {
        if (!isEmpty()) {
            if (val < getVal()) {
                if (getLeft().isEmpty()) {
                    binaryTree a = new binaryTree(val);
                    setLeft(a);
                }
                else getLeft().insert(val);
            }
            else {
                if (val > getVal()) {
                    if (getRight().isEmpty()) {
                        binaryTree a = new binaryTree(val);
                        setRight(a);
                    }
                    else getRight().insert(val);
                }
                else {
                    throw new RuntimeException("cannot insert value twice");
                }
            }
        }
        else {
            setVal(val);
        }
    }


    //recursive delete function
    public Node remove(Node root, int key)  {
        //tree is empty
        if (root == null)  return null;

        //traverse the tree
        if (key < root.val)     //traverse left subtree
            root.left = remove(root.left, key);
        else if (key > root.val)  //traverse right subtree
            root.right = remove(root.right, key);
        else  {
            // node contains only one or zero children
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            // node has two children;
            //get inorder successor (min value in the right subtree)
            root.val = getLeft().getMax().getVal();

            // Delete the inorder successor
            root.right = remove(root.right, root.val);
        }
        return root;
    }

    public static void main (String[]args){
        binaryTree x = new binaryTree();

        int[] values = new int[]{3,2,5,1,4};

        for(int i = 0; i < values.length; i++){
            x.insert(values[i]);
        }

        System.out.println("Inorder: " + x.Inorder(x));
        System.out.println("Preorder: " + x.Preorder(x));
        System.out.println("Postorder: " + x.Postorder(x));
        System.out.println("Searching for 46: " + x.search(46));
        System.out.println("Deleting 1");
        x.remove(x.root, 1);
        System.out.println("Searching for 1" + x.search(1));
        System.out.println("Inorder: " + x.Inorder(x));
    }
}

