package Test;

import org.junit.jupiter.api.Test;
import sports.Extension;
import sports.Lap;
import sports.Track;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Extensions test-class
 * @author Stefan Gruber
 */
class ExtensionTest {
    /**
     * tests the function: Setter and Getter: speed
     */
    @Test
    void testSetter_Getter_Speed() {

        double speed = 12.7;
        Extension extension = new Extension();
        extension.setSpeed(speed);

        assertEquals(speed, extension.getSpeed());
    }
    /**
     * tests the function: Setter and Getter: run cadence
     */
    @Test
    void testSetter_Getter_RunCadence() {

        int runCadence = 161;
        Extension extension = new Extension();
        extension.setRunCadence(runCadence);

        assertEquals(runCadence, extension.getRunCadence());
    }
}