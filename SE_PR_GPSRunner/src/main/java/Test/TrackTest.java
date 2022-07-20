package Test;

import org.junit.jupiter.api.Test;
import sports.Extension;
import sports.Lap;
import sports.Position;
import sports.Track;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
/**
 * Tracktest test-class
 * @author Stefan Gruber
 */
import static org.junit.jupiter.api.Assertions.*;

class TrackTest {
    /**
     * tests the function: Setter and Getter: time
     */
    @Test
    void testSetter_Getter_setsTime () {

        LocalDateTime time = LocalDateTime.now();

        System.out.println(time);

        Track track = new Track();
        track.setTime(time);
        System.out.println(track.getTime());

        assertEquals(time, track.getTime());
    }
    /**
     * tests the function: Setter and Getter: altitude meters
     */
    @Test
    void testSetter_Getter_setsAltitudeMeters () {

        double AltitudeMeters = 50.65;
        Track track = new Track();
        track.setAltitudeMeters(AltitudeMeters);

        assertEquals(AltitudeMeters, track.getAltitudeMeters());
    }
    /**
     * tests the function: Setter and Getter: distance meters tracks
     */
    @Test
    void testSetter_Getter_setsDistanceMetersTracks () {

        double DistanceMetersTracks = 34596.97;
        Track track = new Track();
        track.setDistanceMetersTracks(DistanceMetersTracks);

        assertEquals(DistanceMetersTracks, track.getDistanceMetersTracks());
    }
    /**
     * tests the function: Setter and Getter: heart rate bpm
     */
    @Test
    void testSetter_Getter_HeartRateBpm () {

        int HeartRateBpm = 155;
        Track track = new Track();
        track.setHeartRateBpm(HeartRateBpm);

        assertEquals(HeartRateBpm, track.getHeartRateBpm());
    }
    /**
     * tests the function: Setter and Getter: position
     */
    @Test
    void testSetter_Getter_Position () {

        Position position =  new Position(46.7,54.3);
        Track track = new Track();
        track.setPosition(position);

        assertEquals(position, track.getPosition());
    }
    /**
     * tests the function: Setter and Getter: extension
     */
    @Test
    void testSetter_Getter_Extension () {

        Extension ex = new Extension(54.7, 76);
        Track track = new Track();
        track.setExtension(ex);

        assertEquals(ex, track.getExtension());
    }
}