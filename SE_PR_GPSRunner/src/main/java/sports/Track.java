package sports;


import java.time.LocalDateTime;


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


    public Extension getExtension() {
        return extension;
    }

    public void setExtension(Extension extension) {
        this.extension = extension;
    }


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
