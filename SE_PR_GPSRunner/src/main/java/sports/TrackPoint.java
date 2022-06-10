package sports;

import java.time.LocalDateTime;


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

    public Double getElem() {
        return elem;
    }

    public void setElem(Double elem) {
        this.elem = elem;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Double getTrackPointLat() {
        return trackPointLat;
    }

    public void setTrackPointLat(Double trackPointLat) {
        this.trackPointLat = trackPointLat;
    }

    public Double getTrackPointLon() {
        return trackPointLon;
    }

    public void setTrackPointLon(Double trackPointLon) {
        this.trackPointLon = trackPointLon;
    }

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
