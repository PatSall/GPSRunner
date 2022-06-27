package Test;

import org.junit.jupiter.api.Test;
import sports.Lap;
import sports.Track;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LapTest {

    @Test
    public void testSetter_setsStartTime(){

        LocalDateTime time = LocalDateTime.now();

     final Lap lap = new Lap();
     lap.setStartTime(time);
        assertEquals(time, lap.getStartTime());
    }

    @Test
    public void testSetter_setsTotalTimeSeconds(){

        double sec = 2400.56;

        final Lap lap = new Lap();
        lap.setTotalTimeSeconds(sec);
        assertEquals(lap.getTotalTimeSeconds(), sec);
    }

    @Test
    public void testSetter_setsDistanceMetersTrack(){

        double meters = 3056.23;

        final Lap lap = new Lap();
        lap.setDistanceMetersTracks(meters);
        assertEquals(lap.getDistanceMetersTracks(), meters);
    }

    @Test
    void testSetter_setsMaximumSpeed(){

        double maxSpeed = 13.43;
        final Lap lap = new Lap();
        lap.setMaximumSpeed(maxSpeed);
        assertEquals(lap.getMaximumSpeed(), maxSpeed);
    }

    @Test
    void testSetter_setsCalories(){

        int cal = 376;
        final Lap lap = new Lap();
        lap.setCalories(cal);
        assertEquals(lap.getCalories(), cal);
    }

    @Test
    void testSetter_setsAverageHeartRateBpm(){

        int heartrate = 155;
        final Lap lap = new Lap();
        lap.setAverageHeartRateBpm(heartrate);
        assertEquals(lap.getAverageHeartRateBpm(), heartrate);
    }

    @Test
    void testSetter_setsMaximumHeartRateBpm(){

        int maxHeartRate = 186;
        final Lap lap = new Lap();
        lap.setMaximumHeartRateBpm(maxHeartRate);
        assertEquals(lap.getMaximumHeartRateBpm(), maxHeartRate);
    }

    @Test
    void testSetter_setsIntensity(){

        String intensity = "leicht";
        String intensity2 = "mäßig";
        String intensity3 = "maximal";

        final Lap lap = new Lap();
        lap.setIntensity(intensity);
        assertEquals(lap.getIntensity(), intensity);
        lap.setIntensity(intensity2);
        assertEquals(intensity2, lap.getIntensity());
        lap.setIntensity(intensity3);
        assertEquals(intensity3, lap.getIntensity());
    }
    
    @Test
    void testSetter_setsTriggerMethod(){
        
       String trigger = "trigger";
       final Lap lap = new Lap();
       lap.setTriggerMethod(trigger);
       assertEquals(trigger, lap.getTriggerMethod());
    }
    
    @Test
    void testSetter_setsTrack(){
        
        final Lap lap = new Lap();
        List<Track> tracks = new ArrayList<>();
        lap.setTrack(new ArrayList<>(tracks));
        // Liste vergleichen!?
    }
}