package org.example.task2;

import java.util.ArrayList;

public class FibonacciHeap<T extends Comparable<T>> {
    private Node<T> min;
    private int size;
    private final T minElement;


    public FibonacciHeap(T minElement) {
        min = null;
        size = 0;
        this.minElement = minElement;
    }


    public void clear() {
        min = null;
        size = 0;
    }


    public boolean isEmpty() {
        return min == null;
    }


    public Node<T> getMin() {
        return min;
    }


    public int getSize() {
        return size;
    }


    private void consolidate() {
        ArrayList<Node<T>> consolidateArray = new ArrayList<>(45);

        for (int i = 0; i < 45; i++) {
            consolidateArray.add(null);
        }

        Node<T> start = min;
        Node<T> w = min;

        do {
            Node<T> x = w;
            Node<T> nextW = w.right;
            int d = x.degree;
            while (consolidateArray.get(d) != null) {
                Node<T> y = consolidateArray.get(d);
                if (x.key.compareTo(y.key) > 0) {
                    Node<T> temp = y;
                    y = x;
                    x = temp;
                }
                if (y == start) {
                    start = start.right;
                }
                if (y == nextW) {
                    nextW = nextW.right;
                }
                y.link(x);
                consolidateArray.set(d, null);
                d++;
            }
            consolidateArray.set(d, x);
            w = nextW;
        } while (w != start);

        min = start;

        for (Node<T> a : consolidateArray) {
            if (a != null && a.key.compareTo(min.key) < 0) {
                min = a;
            }
        }
    }


    public void decreaseKey(Node<T> x, T k) {
        decreaseKey(x, k, false);
    }


    private void decreaseKey(Node<T> x, T k, boolean delete) {
        if (x == null) {
            throw new IllegalArgumentException("X is null");
        }

        if (!delete && k.compareTo(x.key) > 0) {
            throw new IllegalArgumentException("cannot increase key value");
        }

        x.key = k;
        Node<T> y = x.parent;

        if (y != null && (delete || k.compareTo(y.key) < 0)) {
            y.cut(x, min);
            y.cascadingCut(min);
        }

        if (delete || k.compareTo(min.key) < 0) {
            min = x;
        }
    }


    public void delete(Node<T> x) {
        decreaseKey(x, minElement, true);
        removeMin();
    }


    public Node<T> insert(T key) {
        Node<T> node = new Node<>(key);
        if (min != null) {
            node.right = min;
            node.left = min.left;
            min.left = node;
            node.left.right = node;
            if (key.compareTo(min.key) < 0) {
                min = node;
            }
        } else {
            min = node;
        }
        size++;
        return node;
    }


    public T removeMin() {
        Node<T> z = min;
        if (z == null) {
            return null;
        }

        if (z.child != null) {
            z.child.parent = null;
            for (Node<T> x = z.child.right; x != z.child; x = x.right) {
                x.parent = null;
            }
            Node<T> minLeft = min.left;
            Node<T> zChildLeft = z.child.left;
            min.left = zChildLeft;
            zChildLeft.right = min;
            z.child.left = minLeft;
            minLeft.right = z.child;
        }

        z.left.right = z.right;
        z.right.left = z.left;
        if (z == z.right) {
            min = null;
        } else {
            min = z.right;
            consolidate();
        }

        size--;
        return z.key;
    }


    public FibonacciHeap<T> mergeHeaps(FibonacciHeap<T> heap1, FibonacciHeap<T> heap2) {
        FibonacciHeap<T> resultHeap = new FibonacciHeap<T>(minElement);
        if (heap1 != null && heap2 != null) {
            resultHeap.min = heap1.min;
            if (resultHeap.min != null) {
                if (heap2.min != null) {
                    resultHeap.min.right.left = heap2.min.left;
                    heap2.min.left.right = resultHeap.min.right;
                    resultHeap.min.right = heap2.min;
                    heap2.min.left = resultHeap.min;
                    if (heap2.min.key.compareTo(heap1.min.key) < 0) {
                        resultHeap.min = heap2.min;
                    }
                }
            } else {
                resultHeap.min = heap2.min;
            }
            resultHeap.size = heap1.size + heap2.size;
        }
        return resultHeap;
    }
}