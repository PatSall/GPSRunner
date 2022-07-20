package sports;

import java.time.LocalDateTime;
import java.util.*;

import gui.GUI.Filter;

/**
 * Activity is a part of the TCX Data Structure
 * the TCX Data Structure is structured hierarchically
 * @author Susanne Gumplmayr
 */
public class Activity {

    private List<Lap> lap;
    private String activity;
    private String id;
    private int lapCounter = 0;

    public Activity() {

    }

    public Activity(String activity) {
        this.activity = activity;
    }


    /**
     * @return acitivty in String format
     * e.g. running, swimming....
     */
    public String getActivity() {
        return activity;
    }

    /**
     * @param sport in String format
     * e.g. running, swimming...
     */
    public void setActivity(String sport) {
        this.activity = sport;
    }

    /**
     * @return id in String format
     * most of the time location
     */
    public String getId() {
        return id;
    }

    /**
     * @param id in String format
     * most of the time location
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return a list of laps in Lap format
     */
    public List<Lap> getLap() {
        return lap;
    }

    /**
     * @param laps in Lap format
     * set a list of laps
     */
    public void setLap(List<Lap> laps) {
        this.lap = laps;
    }

    /**
     * @param laps in Lap format
     * a list of laps
     * @return a average lap in Lap format
     */
    public Lap averageLap(List<Lap> laps) {
        LocalDateTime startTime = null;
        Double totalTimeSeconds = 0.0;
        Double distanceMeters = 0.0 ;
        Double maximumSpeed = 0.0;
        Integer calories = 0;
        double averageHeartRateBpm = 0;
        Integer maximumHeartRateBpm = 0;
        for (Lap value : laps) {
            lapCounter++;
            startTime = laps.get(0).getStartTime();
            totalTimeSeconds += value.getTotalTimeSeconds();
            if (value.getDistanceMetersTracks() != null) {
                distanceMeters += value.getDistanceMetersTracks();
            }
            if (value.getMaximumSpeed() != null && maximumSpeed < value.getMaximumSpeed()) {
                maximumSpeed = value.getMaximumSpeed();
            }
            calories += value.getCalories();
            averageHeartRateBpm += value.getAverageHeartRateBpm();

            if (value.getMaximumHeartRateBpm() != null && maximumHeartRateBpm < value.getMaximumHeartRateBpm()) {
                maximumHeartRateBpm = value.getMaximumHeartRateBpm();
            }
        }
        
        return new Lap(startTime, totalTimeSeconds, distanceMeters, maximumSpeed,
                calories, averageHeartRateBpm/lapCounter, maximumHeartRateBpm );

    }


    /**
     * @return String of the activity elements
     */
    @Override
    public String toString () {
        return "Activity " + this.activity + "\n ID " + this.id + "\n Lap" +  this.lap;
    }

    /**
     * @param graphFilter
     * @return
     */
    public Double showInGraph(Filter[] graphFilter) {
    	if (graphFilter[0].getState()) { //Distance
    		return this.averageLap(this.getLap()).getDistanceMetersTracks();
    	} else if (graphFilter[1].getState()) { //max bpm
    		return (double) this.averageLap(this.getLap()).getMaximumHeartRateBpm();
    	} else if(graphFilter[2].getState()) { //speed
    		return this.averageLap(this.getLap()).getMaximumSpeed()*60;
    	}
    	return 0.0;
    }

    /**
     * @param sportsFilter
     * @param distanceFilter
     * @return
     */
    public boolean showInGui(Filter[] sportsFilter, Filter[] distanceFilter) {
    	boolean sportOK = false;
    	boolean distanceOK = false;
    	if (sportsFilter[0].getState())
    		sportOK = true;
    	for(int j = 1; j < sportsFilter.length; j++) {
            if (this.getActivity().equals(sportsFilter[j].getName()) && sportsFilter[j].getState()) {
                sportOK = true;
                break;
            }
    	}

        for (Filter filter : distanceFilter) {
            if (filter.getState() && filter.getNumber() >= this.averageLap(this.getLap()).getDistanceMetersTracks())
                distanceOK = true;
        }
    	return sportOK && distanceOK;
    }
}
