package sports;

import java.time.LocalDateTime;
import java.util.*;


/**
 * Lap is a part of the TCX Data Structure
 * the TCX Data Structure is structured hierarchically
 * @author Susanne Gumplmayr
 */
public class Lap {

    private LocalDateTime startTime;

    private Double totalTimeSeconds;

    private Double distanceMeters;

    private Double maximumSpeed;

    private Integer calories;

    private double averageHeartRateBpm;

    private Integer maximumHeartRateBpm;

    private String intensity;

    private String triggerMethod;

    private List<Track> track;

    public Lap() {

    }

    public Lap(LocalDateTime startTime, Double totalTimeSeconds, Double distanceMeters, Double maximumSpeed, Integer calories, double averageHeartRateBpm, Integer maximumHeartRateBpm, String intensity, String triggerMethod) {
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

    public Lap(LocalDateTime startTime, Double totalTimeSeconds, Double distanceMeters, Double maximumSpeed, Integer calories, double averageHeartRateBpm, Integer maximumHeartRateBpm) {
        this.startTime = startTime;
        this.totalTimeSeconds = totalTimeSeconds;
        this.distanceMeters = distanceMeters;
        this.maximumSpeed = maximumSpeed;
        this.calories = calories;
        this.averageHeartRateBpm = averageHeartRateBpm;
        this.maximumHeartRateBpm = maximumHeartRateBpm;
    }

    /**
     * @return start time in LocalDateTime format
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * @return total time in Double format
     * unit in seconds
     */
    public Double getTotalTimeSeconds() {
        return totalTimeSeconds;
    }


    /**
     * @return distance tracks in Double format
     * unit in meters
     */
    public Double getDistanceMetersTracks() {
        return distanceMeters;
    }


    /**
     * @return maximum speed in Double format
     */
    public Double getMaximumSpeed() {
        return maximumSpeed;
    }


    /**
     * @return calories in Integer format
     */
    public Integer getCalories() {
        return calories;
    }

    /**
     * @return average heart rate in Double format
     * unit in BPM
     */
    public double getAverageHeartRateBpm() {
        return averageHeartRateBpm;
    }


    /**
     * @return maximum heart rate in Integer format
     * unit in BPM
     */
    public Integer getMaximumHeartRateBpm() {
        return maximumHeartRateBpm;
    }


    /**
     * @return intensity in String format
     */
    public String getIntensity() {
        return intensity;
    }


    /**
     * @return trigger method in String format
     */
    public String getTriggerMethod() {
        return triggerMethod;
    }

    /**
     * @param startTime in LocalDateTime format
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * @param totalTimeSeconds in Double format
     * unit in seconds
     */
    public void setTotalTimeSeconds(Double totalTimeSeconds) {
        this.totalTimeSeconds = totalTimeSeconds;
    }


    /**
     * @param distanceMetersTracks in Double format
     * unit in meter
     */
    public void setDistanceMetersTracks(Double distanceMetersTracks) {
        this.distanceMeters = distanceMetersTracks;
    }


    /**
     * @param maximumSpeed in Double format
     */
    public void setMaximumSpeed(Double maximumSpeed) {
        this.maximumSpeed = maximumSpeed;
    }


    /**
     * @param calories in Integer format
     */
    public void setCalories(Integer calories) {
        this.calories = calories;
    }


    /**
     * @param averageHeartRateBpm in Integer format
     * unit in BPM
     */
    public void setAverageHeartRateBpm(Integer averageHeartRateBpm) {
        this.averageHeartRateBpm = averageHeartRateBpm;
    }

    /**
     * @param maximumHeartRateBpm in Integer format
     * unit in BPM
     */
    public void setMaximumHeartRateBpm(Integer maximumHeartRateBpm) {
        this.maximumHeartRateBpm = maximumHeartRateBpm;
    }

    /**
     * @param intensity in String format
     */
    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }


    /**
     * @param triggerMethod in String format
     */
    public void setTriggerMethod(String triggerMethod) {
        this.triggerMethod = triggerMethod;
    }

    /**
     * @return a list of tracks in Track format
     */
    public List<Track> getTrack() {
        return track;
    }

    /**
     * @param track in Track format
     * set a list of tracks
     */
    public void setTrack(List<Track> track) {
        this.track = track;
    }


    /**
     * @return String  of the lap elements
     */
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
