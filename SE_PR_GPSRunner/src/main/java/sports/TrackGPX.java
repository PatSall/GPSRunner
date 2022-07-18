package sports;

import java.util.Date;
import java.util.List;

/**
 * TrackGPX is a part of the GPX Data Structure
 * the GPX Data Structure is structured hierarchically
 * @author Susanne Gumplmayr
 */
public class TrackGPX {

    private String name;
    private Date date;
    private List<TrackSegment> trackSegments;


    public TrackGPX() {}

    /**
     * @return name in String format
     * most of the time location name
     */
    public String getName() {
        return name;
    }

    /**
     * @return date in Date format
     */
    public Date getDate() {
        return date;
    }

    /**
     * @return a track segment list in TrackSegment format
     */
    public List<TrackSegment> getTrackSegments() {
        return trackSegments;
    }

    /**
     * @param name in String format
     * most of the time location name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param date in Date format
     * set date
     */
    public void setDate(Date  date) {
        this.date = date;
    }

    /**
     * @param trackSegments is a list in TrackSegment format
     * set track segments list
     */
    public void setTrackSegments(List<TrackSegment> trackSegments) {
        this.trackSegments = trackSegments;
    }

    /**
     * @return String of the track gpx elements
     */
    @Override
    public String toString() {
        return "TrackGPS{" +
                "name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", trackSegment=" + trackSegments +
                '}';
    }
}
