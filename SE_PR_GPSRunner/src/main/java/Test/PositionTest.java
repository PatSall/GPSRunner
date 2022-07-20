package Test;

import org.junit.jupiter.api.Test;
import sports.Position;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Position test-class
 * @author Stefan Gruber
 */
class PositionTest {
    /**
     * tests the function: constructor
     */
    @Test
    public void constructorTest() {
        Position a = new Position(23.4, 56.2);

        assertEquals(56.2, a.getLongitudeDegrees());
        assertEquals(23.4, a.getLatitudeDegrees());
    }
    /**
     * tests the function: position value
     */
    @Test
    public void testPositionValue() {
        try {
            new Position(3.3, -2.9);
            new Position(-4.5, 6.3);
            new Position(null, 5.4);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}