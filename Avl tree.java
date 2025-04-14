// AVL Node Class
class AVLNode {
    int key;
    AVLNode left, right;
    int height;

    AVLNode(int key) {
        this.key = key;
        this.height = 0; // Initial height set to 0
        this.left = this.right = null;
    }
}

public class AVLTree {

    // Get the height of a node
    public static int height(AVLNode node) {
        return (node == null) ? -1 : node.height;
    }

    // Rotate node with left child (Single Left Rotation)
    public static AVLNode rotateWithLeftChild(AVLNode k2) {
        AVLNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;

        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;

        return k1;
    }

    // Rotate node with right child (Single Right Rotation)
    public static AVLNode rotateWithRightChild(AVLNode k1) {
        AVLNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;

        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        k2.height = Math.max(height(k2.right), k1.height) + 1;

        return k2;
    }

    // Double rotation: Left-Right (LR Rotation)
    public static AVLNode doubleWithLeftChild(AVLNode k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    // Double rotation: Right-Left (RL Rotation)
    public static AVLNode doubleWithRightChild(AVLNode k1) {
        k1.right = rotateWithLeftChild(k1.right);
        return rotateWithRightChild(k1);
    }

    // Insert a node into the AVL tree
    public static AVLNode insert(int key, AVLNode node) {
        if (node == null)
            return new AVLNode(key);

        if (key < node.key) { // Insert in left subtree
            node.left = insert(key, node.left);
            if (height(node.left) - height(node.right) == 2) {
                node = (key < node.left.key) ? rotateWithLeftChild(node) : doubleWithLeftChild(node);
            }
        } else if (key > node.key) { // Insert in right subtree
            node.right = insert(key, node.right);
            if (height(node.right) - height(node.left) == 2) {
                node = (key > node.right.key) ? rotateWithRightChild(node) : doubleWithRightChild(node);
            }
        }

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }

    // Find the minimum node in a tree
    public static AVLNode findMin(AVLNode node) {
        return (node == null || node.left == null) ? node : findMin(node.left);
    }

    // Delete a node from the AVL tree
    public static AVLNode delete(int key, AVLNode node) {
        if (node == null)
            return null;

        if (key < node.key) {
            node.left = delete(key, node.left);
        } else if (key > node.key) {
            node.right = delete(key, node.right);
        } else { // Node to be deleted found
            if (node.left != null && node.right != null) { // Two children
                AVLNode minNode = findMin(node.right);
                node.key = minNode.key;
                node.right = delete(minNode.key, node.right);
            } else { // One or no child
                node = (node.left != null) ? node.left : node.right;
            }
        }

        if (node != null) {
            node.height = Math.max(height(node.left), height(node.right)) + 1;

            // Balance the tree
            if (height(node.left) - height(node.right) == 2) {
                if (height(node.left.left) >= height(node.left.right)) {
                    node = rotateWithLeftChild(node);
                } else {
                    node = doubleWithLeftChild(node);
                }
            } else if (height(node.right) - height(node.left) == 2) {
                if (height(node.right.right) >= height(node.right.left)) {
                    node = rotateWithRightChild(node);
                } else {
                    node = doubleWithRightChild(node);
                }
            }
        }

        return node;
    }

    // In-order traversal of the AVL tree
    public static void inOrder(AVLNode node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + " ");
            inOrder(node.right);
        }
    }

    public static void main(String[] args) {
        AVLNode root = null;
        int[] array = {3, 2, 1, 4, 5, 6, 7, 16, 15, 14, 13, 12, 11, 10, 8, 9};

        for (int x : array) {
            root = insert(x, root);
            System.out.println("Height after inserting " + x + " is " + height(root));
        }

        System.out.println("In-order traversal of the AVL tree:");
        inOrder(root);
        System.out.println();

        root = delete(13, root);
        System.out.println("In-order traversal after deleting 13:");
        inOrder(root);
        System.out.println();
    }
}
