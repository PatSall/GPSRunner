package sports;

import java.time.LocalDateTime;


/**
 * TrackPoint ist Teil der Struktur der GPX Files,
 * welche hierarchisch aufgebaut sind.
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
     * @return elem
     */
    public Double getElem() {
        return elem;
    }

    /**
     * @param elem setzt elem im Double Format
     */
    public void setElem(Double elem) {
        this.elem = elem;
    }

    /**
     * @return LocalDateTime
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * @param time setzt Zeit im LocalDateTime Format
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    /**
     * @return Double trackPointLatitude
     */
    public Double getTrackPointLat() {
        return trackPointLat;
    }

    /**
     * @param trackPointLat setzt TrackPoint Latitude
     */
    public void setTrackPointLat(Double trackPointLat) {
        this.trackPointLat = trackPointLat;
    }

    /**
     * @return Double trackPointLongitude
     */
    public Double getTrackPointLon() {
        return trackPointLon;
    }

    /**
     * @param trackPointLon setzt TrackPoint Longitude
     */
    public void setTrackPointLon(Double trackPointLon) {
        this.trackPointLon = trackPointLon;
    }

    /**
     * @return String bestehend aus allen TrackPoint Attributen
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
