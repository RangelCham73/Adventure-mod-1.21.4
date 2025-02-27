package com.rangelcham.adventuremod.utils;

import java.util.ArrayList;
import java.util.List;

//
//Author: RangelCham73
//

class Node<T> {
    T value;
    List<Node<T>> children;

    // Constructor
    public Node(T value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    // Añadir un hijo al nodo
    public void addChild(Node<T> child) {
        children.add(child);
    }
}
