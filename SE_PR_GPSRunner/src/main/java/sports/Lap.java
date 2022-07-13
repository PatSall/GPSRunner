package sports;

import java.time.LocalDateTime;
import java.util.*;


/**
 * Lap ist Teil der Struktur der TCX Files,
 * welche hierarchisch aufgebaut sind.
 * @author Susanne Gumplmayr
 */
public class Lap {

    private LocalDateTime startTime;

    private Double totalTimeSeconds;

    private Double distanceMeters;

    private Double maximumSpeed;

    private Integer calories;

    private double averageHeartRateBpm;

    //private Integer maximumHeartRateBpm;
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
     * @return liefert Start Time im LocalDateTime Format
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * @return liefert die Total Time in der Einheit Sekunden im Double Format
     */
    public Double getTotalTimeSeconds() {
        return totalTimeSeconds;
    }


    /**
     * @return liefert die Distance Track in der Einheit Meter im Double Format
     */
    public Double getDistanceMetersTracks() {
        return distanceMeters;
    }


    /**
     * @return liefert die maximal Speed im Double Format
     */
    public Double getMaximumSpeed() {
        return maximumSpeed;
    }


    /**
     * @return liefert die Kalorien im Double Integer
     */
    public Integer getCalories() {
        return calories;
    }

    /**
     * @return  liefert die AverageHeartRate in der Einheit Bpm im Double Format
     */
    public double getAverageHeartRateBpm() {
        return averageHeartRateBpm;
    }


    /**
     * @return liefert die MaximumHeartRate in der Einheit Bpm im Double Format
     */
    public Integer getMaximumHeartRateBpm() {
        return maximumHeartRateBpm;
    }


    /**
     * @return liefert die Intensity im String Format
     */
    public String getIntensity() {
        return intensity;
    }


    /**
     * @return liefert die Trigger im String Format
     */
    public String getTriggerMethod() {
        return triggerMethod;
    }

    /**
     * @param startTime setzt Start Time im LocalDateTime Format
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * @param totalTimeSeconds setzt die Total Time in der Einheit Sekunden im Double Format
     */
    public void setTotalTimeSeconds(Double totalTimeSeconds) {
        this.totalTimeSeconds = totalTimeSeconds;
    }


    /**
     * @param distanceMetersTracks setzt die Distance Track in der Einheit Meter im Double Format
     */
    public void setDistanceMetersTracks(Double distanceMetersTracks) {
        this.distanceMeters = distanceMetersTracks;
    }


    /**
     * @param maximumSpeed liefert die maximal Speed im Double Format
     */
    public void setMaximumSpeed(Double maximumSpeed) {
        this.maximumSpeed = maximumSpeed;
    }


    /**
     * @param calories setzt die Kalorien im Double Integer
     */
    public void setCalories(Integer calories) {
        this.calories = calories;
    }


    /**
     * @param averageHeartRateBpm setzt die AverageHeartRate in der Einheit Bpm im Double Format
     */
    public void setAverageHeartRateBpm(Integer averageHeartRateBpm) {
        this.averageHeartRateBpm = averageHeartRateBpm;
    }

    /**
     * @param maximumHeartRateBpm setzt die MaximumHeartRate in der Einheit Bpm im Double Format
     */
    public void setMaximumHeartRateBpm(Integer maximumHeartRateBpm) {
        this.maximumHeartRateBpm = maximumHeartRateBpm;
    }

    /**
     * @param intensity setzt die Intensity im String Format
     */
    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }


    /**
     * @param triggerMethod setzt die Trigger im String Format
     */
    public void setTriggerMethod(String triggerMethod) {
        this.triggerMethod = triggerMethod;
    }

    /**
     * @return liefert eine Liste von Tracks zurück
     */
    public List<Track> getTrack() {
        return track;
    }

    /**
     * @param track setzt eine Liste von Tracks
     */
    public void setTrack(List<Track> track) {
        this.track = track;
    }


    /**
     * @return String bestehend aus allen Lap Attributen
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
