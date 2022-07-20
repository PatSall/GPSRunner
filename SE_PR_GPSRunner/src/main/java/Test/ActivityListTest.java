package Test;

import org.junit.jupiter.api.Test;

import sports.Activity;
import sports.ActivityList;
import sports.Lap;

import java.time.LocalDateTime;
import java.util.Arrays;


/**
 * ActivityList test-class
 * @author Stefan Gruber
 */

class ActivityListTest {
    /**
     * tests the function: add activity to activities list
     */
    @Test
    void testAdd_Activity () {
        ActivityList activityList = new ActivityList();
        Activity run = new Activity();

        var lapList = Arrays.asList(
                new Lap(LocalDateTime.now().minusMinutes(3), 450.6, 2536.1, 12.5, 200, 156.4, 160),
                new Lap(LocalDateTime.now().minusMinutes(1), 700.6, 5450.5, 13.4, 450, 167.5, 170),
                new Lap(LocalDateTime.now().minusMinutes(5), 1000.56, 7859.5, 10.2, 783, 176.2, 182)
        );

        run.setLap(lapList);
        activityList.addActivity(run);

    }


}