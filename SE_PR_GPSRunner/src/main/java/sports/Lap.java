package sports;

import java.time.LocalDateTime;
import java.util.*;


public class Lap {

    private LocalDateTime startTime;

    private Double totalTimeSeconds;

    private Double distanceMeters;

    private Double maximumSpeed;

    private Integer calories;

    private Integer averageHeartRateBpm;

    //private Integer maximumHeartRateBpm;
    private Integer maximumHeartRateBpm;
    private String intensity;

    private String triggerMethod;

    private List<Track> track;

    public Lap() {

    }

    public Lap(LocalDateTime startTime, Double totalTimeSeconds, Double distanceMeters, Double maximumSpeed, Integer calories, Integer averageHeartRateBpm, Integer maximumHeartRateBpm, String intensity, String triggerMethod) {
        this.startTime = startTime;
        this.totalTimeSeconds = totalTimeSeconds;
        this.distanceMeters = distanceMeters;
        this.maximumSpeed = maximumSpeed;
        this.calories = calories;
        this.averageHeartRateBpm = averageHeartRateBpm;
        this.maximumHeartRateBpm = maximumHeartRateBpm;
        this.intensity = intensity;
        this.triggerMethod = triggerMethod;
    }



    public LocalDateTime getStartTime() {
        return startTime;
    }

    //@XmlElement(name = "TotalTimeSeconds")
    public Double getTotalTimeSeconds() {
        return totalTimeSeconds;
    }


    public Double getDistanceMetersTracks() {
        return distanceMeters;
    }



    public Double getMaximumSpeed() {
        return maximumSpeed;
    }


    public Integer getCalories() {
        return calories;
    }

    public Integer getAverageHeartRateBpm() {
        return averageHeartRateBpm;
    }



    public Integer getMaximumHeartRateBpm() {
        return maximumHeartRateBpm;
    }


    public String getIntensity() {
        return intensity;
    }


    public String getTriggerMethod() {
        return triggerMethod;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setTotalTimeSeconds(Double totalTimeSeconds) {
        this.totalTimeSeconds = totalTimeSeconds;
    }


    public void setDistanceMetersTracks(Double distanceMetersTracks) {
        this.distanceMeters = distanceMetersTracks;
    }


    public void setMaximumSpeed(Double maximumSpeed) {
        this.maximumSpeed = maximumSpeed;
    }


    public void setCalories(Integer calories) {
        this.calories = calories;
    }


    public void setAverageHeartRateBpm(Integer averageHeartRateBpm) {
        this.averageHeartRateBpm = averageHeartRateBpm;
    }

    public void setMaximumHeartRateBpm(Integer maximumHeartRateBpm) {
        this.maximumHeartRateBpm = maximumHeartRateBpm;
    }
    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }


    public void setTriggerMethod(String triggerMethod) {
        this.triggerMethod = triggerMethod;
    }

    public List<Track> getTrack() {
        return track;
    }

    public void setTrack(List<Track> track) {
        this.track = track;
    }

    @Override
    public String toString () {
        return  " StartTime " +

                this.startTime + "\n  totalTimeSeconds " +
                this.totalTimeSeconds + "\n  distanceMeters " +
                this.distanceMeters + "\n  maximumSpeed " +
                this.maximumSpeed + " \n calories " +
                this.calories + "\n  averageHeartRateBpm " +
                this.averageHeartRateBpm + "\n  maximumHeartRateBpm " +
                this.maximumHeartRateBpm + "\n  intensity " +
                this.intensity + "\n  triggerMethod " +
                this.triggerMethod + "\n  tracks " + this.track;
    }


}
