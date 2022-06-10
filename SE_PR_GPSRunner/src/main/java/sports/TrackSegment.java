package sports;


import java.util.ArrayList;
import java.util.List;

public class TrackSegment  {


    private List<TrackPoint> trackPoint;

    public TrackSegment() {
        this.trackPoint = new ArrayList<>();
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
                ", trackPoint=" + trackPoint +
                '}';
    }
}
