package sports;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MapTrackGPXObjectHandlerSax extends DefaultHandler {


    private final StringBuilder currentValue = new StringBuilder();
    List<TrackGPS> tracks;
    TrackGPS track;
    List<TrackSegment> segments ;
    TrackGPS currentTrack;
    TrackSegment currentSegment;

    List<TrackPoint> trackPoints;
    TrackPoint currentTrackPoint;


    public List<TrackGPS> getTrackGPsResult() {
        return tracks;
    }

    public TrackGPS getTrackGPSResult() {
        return track;
    }

    @Override
    public void startDocument() {
        track = new TrackGPS();
        tracks = new ArrayList<>();
    }

    @Override
    public void startElement(
            String uri,
            String localName,
            String qName,
            Attributes attributes) {

        // reset the tag value
        currentValue.setLength(0);

        if (qName.equalsIgnoreCase("trk")) {
            // new activity
            currentTrack = new TrackGPS();
            segments = new ArrayList<>();
        }

        if (qName.equalsIgnoreCase("trkseg")) {
            currentSegment = new TrackSegment();
            trackPoints = new ArrayList<>();
            segments.add(currentSegment);
            currentTrack.setTrackSegments(segments);
            return;
        }

        if (qName.equalsIgnoreCase("trkpt")) {

            currentTrackPoint = new TrackPoint();
            currentTrackPoint.setTrackPointLat(Double.parseDouble(attributes.getValue("lat")));
            currentTrackPoint.setTrackPointLon(Double.parseDouble(attributes.getValue("lon")));
            trackPoints.add(currentTrackPoint);
            currentSegment.setTrackPoint(trackPoints);
            return;
        }
    }
    public void endElement(String uri,
                           String localName,
                           String qName) {

        if (qName.equalsIgnoreCase("name")) {
            currentTrack.setName(currentValue.toString());
            return;
        }

       /* if (qName.equalsIgnoreCase("desc")) {
            currentTrack.setDate(LocalDateTime.parse()currentValue.toString());
            return;
        }*/

        if (qName.equalsIgnoreCase("ele")) {
            currentTrackPoint.setElem(Double.parseDouble(currentValue.toString()));
            return;
        }

        if (qName.equalsIgnoreCase("time")) {
            currentTrackPoint.setTime(LocalDateTime.parse(currentValue.toString().substring(0, 19)));
            return;
        }

       if (qName.equalsIgnoreCase("trk")) {
            currentTrack.setTrackSegments(segments);
            track = currentTrack;
            tracks.add(currentTrack);
       }


    }

    public void endDocument (String uri,
                             String localName,
                             String qName,
                             Attributes attributes) {
        if (qName.equalsIgnoreCase("trk")) {
            return;
        }
    }


    public void characters(char ch[], int start, int length) {
        currentValue.append(ch, start, length);
    }




}

