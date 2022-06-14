package Test;

import org.junit.jupiter.api.Test;
import sports.Extension;
import sports.Lap;
import sports.Position;
import sports.Track;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrackTest {

    @Test
    void testSetter_Getter_setsTime () {

        LocalDateTime time = LocalDateTime.now();

        System.out.println(time);

        Track track = new Track();
        track.setTime(time);
        System.out.println(track.getTime());

        assertEquals(time, track.getTime());
    }

    @Test
    void testSetter_Getter_setsAltitudeMeters () {

        double AltitudeMeters = 50.65;
        Track track = new Track();
        track.setAltitudeMeters(AltitudeMeters);

        assertEquals(AltitudeMeters, track.getAltitudeMeters());
    }

    @Test
    void testSetter_Getter_setsDistanceMetersTracks () {

        double DistanceMetersTracks = 34596.97;
        Track track = new Track();
        track.setDistanceMetersTracks(DistanceMetersTracks);

        assertEquals(DistanceMetersTracks, track.getDistanceMetersTracks());
    }

    @Test
    void testSetter_Getter_HeartRateBpm () {

        int HeartRateBpm = 155;
        Track track = new Track();
        track.setHeartRateBpm(HeartRateBpm);

        assertEquals(HeartRateBpm, track.getHeartRateBpm());
    }

    @Test
    void testSetter_Getter_Position () {

        Position position =  new Position(46.7,54.3);
        Track track = new Track();
        track.setPosition(position);

        assertEquals(position, track.getPosition());
    }

    @Test
    void testSetter_Getter_Extension () {

        Extension ex = new Extension(54.7, 76);
        Track track = new Track();
        track.setExtension(ex);

        assertEquals(ex, track.getExtension());
    }
}