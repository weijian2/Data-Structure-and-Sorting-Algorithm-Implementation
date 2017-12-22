package BST;

/**
 * 08722 Data Structures for Application Programmers.
 *
 * Simple Binary Search Tree interface
 * @author Weijian Li
 */
public class BST implements BSTInterface {
    private Node root;

    public BST() {
        this.root = null;
    }

    public BST(int key, double value) {
        this.root = new Node(key, value);
    }

    /**
     * Finds key in the tree.
     * @param key to find
     * @return boolean value (true when found)
     */
    @Override
    public boolean find(int key) {
        // corner case
        if (root == null) {
            return false;
        }
        // search
        Node current = root;
        while (current != null) {
            if (key < current.key) {
                current = current.left;
            } else if (key > current.key) {
                current = current.right;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * Inserts a new key into the tree.
     * @param key key to add
     */
    @Override
    public void insert(int key, double value) {
        Node newNode = new Node(key, value);
        // corner case
        if (root == null) {
            root = newNode;
            return;
        }
        // search
        Node prev = null;
        Node current = root;
        while (current != null) {
            prev = current;
            if (key < current.key) {
                current = current.left;
            } else if (key > current.key) {
                current = current.right;
            } else {
                return;
            }
        }
        // for here, current == null and we need to insert node under prev
        if (key > prev.key) {
            prev.right = newNode;
        } else {
            prev.left = newNode;
        }
    }

    /**
     * Deletes a key (node) from the tree.
     * @param key key to delete
     */
    @Override
    public void delete(int key) {
        // there are 4 cases
        // case1: the node is not in the tree
        // case2: the node is a leaf node
        // case3: the node has only one child
        // case4: the node has two children
        root = deleteHelper(root, key);

    }

    private Node deleteHelper(Node root, int key) {
        // base case
        if (root == null) {
            return null;
        }
        if (key < root.key) {
            root.left = deleteHelper(root.left, key);
            return root;
        }
        if (key > root.key) {
            root.right = deleteHelper(root.right, key);
            return root;
        }
        // for here, key == root.key, which means we find the target
        // case 2+3
        if (root.left == null || root.right == null) {
            return root.left == null ? root.right : root.left;
        }
        // case4, we have two options, let root be root.left, and connect largest node of left subtree with root.right
        // or let root be root.right, and connect smallest node of right subtree with root.left
        // here, we choose option1
        Node largest = findLargest(root.left);
        largest.right = root.right;
        return root.left;
    }

//    private Node findLargest(Node root) {
//        Node prev = null;
//        while (root != null) {
//            prev = root;
//            root = root.right;
//        }
//        return prev;
//    }

    private Node findLargest(Node root) {
        // base case
        if (root.left == null && root.right == null) {
            return root;
        }
        if (root.right != null) {
            return findLargest(root.right);
        }
        return root;
    }


    /**
     * Traverses the tree in an ascending order based on keys.
     */
    @Override
    public void traverse() {

    }


    private static class Node {
        private int key;
        private double value;
        private Node left, right;

        Node(int k, double v) {
            this.key = k;
            this.value = v;
        }
    }

}
