package sports;


import java.util.ArrayList;
import java.util.List;

/**
 * TrackSegment is a part of the GPX Data Structure
 * the GPX Data Structure is structured hierarchically
 * @author Susanne Gumplmayr
 */
public class TrackSegment  {

    private List<TrackPoint> trackPoint;


    public TrackSegment() {
        this.trackPoint = new ArrayList<>();
    }

    /**
     * @return list of track points
     */
    public List<TrackPoint> getTrackPoint() {
        return trackPoint;
    }


    /**
     * @param trackPoint is a list of tracks points
     * set list of track points
     */
    public void setTrackPoint(List<TrackPoint> trackPoint) {
        this.trackPoint = trackPoint;
    }


    /**
     * @param trackPoint is a track point of the type TrackPoint
     * add a track point to the List trackPoint
    */
    public void addTrackPoint(TrackPoint trackPoint) {
        this.trackPoint.add(trackPoint);
    }


    /**
     * @return String of the track segment elements
     */
    @Override
    public String toString() {
        return "TrackSegment{" +
                ", trackPoint=" + trackPoint +
                '}';
    }
}
