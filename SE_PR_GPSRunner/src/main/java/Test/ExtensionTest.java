package Test;

import org.junit.jupiter.api.Test;
import sports.Extension;
import sports.Lap;
import sports.Track;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExtensionTest {

    @Test
    void testSetter_Getter_Speed() {

        double speed = 12.7;
        Extension extension = new Extension();
        extension.setSpeed(speed);

        assertEquals(speed, extension.getSpeed());
    }

    @Test
    void testSetter_Getter_RunCadence() {

        int runCadence = 161;
        Extension extension = new Extension();
        extension.setRunCadence(runCadence);

        assertEquals(runCadence, extension.getRunCadence());
    }
}