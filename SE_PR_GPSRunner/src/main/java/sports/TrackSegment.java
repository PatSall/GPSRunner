package sports;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TrackSegment  {

    private Double trackPointLat;
    private Double trackPointLon;
    private List<TrackPoint> trackPoint;

    public TrackSegment() {
        this.trackPoint = new ArrayList<>();
    }

    public TrackSegment(Double trackPointLat, Double trackPointLon) {
        this.trackPointLat = trackPointLat;
        this.trackPointLon = trackPointLon;
        this.trackPoint = new ArrayList<>();
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

    public List<TrackPoint> getTrackPoint() {
        return trackPoint;
    }

    public void setTrackPoint(List<TrackPoint> trackPoint) {
        this.trackPoint = trackPoint;
    }

    public void addTrackPoint(TrackPoint trackPoint) {
        this.trackPoint.add(trackPoint);
    }

    @Override
    public String toString() {
        return "TrackSegment{" +
                "trackPointLat=" + trackPointLat +
                ", trackPointLon=" + trackPointLon +
                ", trackPoint=" + trackPoint +
                '}';
    }
}
