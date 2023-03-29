package org.example.task2;

public class Node<T extends Comparable<T>> {
    public T key;
    public Node<T> parent;
    public Node<T> child;
    public Node<T> right;
    public Node<T> left;
    public int degree;
    public boolean mark;


    public Node(T key) {
        this.key = key;
        right = this;
        left = this;
    }


    public void cascadingCut(Node<T> min) {
        Node<T> z = parent;
        if (z != null) {
            if (mark) {
                z.cut(this, min);
                z.cascadingCut(min);
            } else {
                mark = true;
            }
        }
    }


    public void cut(Node<T> x, Node<T> min) {
        x.left.right = x.right;
        x.right.left = x.left;
        degree--;

        if (degree == 0) {
            child = null;
        } else if (child == x) {
            child = x.right;
        }

        x.right = min;
        x.left = min.left;
        min.left = x;
        x.left.right = x;
        x.parent = null;
        x.mark = false;
    }


    public void link(Node<T> parent) {
        left.right = right;
        right.left = left;

        this.parent = parent;
        if (parent.child == null) {
            parent.child = this;
            right = this;
            left = this;
        } else {
            left = parent.child;
            right = parent.child.right;
            parent.child.right = this;
            right.left = this;
        }

        parent.degree++;
        mark = false;
    }
}
