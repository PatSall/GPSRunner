package sports;


import java.util.ArrayList;
import java.util.List;

/**
 * TrackSegment ist Teil der Struktur der GPX Files,
 * welche hierarchisch aufgebaut sind.
 * @author Susanne Gumplmayr
 */
public class TrackSegment  {

    private List<TrackPoint> trackPoint;


    public TrackSegment() {
        this.trackPoint = new ArrayList<>();
    }

    /**
     * @return List of TrackPoints
     */
    public List<TrackPoint> getTrackPoint() {
        return trackPoint;
    }


    /**
     * @param trackPoint setzt Track Point
     *
     */
    public void setTrackPoint(List<TrackPoint> trackPoint) {
        this.trackPoint = trackPoint;
    }


    /**
     * @param trackPoint fügt einen TrackPoint zur List of TrackPoints hinzu
     */
    public void addTrackPoint(TrackPoint trackPoint) {
        this.trackPoint.add(trackPoint);
    }


    /**
     * @return String bestehend aus allen TrackSegmenten Attributen
     */
    @Override
    public String toString() {
        return "TrackSegment{" +
                ", trackPoint=" + trackPoint +
                '}';
    }
}
