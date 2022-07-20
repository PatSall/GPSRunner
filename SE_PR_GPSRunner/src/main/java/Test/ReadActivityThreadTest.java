package Test;

import org.junit.jupiter.api.Test;
import sports.ReadActivityThread;



import static org.junit.jupiter.api.Assertions.*;
/**
 * ReadActivityThread test-class
 * @author Stefan Gruber
 */
class ReadActivityThreadTest {
    /**
     * tests the function: Setter and Getter: name
     */
    @Test
    void test_Setter_Getter_name () {
        String name = "name";
        ReadActivityThread.currentThread().setName(name);
        assertEquals(name, ReadActivityThread.currentThread().getName());
    }
    /**
     * tests the function: priority
     */
    @Test
    void test_Prio () {
        int prio = 5;
        ReadActivityThread.currentThread().setPriority(prio);
        assertEquals(prio, ReadActivityThread.currentThread().getPriority());
    }

}