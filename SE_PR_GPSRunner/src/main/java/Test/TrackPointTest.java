package Test;

import org.junit.jupiter.api.Test;
import sports.Track;
import sports.TrackPoint;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TrackPointTest {

    @Test
    void testSetter_Getter_Elem () {

        double elem = 20.54;
        TrackPoint tp = new TrackPoint();
        tp.setElem(elem);

        assertEquals(elem, tp.getElem());
    }

    @Test
    void testSetter_Getter_Time () {
        LocalDateTime time = LocalDateTime.now();

        System.out.println(time);

        TrackPoint tp = new TrackPoint();
        tp.setTime(time);
        System.out.println(tp.getTime());

        assertEquals(time, tp.getTime());
    }

    @Test
    void testSetter_Getter_getTrackPointLat () {

        double trackPointLat = 30.45;
        TrackPoint tp = new TrackPoint();
        tp.setTrackPointLat(trackPointLat);

        assertEquals(trackPointLat, tp.getTrackPointLat());
    }

    @Test
    void testSetter_Getter_getTrackPointLon () {

        double trackPointLon = 22.56;
        TrackPoint tp = new TrackPoint();
        tp.setTrackPointLon(trackPointLon);

        assertEquals(trackPointLon, tp.getTrackPointLon());
    }

}