package Test;

import org.junit.jupiter.api.Test;
import sports.TrackGPX;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
/**
 * TrackGPX test-class
 * @author Stefan Gruber
 */
class TrackGPXTest {
    /**
     * tests the function: Setter and Getter: name
     */
    @Test
    void testSetter_Getter_Name() {

        String name = "testzwecke";
        TrackGPX trackGPX = new TrackGPX();
        trackGPX.setName(name);

        assertEquals(name, trackGPX.getName());
    }
    /**
     * tests the function: Setter and Getter: date
     */
    @Test
    void testSetter_Getter_Date() {

        Date date = new Date();
        TrackGPX trackGPX = new TrackGPX();
        trackGPX.setDate(date);

        assertEquals(date, trackGPX.getDate());
    }
}