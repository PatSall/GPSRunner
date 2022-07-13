package Test;

import org.junit.jupiter.api.Test;
import sports.ReadActivityThread;



import static org.junit.jupiter.api.Assertions.*;

class ReadActivityThreadTest {

    @Test
    void test_Setter_Getter_name () {
        String name = "name";
        ReadActivityThread.currentThread().setName(name);
        assertEquals(name, ReadActivityThread.currentThread().getName());
    }

    @Test
    void test_Prio () {
        int prio = 5;
        ReadActivityThread.currentThread().setPriority(prio);
        assertEquals(prio, ReadActivityThread.currentThread().getPriority());
    }

}