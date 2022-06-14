package Test;

import org.junit.jupiter.api.Test;
import sports.TrackGPS;
import sports.TrackSegment;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrackGPSTest {

    @Test
    void testSetter_Getter_Name() {

        String name = "testzwecke";
        TrackGPS trackGPS = new TrackGPS();
        trackGPS.setName(name);

        assertEquals(name, trackGPS.getName());
    }

    @Test
    void testSetter_Getter_Date() {

        Date date = new Date();
        TrackGPS trackGPS = new TrackGPS();
        trackGPS.setDate(date);

        assertEquals(date, trackGPS.getDate());
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