package com.rangelcham.adventuremod.utils;

//
//Author: RangelCham73
//

public class Tree<T> {
    Node<T> root;

    // Constructor
    public Tree(T rootValue) {
        root = new Node<T>(rootValue);
    }

    // Method to add a child to a node
    public void addChild(T fatherValue, T childValue) {
        Node<T> fatherNode = findNode(root, fatherValue);
        if (fatherNode != null) {
            Node<T> child = new Node(childValue);
            fatherNode.addChild(child);
        }
    }

    // Method to search for a node with a specific value
    private Node<T> findNode(Node<T> node, T value) {
        if (node == null) {
            return null;
        }
        if (node.value.equals(value)) {
            return node;
        }
        for (Node<T> child : node.children) {
            Node<T> found = findNode(child, value);
            if (found != null) {
                return found;
            }
        }
        return null; // Not found
    }

    // Depth-First Traversal (DFS)
    public void depthFirstTraversal() {
        depthFirstTraversalRecursive(root);
    }

    // Recursive method for depth traversing
    private void depthFirstTraversalRecursive(Node<T> node) {
        if (node != null) {
            System.out.print(node.value + " "); // Visit node
            for (Node<T> child : node.children) {
                depthFirstTraversalRecursive(child); // Traverse children
            }
        }
    }
}
