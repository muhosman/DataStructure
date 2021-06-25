package com.datastrcrew.libraryapi.classes;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;

/**
 * Class for a binary tree that stores generic objects.
 * @author Koffman and Wolfgang
 **/
public class BinaryTree<E> implements Serializable {
	
    protected static class Node<E> implements Serializable {

        /** Data */
        public E data;
        /** Left Node */
        public Node<E> left;
        /** Right Node */
        public Node<E> right;
        /** Count */
        public int count;

        /**
         * Construct a node with given data and no children.
         * @param data The data to store in this node
         */
        public Node(E data) {
            this.data = data;
            left = null;
            right = null;
            count=1;
        }

        /**
         * Returns a string representation of the node.
         * @return A string representation of the data fields
         */
        @Override
        public String toString() {
            return data.toString();
        }
    }

    /** The root of the binary tree */
    protected Node<E> root;
    /** Size */
    protected int size;

    /** Construct an empty BinaryTree */
    public BinaryTree() {
        root = null;
        size=0;
    }

    /**
     * Construct a BinaryTree with a specified root.
     * Should only be used by subclasses.
     * @param root The node that is the root of the tree.
     */
    protected BinaryTree(Node<E> root) {
        this.root = root;
    }

    /**
     * Constructs a new binary tree with data in its root,leftTree
     * as its left subtree and rightTree as its right subtree.
     * @param data data object.
     * @param leftTree left tree.
     * @param rightTree right tree.
     */
    public BinaryTree(E data, BinaryTree<E> leftTree,
            BinaryTree<E> rightTree) {
        root = new Node<E>(data);
        if (leftTree != null) {
            root.left = leftTree.root;
        } else {
            root.left = null;
        }
        if (rightTree != null) {
            root.right = rightTree.root;
        } else {
            root.right = null;
        }
    }

    /**
     * Return the left subtree.
     * @return The left subtree or null if either the root or
     * the left subtree is null
     */
    public BinaryTree<E> getLeftSubtree() {
        if (root != null && root.left != null) {
            return new BinaryTree<E>(root.left);
        } else {
            return null;
        }
    }

    /**
     * Return the right sub-tree
     * @return the right sub-tree or
     *         null if either the root or the
     *         right subtree is null.
     */
    public BinaryTree<E> getRightSubtree() {
        if (root != null && root.right != null) {
            return new BinaryTree<E>(root.right);
        } else {
            return null;
        }
    }

    /**
     * Return the data field of the root
     * @return the data field of the root
     *         or null if the root is null
     */
    public E getRoot()
	{
		if (root != null) 
	        return root.data;
	    else
	        return null;
    }
    
    /**
	 * @return true if empty, false if not 
	 */
	public boolean isEmpty()
	{
		if(root==null)
			return true;
		
		return false;
	}
	
	public int size() {return size;}

    /**
     * Determine whether this tree is a leaf.
     * @return true if the root has no children
     */
    public boolean isLeaf() {
        return (root == null || (root.left == null && root.right == null));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();
    }

    /**
     * Perform a preorder traversal.
     * @param node The local root
     * @param depth The depth
     * @param sb The string buffer to save the output
     */
    private void preOrderTraverse(Node<E> node, int depth,
            StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            sb.append("  ");
        }
        if (node == null) {
            sb.append("null\n");
        } else {
            sb.append(node.toString());
            sb.append("\n");
            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth + 1, sb);
        }
    }

    /**
     * Method to read a binary tree.
     * 
     * @param bR The input file
     * @return The binary tree
     * @throws IOException If there is an input error
     */
    public static BinaryTree<String> readBinaryTree(BufferedReader bR)
            throws IOException {
        // Read a line and trim leading and trailing spaces.
        String data = bR.readLine().trim();
        if (data.equals("null")) {
            return null;
        } else {
            BinaryTree<String> leftTree = readBinaryTree(bR);
            BinaryTree<String> rightTree = readBinaryTree(bR);
            return new BinaryTree<String>(data, leftTree, rightTree);
        }
    }
    
    /**
	 * Prints the tree inOrder form.
	 */
	private void inOrderPrint(Node<E> node)
	{
		if(node==null)
			return;
		else
		{
			inOrderPrint(node.left);
			System.out.println(node.toString());
			inOrderPrint(node.right);
		}
	}
	
	/**
	 * Prints the tree inOrder form.
	 */
	public void inOrderPrint()
	{
		inOrderPrint(root);
	}

}
