package sports;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class Track extends Lap{
    private LocalDateTime time;
    private  Double latitudeDegrees;
    private  Double longitudeDegrees;
    private  Double altitudeMeters;
    private  Double distanceMetersTracks;
    private  Integer heartRateBpm;
    private  Double speed;
    private  Integer runCadence;
    
    public Track() {
        super();
    }

    public Track(LocalDateTime time, Double latitudeDegrees, Double longitudeDegrees, Double altitudeMeters, Double distanceMeters, Integer heartRateBpm, Double speed, Integer runCadence) {
        this.time = time;
        this.latitudeDegrees = latitudeDegrees;
        this.longitudeDegrees = longitudeDegrees;
        this.altitudeMeters = altitudeMeters;
        this.distanceMetersTracks = distanceMeters;
        this.heartRateBpm = heartRateBpm;
        this.speed = speed;
        this.runCadence = runCadence;
    }

    public Track(String startTime, Double totalTimeSeconds, Double distanceMeters, Double maximumSpeed, Integer calories, Integer averageHeartRateBpm, Integer maximumHeartRateBpm, String intensity, String triggerMethod, Timestamp time, Double latitudeDegrees, Double latitudeDegrees1, Double altitudeMeters, Double distanceMeters1, Integer heartRateBpm, Double speed, Integer runCadence) {
        super(startTime, totalTimeSeconds, distanceMeters, maximumSpeed, calories, averageHeartRateBpm, maximumHeartRateBpm, intensity, triggerMethod);

        this.latitudeDegrees = latitudeDegrees;
        this.longitudeDegrees = latitudeDegrees1;
        this.altitudeMeters = altitudeMeters;
        this.distanceMetersTracks = distanceMeters1;
        this.heartRateBpm = heartRateBpm;
        this.speed = speed;
        this.runCadence = runCadence;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Double getLatitudeDegrees() {
        return latitudeDegrees;
    }

    public void setLatitudeDegrees(Double latitudeDegrees) {
        this.latitudeDegrees = latitudeDegrees;
    }

    public Double getLongitudeDegrees() {
        return longitudeDegrees;
    }

    public void setLongitudeDegrees(Double longitudeDegrees) {
        this.longitudeDegrees = longitudeDegrees;
    }

    public Double getAltitudeMeters() {
        return altitudeMeters;
    }

    public void setAltitudeMeters(Double altitudeMeters) {
        this.altitudeMeters = altitudeMeters;
    }

    @Override
    public Double getDistanceMetersTracks() {
        return distanceMetersTracks;
    }

    @Override
    public void setDistanceMetersTracks(Double distanceMetersTracks) {
        this.distanceMetersTracks = distanceMetersTracks;
    }

    public Integer getHeartRateBpm() {
        return heartRateBpm;
    }

    public void setHeartRateBpm(Integer heartRateBpm) {
        this.heartRateBpm = heartRateBpm;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Integer getRunCadence() {
        return runCadence;
    }

    public void setRunCadence(Integer runCadence) {
        this.runCadence = runCadence;
    }

    @Override
    public String toString() {
        return "Track{" +
                "time=" + time +
                ", latitudeDegrees=" + latitudeDegrees +
                ", longitudeDegrees=" + longitudeDegrees +
                ", altitudeMeters=" + altitudeMeters +
                ", distanceMeters=" + distanceMetersTracks +
                ", heartRateBpm=" + heartRateBpm +
                ", speed=" + speed +
                ", runCadence=" + runCadence +
                '}';
    }
}
