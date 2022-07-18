package sports;

import java.time.LocalDateTime;


/**
 * TrackPoint is a part of the GPX Data Structure
 * the GPX Data Structure is structured hierarchically
 * @author Susanne Gumplmayr
 */
public class TrackPoint {


    private Double trackPointLat;
    private Double trackPointLon;
    private Double elem;
    private LocalDateTime time;


    public TrackPoint () {

    }

    public TrackPoint(Double elem) {
        this.elem = elem;
        this.time = null;
    }

    /**
     * @return elem in Double format
     */
    public Double getElem() {
        return elem;
    }

    /**
     * @param elem in Double format
     * set elem in Double format
     */
    public void setElem(Double elem) {
        this.elem = elem;
    }

    /**
     * @return time in LocalDateTime format
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * @param time in LocalDateTime format
     * set time in LocalDateTime format
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    /**
     * @return track point latitude in Double format
     */
    public Double getTrackPointLat() {
        return trackPointLat;
    }

    /**
     * @param trackPointLat in Double format
     * set time in LocalDateTime format
     */
    public void setTrackPointLat(Double trackPointLat) {
        this.trackPointLat = trackPointLat;
    }

    /**
     * @return track point latitude in Double format
     */
    public Double getTrackPointLon() {
        return trackPointLon;
    }

    /**
     * @param trackPointLon  in Double format
     * set time in LocalDateTime format
     */
    public void setTrackPointLon(Double trackPointLon) {
        this.trackPointLon = trackPointLon;
    }

    /**
     * @return String of the track point elements
     */
    @Override
    public String toString() {
        return "TrackPoint{" +
                "trackPointLat=" + trackPointLat +
                ", trackPointLon=" + trackPointLon +
                ", elem=" + elem +
                ", time=" + time +
                '}';
    }
}
