import gui.GUI;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import sports.Activity;
import sports.Lap;
import sports.Track;

import javax.xml.parsers.*;
import java.awt.*;
import java.io.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

import java.util.ArrayList;
import java.io.File;
import java.util.List;

public class Controller {
    public static void main(String[] args) {
    	
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				GUI g = new GUI();
				g.setVisible(true);
			}
		});
    	
        System.out.println("READ XML File with JAXB");


        List<Activity> activities = parseActivitiesXML();
        for (Activity a: activities) {
            //System.out.println(a.getActivity());
            //System.out.println(a.getId());
            System.out.println();
            System.out.println(a.toString());
            //System.out.println()

        }

    }

    private static List<Activity> parseActivitiesXML() {
        List<Activity> activities = new ArrayList<>();
        List<Lap> laps = new ArrayList<>();
        List<Track> tracks = new ArrayList<>();
        Activity activity = null;
        Lap lap = null;
        Track track = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document document = null;
        try {
            document = builder.parse(new File("C://temp//activity.xml"));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        document.getDocumentElement().normalize();
        NodeList nList = document.getElementsByTagName("Activity");
        NodeList lapList = document.getElementsByTagName("Lap");
        NodeList trackList = document.getElementsByTagName("Trackpoint");
        for (int temp = 0; temp < nList.getLength(); temp++)
        {
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                //Create new Activity Object
                activity = new Activity();
                activity.setActivity(eElement.getAttribute("Sport"));
                activity.setId(eElement.getElementsByTagName("Id").item(temp).getTextContent());

                for (int i = 0; i < lapList.getLength(); i++) {
                    Node lapNode = lapList.item(i);
                    if (lapNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element lElement = (Element) node;
                        //Create new Lap Object
                        lap = new Lap();
                       //System.out.println(lElement.getAttributeNS("Lap", "StartTime"));
                        lap.setMaximumSpeed(Double.parseDouble(lElement.getElementsByTagName("MaximumSpeed").item(i).getTextContent()));
                        lap.setTotalTimeSeconds(Double.parseDouble(lElement.getElementsByTagName("TotalTimeSeconds").item(i).getTextContent()));
                        lap.setDistanceMetersTracks(Double.parseDouble(lElement.getElementsByTagName("DistanceMeters").item(i).getTextContent()));
                        lap.setCalories(Integer.parseInt(lElement.getElementsByTagName("Calories").item(i).getTextContent()));
                        lap.setAverageHeartRateBpm(Integer.parseInt(
                                lElement.getElementsByTagName("AverageHeartRateBpm").item(i).getTextContent().trim()
                        ));
                        lap.setMaximumHeartRateBpm(Integer.parseInt(
                                lElement.getElementsByTagName("MaximumHeartRateBpm").item(i).getTextContent().trim()
                        ));
                        lap.setIntensity(lElement.getElementsByTagName("Intensity").item(i).getTextContent());
                        lap.setTriggerMethod(lElement.getElementsByTagName("TriggerMethod").item(i).getTextContent());

                    }
                    for (int j = 0; j < lapList.getLength(); j++) {
                        Node trackNode = lapList.item(j);
                        if (trackNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element tElement = (Element) node;
                            //Create new Track Object
                            track = new Track();
                            track.setTime(LocalDateTime.parse(tElement.getElementsByTagName("Time").item(j).getTextContent().substring(0,19)));
                            track.setLatitudeDegrees(Double.parseDouble(tElement.getElementsByTagName("LatitudeDegrees").item(i).getTextContent()));
                            track.setLongitudeDegrees(Double.parseDouble(tElement.getElementsByTagName("LongitudeDegrees").item(i).getTextContent()));
                            track.setAltitudeMeters(Double.parseDouble(tElement.getElementsByTagName("AltitudeMeters").item(i).getTextContent()));
                            track.setDistanceMetersTracks(Double.parseDouble(tElement.getElementsByTagName("DistanceMeters").item(i).getTextContent()));
                            track.setHeartRateBpm(Integer.parseInt(tElement.getElementsByTagName("HeartRateBpm").item(i).getTextContent().trim()));
                            track.setSpeed(Double.parseDouble(tElement.getElementsByTagName("Speed").item(i).getTextContent()));
                            track.setRunCadence(Integer.parseInt(tElement.getElementsByTagName("RunCadence").item(i).getTextContent()));
                        }
                        tracks.add(track);

                    }
                    lap.setTrack(tracks);
                    laps.add(lap);

                }
                activity.setLap(laps);
                activities.add(activity);
                //activities.add(laps);
            }
        }
        return activities;
    }
}



