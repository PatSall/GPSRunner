package sports;

import java.time.LocalDateTime;
import java.util.*;


public class Activity {

    private static final long serialVersionUID = 1L;
    private List<Lap> lap;
    private String activity;
    private String id;
    private int lapCounter = 0;

    public Activity() {

    }

    public Activity(String activity) {
        this.activity = activity;
    }


    public String getActivity() {
        return activity;
    }

    public void setActivity(String sport) {
        this.activity = sport;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Lap> getLap() {
        return lap;
    }

    public void setLap(List<Lap> laps) {
        this.lap = laps;
    }

    public Lap averageLap(List<Lap> laps) {
        LocalDateTime startTime = null;
        Double totalTimeSeconds = 0.0;
        Double distanceMeters = 0.0 ;
        Double maximumSpeed = 0.0;
        Integer calories = 0;
        Integer averageHeartRateBpm = 0;
        Integer maximumHeartRateBpm = 0;

        for(int i = 0; i < laps.size(); i++) {
            lapCounter++;
            startTime = laps.get(0).getStartTime();
            totalTimeSeconds += laps.get(i).getTotalTimeSeconds();
            distanceMeters += laps.get(i).getDistanceMetersTracks();
            if (maximumSpeed < laps.get(i).getMaximumSpeed()){
                maximumSpeed = laps.get(i).getMaximumSpeed();
            }
            calories += laps.get(i).getCalories();
            averageHeartRateBpm += laps.get(i).getAverageHeartRateBpm();
            if (maximumHeartRateBpm < laps.get(i).getMaximumHeartRateBpm()) {
                maximumHeartRateBpm = laps.get(i).getMaximumHeartRateBpm();
            }
        }


        return new Lap(startTime, totalTimeSeconds, distanceMeters, maximumSpeed,
                calories, averageHeartRateBpm/lapCounter, maximumHeartRateBpm );

    }


    @Override
    public String toString () {
        return "Activity " + this.activity + "\n ID " + this.id + "\n Lap" +  this.lap;
    }
}
