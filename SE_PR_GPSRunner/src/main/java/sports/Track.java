package sports;


import java.time.LocalDateTime;

/**
 * Track is a part of the TCX Data Structure
 * the TCX Data Structure is structured hierarchically
 * @author Susanne Gumplmayr
 */
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

    /**
     * @return time im LocalDateTime format
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * @param time in LocalDateTime format
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }


    /**
     * @return altitude meters in Double format
     * unit in m
     */
    public Double getAltitudeMeters() {
        return altitudeMeters;
    }

    /**
     * @param altitudeMeters in Double format
     * altitude meters unit in m
     */
    public void setAltitudeMeters(Double altitudeMeters) {
        this.altitudeMeters = altitudeMeters;
    }


    /**
     * @return distance meters in Double format
     * unit in m
     */
    public Double getDistanceMetersTracks() {
        return distanceMetersTracks;
    }

    /**
     * @param distanceMetersTracks in Double format
     * distance meters unit in m
     */
    public void setDistanceMetersTracks(Double distanceMetersTracks) {
        this.distanceMetersTracks = distanceMetersTracks;
    }

    /**
     * @return heart rate in Integer format
     * unit in BPM
     */
    public Integer getHeartRateBpm() {
        return heartRateBpm;
    }

    /**
     * @param heartRateBpm in Integer format
     * unit in BPM
     */
    public void setHeartRateBpm(Integer heartRateBpm) {
        this.heartRateBpm = heartRateBpm;
    }

    /**
     * @return position in Position format
     */
    public Position getPosition() {
        return position;
    }

    /**
     * @param position in Position format
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * @return extension in Extension format
     */
    public Extension getExtension() {
        return extension;
    }

    /**
     * @param extension in Extension format
     */
    public void setExtension(Extension extension) {
        this.extension = extension;
    }

    /**
     * @return String  of the track elements
     */
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
