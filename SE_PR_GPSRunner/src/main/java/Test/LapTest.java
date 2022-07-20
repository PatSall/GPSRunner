package Test;

import org.junit.jupiter.api.Test;
import sports.Lap;
import sports.Track;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * LapTest test-class
 * @author Stefan Gruber
 */
class LapTest {
    /**
     * tests the function: Setter and Getter: start time
     */
    @Test
    public void testSetter_setsStartTime(){

        LocalDateTime time = LocalDateTime.now();

     final Lap lap = new Lap();
     lap.setStartTime(time);
        assertEquals(time, lap.getStartTime());
    }
    /**
     * tests the function: Setter and Getter: total time seconds
     */
    @Test
    public void testSetter_setsTotalTimeSeconds(){

        double sec = 2400.56;

        final Lap lap = new Lap();
        lap.setTotalTimeSeconds(sec);
        assertEquals(lap.getTotalTimeSeconds(), sec);
    }
    /**
     * tests the function: Setter and Getter: distance meters track
     */
    @Test
    public void testSetter_setsDistanceMetersTrack(){

        double meters = 3056.23;

        final Lap lap = new Lap();
        lap.setDistanceMetersTracks(meters);
        assertEquals(lap.getDistanceMetersTracks(), meters);
    }
    /**
     * tests the function: Setter and Getter: maximum speed
     */
    @Test
    void testSetter_setsMaximumSpeed(){

        double maxSpeed = 13.43;
        final Lap lap = new Lap();
        lap.setMaximumSpeed(maxSpeed);
        assertEquals(lap.getMaximumSpeed(), maxSpeed);
    }
    /**
     * tests the function: Setter and Getter: calories
     */
    @Test
    void testSetter_setsCalories(){

        int cal = 376;
        final Lap lap = new Lap();
        lap.setCalories(cal);
        assertEquals(lap.getCalories(), cal);
    }
    /**
     * tests the function: Setter and Getter: average heard rate bpm
     */
    @Test
    void testSetter_setsAverageHeartRateBpm(){

        int heartrate = 155;
        final Lap lap = new Lap();
        lap.setAverageHeartRateBpm(heartrate);
        assertEquals(lap.getAverageHeartRateBpm(), heartrate);
    }
    /**
     * tests the function: Setter and Getter: maximum heart rate bpm
     */
    @Test
    void testSetter_setsMaximumHeartRateBpm(){

        int maxHeartRate = 186;
        final Lap lap = new Lap();
        lap.setMaximumHeartRateBpm(maxHeartRate);
        assertEquals(lap.getMaximumHeartRateBpm(), maxHeartRate);
    }
    /**
     * tests the function: Setter and Getter: intensity
     */
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
    /**
     * tests the function: Setter and Getter: trigger method
     */
    @Test
    void testSetter_setsTriggerMethod(){
        
       String trigger = "trigger";
       final Lap lap = new Lap();
       lap.setTriggerMethod(trigger);
       assertEquals(trigger, lap.getTriggerMethod());
    }
    /**
     * tests the function: Setter and Getter: track
     */
    @Test
    void testSetter_setsTrack(){
        
        final Lap lap = new Lap();
        List<Track> tracks = new ArrayList<>();
        lap.setTrack(new ArrayList<>(tracks));
    }
}