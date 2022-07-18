package sports;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * TCX sax parser class
 * @author Susanne Gumplmayr
 */
public class MapActivityObjectHandlerSax extends DefaultHandler {
    private final StringBuilder currentValue = new StringBuilder();
    List<Activity> activities;
    Activity activity;
    List<Lap> laps;
    Activity currentActivity;
    Lap currentLap;

    List<Track> tracks;
    Track currentTrack;

    Position positions;
    Position currentPosition;

    Extension extensions;
    Extension currentExtension;

    boolean lapDistanceMeters;
    boolean trackDistanceMeters;

    boolean lapAverageHeartRateBpm;
    boolean lapMaximumHeartRateBpm;
    boolean trackHeartRateBpm;

    boolean trackTime;

    /**
     * @return a list of activities in Activity format
     */
    public List<Activity> getActivitiesResult () {
        return activities;
    }

    /**
     * @return activity in Activity format
     */
    public Activity getActivityResult() {
        return activity;
    }

    /**
     *
     */
    @Override
    public void startDocument() {
        activity = new Activity();
        activities = new ArrayList<>();
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

        if (qName.equalsIgnoreCase("Activity")) {
            currentActivity = new Activity();
            currentActivity.setActivity(attributes.getValue("Sport"));
            laps = new ArrayList<>();
            return;
        }

        if (qName.equalsIgnoreCase("Lap")) {
            lapDistanceMeters = false;
            lapAverageHeartRateBpm = false;
            lapMaximumHeartRateBpm = false;
            currentLap = new Lap();
            currentLap.setStartTime(LocalDateTime.parse(attributes.getValue("StartTime").substring(0, 19)));

            tracks = new ArrayList<>();
            laps.add(currentLap);
            currentActivity.setLap(laps);
            return;

        }

        if (qName.equalsIgnoreCase("Trackpoint")) {
            trackDistanceMeters = false;
            trackHeartRateBpm = false;
            trackTime = false;
            currentTrack = new Track();
            tracks.add(currentTrack);
            currentLap.setTrack(tracks);
            positions = new Position();
            extensions = new Extension();
            return;
        }


        if (qName.equalsIgnoreCase("Position")) {
            currentPosition = new Position();
            currentTrack.setPosition(positions);
            return;
        }

        if (qName.equalsIgnoreCase("Extensions")) {
            currentExtension = new Extension();
            currentTrack.setExtension(extensions);
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

        if (qName.equalsIgnoreCase("Id")) {
            currentActivity.setId(currentValue.toString());
            return;
        }

        if (qName.equalsIgnoreCase("TotalTimeSeconds")) {
            currentLap.setTotalTimeSeconds(Double.parseDouble(currentValue.toString()));
            return;
        }

        if (qName.equalsIgnoreCase("DistanceMeters") && !lapDistanceMeters)  {
            currentLap.setDistanceMetersTracks(Double.parseDouble(currentValue.toString()));
            lapDistanceMeters = true;
            return;
        }
        if (qName.equalsIgnoreCase("MaximumSpeed")) {
            currentLap.setMaximumSpeed(Double.parseDouble(currentValue.toString()));
            return;
        }
        if (qName.equalsIgnoreCase("Calories")) {
            currentLap.setCalories(Integer.parseInt(currentValue.toString()));
            return;
        }

        if (qName.equalsIgnoreCase("Value") && !lapAverageHeartRateBpm && !lapMaximumHeartRateBpm)  {
            currentLap.setAverageHeartRateBpm(Integer.parseInt(currentValue.toString()));
            lapAverageHeartRateBpm = true;
            return;

        }

        if (qName.equalsIgnoreCase("Value") && lapAverageHeartRateBpm && !lapMaximumHeartRateBpm) {
            currentLap.setMaximumHeartRateBpm(Integer.parseInt(currentValue.toString()));
            lapMaximumHeartRateBpm = true;
            return;
        }
        if (qName.equalsIgnoreCase("Intensity")) {
            currentLap.setIntensity(currentValue.toString());
            return;
        }
        if (qName.equalsIgnoreCase("Distance")) {
            currentLap.setTriggerMethod(currentValue.toString());
            return;
        }

        if (qName.equalsIgnoreCase("Time") && !trackTime)  {
            currentTrack.setTime(LocalDateTime.parse(currentValue.substring(0, 19)));
            trackTime= true;
            return;
        }
        if (qName.equalsIgnoreCase("LatitudeDegrees")) {
            currentTrack.getPosition().setLatitudeDegrees(Double.parseDouble(currentValue.toString()));
            return;
        }
        if (qName.equalsIgnoreCase("LongitudeDegrees")) {
            currentTrack.getPosition().setLongitudeDegrees(Double.parseDouble(currentValue.toString()));
            return;
        }
        if (qName.equalsIgnoreCase("AltitudeMeters")) {
            currentTrack.setAltitudeMeters(Double.parseDouble(currentValue.toString()));
            return;
        }

        if (qName.equalsIgnoreCase("DistanceMeters") && !trackDistanceMeters) {
            currentTrack.setDistanceMetersTracks(Double.parseDouble(currentValue.toString()));
            trackDistanceMeters = true;
            return;
        }

        if (qName.equalsIgnoreCase("Value") &&lapAverageHeartRateBpm && lapMaximumHeartRateBpm
                && !trackHeartRateBpm) {
            currentTrack.setHeartRateBpm(Integer.parseInt(currentValue.toString()));
            trackHeartRateBpm = true;
            return;
        }

        if (qName.equalsIgnoreCase("Speed")) {
            currentTrack.getExtension().setSpeed(Double.parseDouble(currentValue.toString()));
            return;
        }
        if (qName.equalsIgnoreCase("RunCadence")) {
            currentTrack.getExtension().setRunCadence(Integer.parseInt(currentValue.toString()));
            return;
        }

        if (qName.equalsIgnoreCase("Activity")) {
            currentActivity.setLap(laps);
            activity = currentActivity;
            activities.add(currentActivity);
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
