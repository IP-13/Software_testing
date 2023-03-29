import org.example.task2.Node;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("Node")
public class NodeTest {
    @Test
    public void cutTest() {
        Node<Integer> node = new Node<>(10);
        node.degree = 1;

        Node<Integer> x = new Node<>(20);

        Node<Integer> min = new Node<>(5);
        Node<Integer> minLeft = new Node<>(30);
        Node<Integer> minRight = new Node<>(40);

        min.left = minLeft;
        min.right = minRight;
        minRight.left = min;
        minRight.right = minLeft;
        minLeft.right = min;
        minLeft.left = minRight;

        node.cut(x, min);

        assertEquals(node.degree, 0);
        assertNull(node.child);
        assertEquals(x.right, min);
        assertEquals(x.left, minLeft);
        assertEquals(min.left, x);
        assertEquals(minLeft.right, x);
        assertNull(x.parent);
        assertFalse(x.mark);

    }


    @Test
    public void cascadingCutMarkFalseTest() {
        Node<Integer> node = new Node<>(20);
        Node<Integer> parent = new Node<>(10);
        node.parent = parent;
        parent.child = node;

        Node<Integer> min = new Node<>(5);
        Node<Integer> minLeft = new Node<>(30);
        Node<Integer> minRight = new Node<>(40);

        min.left = minLeft;
        min.right = minRight;
        minRight.left = min;
        minRight.right = minLeft;
        minLeft.right = min;
        minLeft.left = minRight;

        node.cascadingCut(min);

        assertTrue(node.mark);
    }


    @Test
    public void cascadingCutMarkTrueTest() {
        Node<Integer> node = new Node<>(20);
        Node<Integer> parent = new Node<>(10);
        node.parent = parent;
        node.mark = true;
        parent.child = node;
        parent.degree = 1;

        Node<Integer> min = new Node<>(5);
        Node<Integer> minLeft = new Node<>(30);
        Node<Integer> minRight = new Node<>(40);

        min.left = minLeft;
        min.right = minRight;
        minRight.left = min;
        minRight.right = minLeft;
        minLeft.right = min;
        minLeft.left = minRight;

        node.cascadingCut(min);

        assertEquals(parent.degree, 0);
        assertNull(parent.child);
        assertEquals(node.right, min);
        assertEquals(node.left, minLeft);
        assertEquals(min.left, node);
        assertEquals(minLeft.right, node);
        assertNull(node.parent);
        assertFalse(node.mark);
    }


    @Test
    public void linkTest() {
        Node<Integer> child1 = new Node<>(10);
        Node<Integer> child2 = new Node<>(11);
        Node<Integer> child3 = new Node<>(12);

        child1.left = child2;
        child2.left = child3;
        child3.left = child1;
        child1.right = child3;
        child2.right = child1;
        child3.right = child2;

        Node<Integer> parent = new Node<>(20);

        child1.link(parent);

        assertEquals(child1.parent, parent);
        assertEquals(parent.child, child1);
        assertEquals(parent.degree, 1);
        assertFalse(parent.mark);
        assertEquals(child1.left, child1);
        assertEquals(child1.right, child1);
        assertEquals(child2.right, child3);
        assertEquals(child3.left, child2);

        child2.link(parent);

        assertEquals(child2.parent, parent);
        assertEquals(parent.child.right, child2);
        assertEquals(parent.child.right.left, child1);
        assertEquals(parent.degree, 2);
        assertFalse(parent.mark);
        assertEquals(child3.right, child3);
        assertEquals(child3.left, child3);
    }

}
