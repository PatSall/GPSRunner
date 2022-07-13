package sports;

import java.util.Date;
import java.util.List;

/**
 * TrackGPX ist Teil der Struktur der GPX Files,
 * welche hierarchisch aufgebaut sind.
 * @author Susanne Gumplmayr
 */
public class TrackGPX {

    private String name;
    private Date date;
    private List<TrackSegment> trackSegments;


    public TrackGPX() {}

    /**
     * @return liefert Namen in String Format;
     * meist Ortnamen
     */
    public String getName() {
        return name;
    }

    /**
     * @return liefert Datum in Format date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @return liefert eine Liste von Track Segmenten ab
     */
    public List<TrackSegment> getTrackSegments() {
        return trackSegments;
    }

    /**
     * @param name setzt Namen in String Format;
     * meist Ortsnamen
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param date setzt in Date Format
     */
    public void setDate(Date  date) {
        this.date = date;
    }

    /**
     * @param trackSegments setzt eine Liste von Track Segmenten
     */
    public void setTrackSegments(List<TrackSegment> trackSegments) {
        this.trackSegments = trackSegments;
    }

    /**
     * @return String bestehend aus allen TrackGPX Attributen
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
