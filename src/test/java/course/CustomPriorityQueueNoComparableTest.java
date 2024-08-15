package course;

import org.junit.jupiter.api.Test;
import ru.clevertec.course.CustomPriorityQueue;
import ru.clevertec.course.CustomQueue;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomPriorityQueueNoComparableTest {
    @Test
    void add_noComparable() {
        CustomQueue<Object> queue = new CustomPriorityQueue<>();

        assertThrows(ClassCastException.class, ()-> queue.add(new Object()));
    }
}
