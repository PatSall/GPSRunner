package Test;

import gui.GUI;
import org.junit.jupiter.api.Test;
import sports.Activity;
import sports.Lap;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


class ActivityTest {

        LocalDateTime startTime= LocalDateTime.now();
        Double totalTimeSeconds = 100.54;
        Double distanceMeters = 1000.55;
        Double maximumSpeed = 12.4;
        Integer calories = 243;
        double averageHeartRateBpm = 156;
        Integer maximumHeartRateBpm = 163;

    @Test //showInGui
    void testShowInGuiTrue(){

        var sportsFilter = new GUI.Filter[1];
        sportsFilter[0] = new GUI().new Filter("run", true, null);

        var distanceFilter = new GUI.Filter[1];
        distanceFilter[0] = new GUI().new Filter("run", true, null, 1000000);

        var activity = new Activity();
        activity.setLap(
                Arrays.asList(
                        new Lap(LocalDateTime.now().minusMinutes(3), 450.6, 2536.1, 12.5, 200, 156.4, 160),
                        new Lap(LocalDateTime.now().minusMinutes(1), 700.6, 5450.5, 13.4, 450, 167.5, 170),
                        new Lap(LocalDateTime.now().minusMinutes(5), 1000.56, 7859.5, 10.2, 783, 176.2, 182)
                )
        );


        assertTrue(activity.showInGui(sportsFilter, distanceFilter));

    }

    @Test //showInGui
    void testShowInGuiFalse(){
        var sportsFilter = new GUI.Filter[1];
        sportsFilter[0] = new GUI().new Filter("run", false, null);

        var distanceFilter = new GUI.Filter[1];
        distanceFilter[0] = new GUI().new Filter("run", true, null, 1000000);

        var activity = new Activity();
        activity.setLap(
                Arrays.asList(
                        new Lap(LocalDateTime.now().minusMinutes(3), 450.6, 2536.1, 12.5, 200, 156.4, 160),
                        new Lap(LocalDateTime.now().minusMinutes(1), 700.6, 5450.5, 13.4, 450, 167.5, 170),
                        new Lap(LocalDateTime.now().minusMinutes(5), 1000.56, 7859.5, 10.2, 783, 176.2, 182)
                )
        );
        assertFalse(activity.showInGui(sportsFilter, distanceFilter));
    }

    /*
    @Test //showInGraph
    void graphTrue(){

        var graphFilter = Arrays.asList(
                new  GUI.Filter("run", true, ) //JCheckBoxMenuItem fehlt
        );

        var graph1 = new Activity();
        assertEquals(graph1.showInGraph(graphFilter));
    }
*/
    @Test //averageLap
    void lap(){

        var lapList = Arrays.asList(
                new Lap(LocalDateTime.now().minusMinutes(3), 450.6, 2536.1, 12.5, 200, 156.4, 160),
                new Lap(LocalDateTime.now().minusMinutes(1), 700.6, 5450.5, 13.4, 450, 167.5, 170),
                new Lap(LocalDateTime.now().minusMinutes(5), 1000.56, 7859.5, 10.2, 783, 176.2, 182)
        );

        var activity = new Activity();
        var averageLap = activity.averageLap(lapList);

        assertEquals(lapList.get(0).getStartTime(), averageLap.getStartTime());
        assertEquals(lapList.stream().mapToDouble(Lap::getTotalTimeSeconds).sum(), averageLap.getTotalTimeSeconds());
        assertEquals(2151.76, averageLap.getTotalTimeSeconds());


    }

}