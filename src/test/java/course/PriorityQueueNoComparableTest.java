package course;

import org.junit.jupiter.api.Test;
import ru.clevertec.course.PriorityQueue;
import ru.clevertec.course.Queue;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PriorityQueueNoComparableTest {
    @Test
    void add_noComparable() {
        Queue<Object> queue = new PriorityQueue<>();

        assertThrows(ClassCastException.class, ()-> queue.add(new Object()));
    }
}
