package sports;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TrackGPS {

    private String name;
    private Date date;
    private List<TrackSegment> trackSegments;


    public TrackGPS() {}

    public TrackGPS(String name, Date date, List<TrackSegment> trackSegments) {
        this.name = name;
        this.date = date;
        this.trackSegments = trackSegments;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public List<TrackSegment> getTrackSegments() {
        return trackSegments;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date  date) {
        this.date = date;
    }

    public void setTrackSegments(List<TrackSegment> trackSegments) {
        this.trackSegments = trackSegments;
    }

    @Override
    public String toString() {
        return "TrackGPS{" +
                "name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", trackSegment=" + trackSegments +
                '}';
    }
}
