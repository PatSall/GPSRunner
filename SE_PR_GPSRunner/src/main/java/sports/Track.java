package sports;


import java.time.LocalDateTime;

/**
 * Track ist Teil der Struktur der TCX Files,
 * welche hierarchisch aufgebaut sind.
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
     * @return Time im LocalDateTime Format
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * @param time setzt Time im Double Format
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }


    /**
     * @return liefert Altitude in der Einheit Meter abgebildet im Double Format
     */
    public Double getAltitudeMeters() {
        return altitudeMeters;
    }

    /**
     * @param altitudeMeters setzt Altitude in der Einheit Meter abgebildet im Double Format
     */
    public void setAltitudeMeters(Double altitudeMeters) {
        this.altitudeMeters = altitudeMeters;
    }


    /**
     * @return liefert DistanceTracks in der Einheit Meter abgebildet im Double Format
     */
    public Double getDistanceMetersTracks() {
        return distanceMetersTracks;
    }


    /**
     * @param distanceMetersTracks setzt DistanceTracks in der Einheit Meter abgebildet im Double Format
     */
    public void setDistanceMetersTracks(Double distanceMetersTracks) {
        this.distanceMetersTracks = distanceMetersTracks;
    }

    /**
     * @return liefert HeartRate in der Einheit Bpm abgebildet im Integer Format
     */
    public Integer getHeartRateBpm() {
        return heartRateBpm;
    }

    /**
     * @param heartRateBpm setzt HeartRate in der Einheit Bpm abgebildet im Integer Format
     */
    public void setHeartRateBpm(Integer heartRateBpm) {
        this.heartRateBpm = heartRateBpm;
    }


    /**
     * @return
     */
    public Position getPosition() {
        return position;
    }

    /**
     * @param position setzt position im Position Format
     */
    public void setPosition(Position position) {
        this.position = position;
    }


    /**
     * @return liefert extension im Extension Format
     */
    public Extension getExtension() {
        return extension;
    }

    /**
     * @param extension setzt extension
     */
    public void setExtension(Extension extension) {
        this.extension = extension;
    }


    /**
     * @return String bestehend aus allen Track Attributen
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
