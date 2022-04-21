package sports;

import java.time.LocalDateTime;

public class TrackSegment extends TrackGPS {

    private Double trackPointLat;
    private Double trackPointLon;
    private Double elem;
    private LocalDateTime time;

    public TrackSegment() {
        super();
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

    @Override
    public String toString() {
        return  "TrackSegment{" +
                "trackPointLat=" + trackPointLat +
                ", trackPointLon=" + trackPointLon +
                ", elem=" + elem +
                ", time=" + time +
                '}';
    }
}
