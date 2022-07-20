package Test;

import org.junit.jupiter.api.Test;
import sports.Lap;
import sports.TrackPoint;
import sports.TrackSegment;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Track Point test-class
 * @author Stefan Gruber
 */
class TrackSegmentTest {
    /**
     * tests the function: Setter and Getter: trackpoint
     */
    @Test
    void testSetter_Getter_TrackPoint () {

        List<TrackPoint> trackPoint = null; // trackpoint befüllen!
        TrackSegment trackSegment = new TrackSegment();
        trackSegment.setTrackPoint(trackPoint);

        assertEquals(trackPoint, trackSegment.getTrackPoint());
    }
    /**
     * tests the function: Add and Getter: trackpoint
     */
    @Test
    void testAdd_Getter_TrackPoint () {

        TrackSegment trackSegment = new TrackSegment();
        TrackPoint trackPoint = new TrackPoint();
        LocalDateTime time = LocalDateTime.now();

        trackPoint.setTrackPointLon(55.6);
        trackPoint.setTrackPointLat(44.6);
        trackPoint.setElem(33.22);
        trackPoint.setTime(time);

        trackSegment.addTrackPoint(trackPoint);
    }
}