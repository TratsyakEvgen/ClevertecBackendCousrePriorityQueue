package course;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.course.CustomPriorityQueue;
import ru.clevertec.course.CustomQueue;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CustomPriorityQueueDefaultConstructorTest {

    private CustomQueue<Integer> queue;

    @BeforeEach
    void init() {
        queue = new CustomPriorityQueue<>();
    }

    @Test
    void add_null() {
        assertThrows(IllegalArgumentException.class, () -> queue.add(null));
    }


    @Test
    void add_checkHeap() {
        fillQueue();

        String heap = queue.toString();

        assertEquals(heap, Arrays.toString(new Integer[]{1, 3, 2, 7, 6, 3, 2, null}));
    }


    @Test
    void peek() {
        fillQueue();

        Integer expected = queue.peek();

        assertEquals(expected, 1);
    }

    @Test
    void peek_checkHeap() {
        fillQueue();

        queue.peek();

        assertEquals(queue.toString(), Arrays.toString(new Integer[]{1, 3, 2, 7, 6, 3, 2, null}));
    }

    @Test
    void poll() {
        fillQueue();

        Integer expected = queue.poll();

        assertEquals(expected, 1);
    }

    @Test
    void poll_checkHeap() {
        fillQueue();

        queue.poll();

        assertEquals(queue.toString(), Arrays.toString(new Integer[]{2, 3, 2, 7, 6, 3, null, null}));
    }



    @Test
    void getSize_afterAdd() {
        queue.add(1);
        queue.add(2);

        int size = queue.getSize();

        assertEquals(size, 2);
    }

    @Test
    void getSize_afterAddIdenticalElements() {
        queue.add(1);
        queue.add(1);
        queue.add(1);

        int size = queue.getSize();

        assertEquals(size, 3);
    }

    @Test
    void getSize_afterPeek() {
        queue.add(1);
        queue.peek();

        int size = queue.getSize();

        assertEquals(size, 1);
    }

    @Test
    void getSize_afterPoll() {
        queue.add(1);
        queue.poll();

        int size = queue.getSize();

        assertEquals(size, 0);
    }

    @Test
    void getSize_afterPollEmptyQueue() {
        queue.poll();

        int size = queue.getSize();

        assertEquals(size, 0);
    }


    private void fillQueue() {
        Integer[] array = new Integer[]{3, 3, 1, 7, 6, 2, 2};
        for (Integer i : array) {
            queue.add(i);
        }
    }

}