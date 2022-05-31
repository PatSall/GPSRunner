package Test;

import org.junit.jupiter.api.Test;
import sports.Position;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    public void constructorTest() {
        Position a = new Position(23.4, 56.2);

        assertEquals(56.2, a.getLongitudeDegrees());
        assertEquals(23.4, a.getLatitudeDegrees());
    }

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

   /* @Test
    public void testPositionIllegalValue() {
        try {
            new Position(null, null);
            fail("Exception was expected for null input");
        } catch (IllegalArgumentException e) {
        }

        try {
            new Position(0.0, 0.0);
            fail("Exception was expected for empty input");
        } catch (IllegalArgumentException e) {
        }

        try {
            new Position(6.7, 5.4);
            fail("Exception was expected for non-number input");
        } catch (IllegalArgumentException e) {
        }

    }

    */
}