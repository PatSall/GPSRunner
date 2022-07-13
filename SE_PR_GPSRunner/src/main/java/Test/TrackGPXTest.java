package Test;

import org.junit.jupiter.api.Test;
import sports.TrackGPX;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TrackGPXTest {

    @Test
    void testSetter_Getter_Name() {

        String name = "testzwecke";
        TrackGPX trackGPX = new TrackGPX();
        trackGPX.setName(name);

        assertEquals(name, trackGPX.getName());
    }

    @Test
    void testSetter_Getter_Date() {

        Date date = new Date();
        TrackGPX trackGPX = new TrackGPX();
        trackGPX.setDate(date);

        assertEquals(date, trackGPX.getDate());
    }

   /*
    @Test
    void testSetter_Getter_TrackSegment() {

        TrackSegment trackSegment = new TrackSegment();
        TrackGPS trackGPS = new TrackGPS();

        trackGPS.setTrackSegments((List<TrackSegment>) trackSegment);

        assertEquals(trackSegment, trackGPS.getTrackSegments());
    }

    */

}