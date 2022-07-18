package sports;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * GPX sax parser class
 * @author Susanne Gumplmayr
 */
public class MapTrackGPXObjectHandlerSax extends DefaultHandler {
    private final StringBuilder currentValue = new StringBuilder();
    List<TrackGPX> tracks;
    TrackGPX track;
    List<TrackSegment> segments ;
    TrackGPX currentTrack;
    TrackSegment currentSegment;

    List<TrackPoint> trackPoints;
    TrackPoint currentTrackPoint;


    /**
     * @return a list of tracks in TrackGPX format
     */
    public List<TrackGPX> getTrackGPsResult() {
        return tracks;
    }

    /**
     * @return track in TrackGPX format
     */
    public TrackGPX getTrackGPSResult() {
        return track;
    }

    /**
     * generate a new trackGPX and
     * a track ArrayList
     */
    @Override
    public void startDocument() {
        track = new TrackGPX();
        tracks = new ArrayList<>();
    }

    /**
     * @param uri        The Namespace URI, or the empty string if the
     *                   element has no Namespace URI or if Namespace
     *                   processing is not being performed.
     * @param localName  The local name (without prefix), or the
     *                   empty string if Namespace processing is not being
     *                   performed.
     * @param qName      The qualified name (with prefix), or the
     *                   empty string if qualified names are not available.
     * @param attributes The attributes attached to the element.  If
     *                   there are no attributes, it shall be an empty
     *                   Attributes object.
     */
    @Override
    public void startElement(
            String uri,
            String localName,
            String qName,
            Attributes attributes) {

        currentValue.setLength(0);

        if (qName.equalsIgnoreCase("trk")) {
            currentTrack = new TrackGPX();
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
        }
    }

    /**
     * @param uri       The Namespace URI, or the empty string if the
     *                  element has no Namespace URI or if Namespace
     *                  processing is not being performed.
     * @param localName The local name (without prefix), or the
     *                  empty string if Namespace processing is not being
     *                  performed.
     * @param qName     The qualified name (with prefix), or the
     *                  empty string if qualified names are not available.
     */
    public void endElement(String uri,
                           String localName,
                           String qName) {

        if (qName.equalsIgnoreCase("name")) {
            currentTrack.setName(currentValue.toString());
            return;
        }


        if (qName.equalsIgnoreCase("ele")) {
            currentTrackPoint.setElem(Double.parseDouble(currentValue.toString()));
            return;
        }

        if (qName.equalsIgnoreCase("time")) {
            currentTrackPoint.setTime(LocalDateTime.parse(currentValue.substring(0, 19)));
            return;
        }

       if (qName.equalsIgnoreCase("trk")) {
            currentTrack.setTrackSegments(segments);
            track = currentTrack;
            tracks.add(currentTrack);
       }


    }

    /**
     * @param ch     The characters.
     * @param start  The start position in the character array.
     * @param length The number of characters to use from the
     *               character array.
     */
    public void characters(char[] ch, int start, int length) {
        currentValue.append(ch, start, length);
    }




}

