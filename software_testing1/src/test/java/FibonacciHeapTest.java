import org.example.task2.FibonacciHeap;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("FibonacciHeap")
public class FibonacciHeapTest {
    @Test
    public void nullMinTest() {
        FibonacciHeap<Integer> fibonacciHeap = new FibonacciHeap<>(Integer.MIN_VALUE);
        assertNull(fibonacciHeap.getMin());
    }


    @Test
    public void clear_isEmpty_getMin_getSize_Test() {
        FibonacciHeap<Integer> fibonacciHeap = new FibonacciHeap<>(Integer.MIN_VALUE);
        fibonacciHeap.clear();
        assertEquals(0, fibonacciHeap.getSize());
        assertNull(fibonacciHeap.getMin());
        assertTrue(fibonacciHeap.isEmpty());
    }


    @Test
    public void insertIntegerTest() {
        FibonacciHeap<Integer> fibonacciHeap = new FibonacciHeap<>(Integer.MIN_VALUE);

        fibonacciHeap.insert(1);

        assertEquals(fibonacciHeap.getMin().key, 1);
        assertEquals(fibonacciHeap.getMin().right, fibonacciHeap.getMin());
        assertEquals(fibonacciHeap.getMin().left, fibonacciHeap.getMin());


        fibonacciHeap.insert(-1);

        assertEquals(fibonacciHeap.getMin().key, -1);
        assertEquals(fibonacciHeap.getMin().left.key, 1);
        assertEquals(fibonacciHeap.getMin().right.key, 1);
        assertEquals(fibonacciHeap.getMin().left.right.key, -1);
        assertEquals(fibonacciHeap.getMin().right.left.key, -1);


        fibonacciHeap.insert(-100);

        assertEquals(fibonacciHeap.getMin().key, -100);
        assertEquals(fibonacciHeap.getMin().left.key, 1);
        assertEquals(fibonacciHeap.getMin().right.key, -1);
        assertEquals(fibonacciHeap.getMin().right.left.key, -100);
        assertEquals(fibonacciHeap.getMin().left.right.key, -100);


        fibonacciHeap.insert(555);

        assertEquals(fibonacciHeap.getMin().key, -100);
        assertEquals(fibonacciHeap.getMin().left.right.key, -100);
        assertEquals(fibonacciHeap.getMin().left.left.key, 1);
        assertEquals(fibonacciHeap.getMin().left.left.right.key, 555);
        assertEquals(fibonacciHeap.getMin().left.key, 555);


        fibonacciHeap.insert(10);

        assertEquals(fibonacciHeap.getMin().key, -100);
        assertEquals(fibonacciHeap.getMin().left.right.key, -100);
        assertEquals(fibonacciHeap.getMin().left.left.key, 555);
        assertEquals(fibonacciHeap.getMin().left.key, 10);
        assertEquals(fibonacciHeap.getMin().left.left.right.key, 10);
    }


    @Test
    public void removeMinIntegerTest() {
        FibonacciHeap<Integer> fibonacciHeap = new FibonacciHeap<>(Integer.MIN_VALUE);

        fibonacciHeap.insert(3);
        fibonacciHeap.insert(1);
        fibonacciHeap.insert(5);
        fibonacciHeap.insert(8);
        fibonacciHeap.insert(10);

        assertEquals(fibonacciHeap.getMin().key, 1);


        fibonacciHeap.removeMin();

        assertEquals(fibonacciHeap.getMin().key, 3);
        assertEquals(fibonacciHeap.getMin().degree, 2);
        assertFalse(fibonacciHeap.getMin().mark);
        assertEquals(fibonacciHeap.getMin().child.key, 5);
        assertEquals(fibonacciHeap.getMin().child.parent, fibonacciHeap.getMin());
        assertEquals(fibonacciHeap.getMin().child.left.key, 8);
        assertEquals(fibonacciHeap.getMin().child.right.key, 8);
        assertEquals(fibonacciHeap.getMin().child.left.right.key, 5);
        assertEquals(fibonacciHeap.getMin().child.left.left.key, 5);
        assertEquals(fibonacciHeap.getMin().child.left.degree, 1);
        assertFalse(fibonacciHeap.getMin().child.left.mark);
        assertEquals(fibonacciHeap.getMin().child.left.child.key, 10);
        assertEquals(fibonacciHeap.getMin().child.left.child.parent, fibonacciHeap.getMin().child.left);


        fibonacciHeap.removeMin();

        assertEquals(fibonacciHeap.getMin().key, 5);
        assertNull(fibonacciHeap.getMin().parent);
        assertEquals(fibonacciHeap.getMin().left.key, 8);
        assertEquals(fibonacciHeap.getMin().right.key, 8);
        assertEquals(fibonacciHeap.getMin().right.left.key, 5);
        assertEquals(fibonacciHeap.getMin().right.right.key, 5);
        assertNull(fibonacciHeap.getMin().left.parent);
        assertFalse(fibonacciHeap.getMin().left.mark);
        assertEquals(fibonacciHeap.getMin().left.degree, 1);
        assertEquals(fibonacciHeap.getMin().left.child.key, 10);
        assertEquals(fibonacciHeap.getMin().left.child.parent.key, 8);
    }


    @Test
    public void decreaseKeyIntegerTest() {
        FibonacciHeap<Integer> fibonacciHeap = new FibonacciHeap<>(Integer.MIN_VALUE);

        fibonacciHeap.insert(3);
        fibonacciHeap.insert(1);
        fibonacciHeap.insert(5);
        fibonacciHeap.insert(8);
        fibonacciHeap.insert(10);

        fibonacciHeap.removeMin();

        fibonacciHeap.decreaseKey(fibonacciHeap.getMin().child.left.child, 1);

        assertEquals(fibonacciHeap.getMin().key, 1);
        assertEquals(fibonacciHeap.getMin().right.child.left.degree, 0);
        assertEquals(fibonacciHeap.getMin().right.key, 3);
        assertEquals(fibonacciHeap.getMin().right.left, fibonacciHeap.getMin());
        assertTrue(fibonacciHeap.getMin().right.child.left.mark);



    }


    @Test
    public void decreaseKeyInNullNodeTest() {
        FibonacciHeap<Integer> fibonacciHeap = new FibonacciHeap<>(Integer.MIN_VALUE);

        assertThrows(IllegalArgumentException.class, () -> fibonacciHeap.decreaseKey(null, 1000));
    }
}
