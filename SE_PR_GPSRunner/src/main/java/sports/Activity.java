package sports;

import java.time.LocalDateTime;
import java.util.*;

import gui.GUI.Filter;


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
        double averageHeartRateBpm = 0;
        Integer maximumHeartRateBpm = 0;

        for(int i = 0; i < laps.size(); i++) {
            lapCounter++;
            startTime = laps.get(0).getStartTime();
            totalTimeSeconds += laps.get(i).getTotalTimeSeconds();
            if(laps.get(i).getDistanceMetersTracks() != null) {
            	distanceMeters += laps.get(i).getDistanceMetersTracks();
            }
            if (laps.get(i).getMaximumSpeed() != null && maximumSpeed < laps.get(i).getMaximumSpeed()){
                maximumSpeed = laps.get(i).getMaximumSpeed();
            }
            calories += laps.get(i).getCalories();
//            if(laps.get(i).getAverageHeartRateBpm() != null) {
            	averageHeartRateBpm += laps.get(i).getAverageHeartRateBpm();
            	System.out.println(lapCounter);
//            }
            if (laps.get(i).getMaximumHeartRateBpm() != null && maximumHeartRateBpm < laps.get(i).getMaximumHeartRateBpm()) {
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
    
    public Double showInGraph(Filter[] graphFilter) {
    	if (graphFilter[0].getState()) { //Distance
    		return this.averageLap(this.getLap()).getDistanceMetersTracks();
    	} else if (graphFilter[1].getState()) { //max bpm
    		return (double) this.averageLap(this.getLap()).getMaximumHeartRateBpm();
    	} else if(graphFilter[2].getState()) { //speed
    		return this.averageLap(this.getLap()).getMaximumSpeed();
    	}
    	return 0.0;
    }
    
    public boolean showInGui(Filter[] sportsFilter, Filter[] distanceFilter) {
    	boolean sportOK = false;
    	boolean distanceOK = false;
    	if (sportsFilter[0].getState())
    		sportOK = true;
    	for(int j = 1; j < sportsFilter.length; j++) {
    		if (this.getActivity().equals(sportsFilter[j].getName()) && sportsFilter[j].getState())
    			sportOK = true;
    	}
    	
    	for (int i = 0; i < distanceFilter.length; i++) {
    		if (distanceFilter[i].getState() && distanceFilter[i].getNumber() >= this.averageLap(this.getLap()).getDistanceMetersTracks())
    			distanceOK = true;   		 
    	}
    	return sportOK && distanceOK;
    }
}
