package sports;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

public class Track {
    private LocalDateTime time;

    private  Double altitudeMeters;
    private  Double distanceMetersTracks;
    private  Integer heartRateBpm;
    private Position position;
    private Extension extension;


    public Track() {
        this.position = new Position();
        this.extension = new Extension();
    }

    public Track(LocalDateTime time, Double latitudeDegrees, Double longitudeDegrees, Double altitudeMeters, Double distanceMeters, Integer heartRateBpm, Double speed, Integer runCadence) {
        this.time = time;

        this.altitudeMeters = altitudeMeters;
        this.distanceMetersTracks = distanceMeters;
        this.heartRateBpm = heartRateBpm;
        this.position = new Position();
        this.extension = new Extension();
    }

    public Track(LocalDateTime startTime, Double totalTimeSeconds, Double distanceMeters, Double maximumSpeed, Integer calories, Integer averageHeartRateBpm, Integer maximumHeartRateBpm, String intensity, String triggerMethod, Timestamp time, Double latitudeDegrees, Double latitudeDegrees1, Double altitudeMeters, Double distanceMeters1, Integer heartRateBpm, Double speed, Integer runCadence) {
        //super(startTime, totalTimeSeconds, distanceMeters, maximumSpeed, calories, averageHeartRateBpm, maximumHeartRateBpm, intensity, triggerMethod);


        this.altitudeMeters = altitudeMeters;
        this.distanceMetersTracks = distanceMeters1;
        this.heartRateBpm = heartRateBpm;
        this.position = new Position();
        this.extension = new Extension();

    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }


    public Double getAltitudeMeters() {
        return altitudeMeters;
    }

    public void setAltitudeMeters(Double altitudeMeters) {
        this.altitudeMeters = altitudeMeters;
    }


    public Double getDistanceMetersTracks() {
        return distanceMetersTracks;
    }


    public void setDistanceMetersTracks(Double distanceMetersTracks) {
        this.distanceMetersTracks = distanceMetersTracks;
    }

    public Integer getHeartRateBpm() {
        return heartRateBpm;
    }

    public void setHeartRateBpm(Integer heartRateBpm) {
        this.heartRateBpm = heartRateBpm;
    }


    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

//    public void addPosition(Position trackpoint) {
//        this.position.add(trackpoint);
//    }

    public Extension getExtension() {
        return extension;
    }

    public void setExtension(Extension extension) {
        this.extension = extension;
    }

//    public void addExtension(Extension trackpoint) {
//        this.extension.add(trackpoint);
//    }

    @Override
    public String toString() {
        return "Track{" +
                "time=" + time +
                ", altitudeMeters=" + altitudeMeters +
                ", distanceMetersTracks=" + distanceMetersTracks +
                ", heartRateBpm=" + heartRateBpm +
                ", position=" + position +
                ", extension=" + extension +
                '}';
    }
}
