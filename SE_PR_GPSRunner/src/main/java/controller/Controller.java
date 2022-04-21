package controller;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import sports.*;
import gui.*;

import javax.xml.parsers.*;
import java.awt.*;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;


public class Controller {
	public static void main(String[] args) {

		List<Activity> activities = parseActivitiesXML();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				GUI g = new GUI();
				g.setVisible(true);
				g.setActivities(activities);

			}
		});
		for (Activity a : activities) {
			System.out.println();
			// System.out.println(a.toString());

			System.out.println(a.getId());
		}

		List<TrackGPS> trackGPS = parseTracksGPS();
		for (TrackGPS t : trackGPS) {
			System.out.println();
			System.out.println(t.toString());
			System.out.println(t.getName());
		}

	}
	/*
	 * private static final DateFormat timeFormat = new SimpleDateFormat("HH:mm a");
	 * 
	 * private static final SimpleDateFormat displayFormat = new
	 * SimpleDateFormat("HH:mm");
	 */

	/*
	 * Date date = parseFormat.parse("10:30 PM");
	 * System.out.println(parseFormat.format(date) + " = " +
	 * displayFormat.format(date)); System.out.println("READ XML File with JAXB");
	 */

	private static List<TrackGPS> parseTracksGPS() {
		List<TrackGPS> trackGPSs = new ArrayList<>();
		List<TrackSegment> trackSegments = new ArrayList<>();
		TrackGPS trackGPS = null;
		TrackSegment trackSegment = null;
		NodeList nTrackList = null;
		NodeList nSegmentList = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		Document document = null;
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths
				.get("C:\\temp\\Files\\"))) {
			for (Path file : stream) {
				System.out.println(file.getFileName());
				document = builder.parse(
						"C:\\temp\\Files\\"
								+ file.getFileName());
				document.getDocumentElement().normalize();
				nTrackList = document.getElementsByTagName("trk");
				nSegmentList = document.getElementsByTagName("trkpt");
				for (int temp = 0; temp < nTrackList.getLength(); temp++) {
					Node node = nTrackList.item(temp);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) node;
						// Create new Activity Object
						trackGPS = new TrackGPS();
						trackGPS.setName(eElement.getElementsByTagName("name").item(temp).getTextContent());
						// trackGPS.setDate((timeFormat.parse(
						// eElement.getElementsByTagName("desc").item(temp).getTextContent())));
						/*
						 * System.out.println(
						 * (eElement.getElementsByTagName("desc").item(temp).getTextContent())); Date
						 * date = displayFormat.parse("11:30 PM"); System.out.println(date);
						 */
						for (int i = 0; i < nSegmentList.getLength(); i++) {
							Node trackSegmentNode = nSegmentList.item(i);
							if (trackSegmentNode.getNodeType() == Node.ELEMENT_NODE) {
								Element lElement = (Element) node;
								// Create new Lap Object
								trackSegment = new TrackSegment();
								if (lElement.getElementsByTagName("ele").item(i) != null) {
									if (!lElement.getElementsByTagName("ele").item(i).getTextContent().isEmpty()) {
										trackSegment.setElem(Double.parseDouble(
												lElement.getElementsByTagName("ele").item(i).getTextContent()));
									}
								}
								// System.out.println(lElement.getElementsByTagName("trkpt").item(i).getAttributes().getNamedItem("lon").getTextContent());

								if (lElement.getElementsByTagName("trkpt").item(i).getAttributes()
										.getNamedItem("lon") != null) {
									if (!lElement.getElementsByTagName("trkpt").item(i).getAttributes()
											.getNamedItem("lon").getTextContent().isEmpty()) {
										trackSegment.setTrackPointLon(
												Double.parseDouble(lElement.getElementsByTagName("trkpt").item(i)
														.getAttributes().getNamedItem("lon").getTextContent()));
									}
								}
								if (lElement.getElementsByTagName("trkpt").item(i).getAttributes()
										.getNamedItem("lat") != null) {
									if (!lElement.getElementsByTagName("trkpt").item(i).getAttributes()
											.getNamedItem("lat").getTextContent().isEmpty()) {
										trackSegment.setTrackPointLat(
												Double.parseDouble(lElement.getElementsByTagName("trkpt").item(i)
														.getAttributes().getNamedItem("lat").getTextContent()));
									}
								}
								if (lElement.getElementsByTagName("time").item(i) != null) {
									if (!lElement.getElementsByTagName("time").item(i).getTextContent().isEmpty()) {
										trackSegment.setTime(LocalDateTime.parse(lElement.getElementsByTagName("time")
												.item(i).getTextContent().substring(0, 19)));
										;
									}
								}
							}
							trackSegments.add(trackSegment);
						}
						trackGPS.setTrackSegments(trackSegments);
						trackGPSs.add(trackGPS);
					}
				}

			}
		} catch (IOException | DirectoryIteratorException | SAXException ex) {
			System.err.println(ex);
		}

		return trackGPSs;
	}

	private static List<Activity> parseActivitiesXML() {
		List<Activity> activities = new ArrayList<>();
		List<Lap> laps = new ArrayList<>();
		List<Track> tracks = new ArrayList<>();
		Activity activity = null;
		Lap lap = null;
		Track track = null;
		NodeList nList = null;
		NodeList lapList = null;
		NodeList trackList = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		Document document = null;
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths
				.get("C:\\temp\\Files\\"))) {
			for (Path file : stream) {
				System.out.println(file.getFileName());
				document = builder.parse(
						"C:\\temp\\Files\\"
								+ file.getFileName());
				document.getDocumentElement().normalize();
				nList = document.getElementsByTagName("Activity");
				lapList = document.getElementsByTagName("Lap");
				trackList = document.getElementsByTagName("Trackpoint");
				for (int temp = 0; temp < nList.getLength(); temp++) {
					Node node = nList.item(temp);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) node;
						// Create new Activity Object
						activity = new Activity();
						activity.setActivity(eElement.getAttribute("Sport"));
						activity.setId(eElement.getElementsByTagName("Id").item(temp).getTextContent());

						for (int i = 0; i < lapList.getLength(); i++) {
							Node lapNode = lapList.item(i);
							if (lapNode.getNodeType() == Node.ELEMENT_NODE) {
								Element lElement = (Element) node;
								// Create new Lap Object
								lap = new Lap();

								if (lElement.getElementsByTagName("Lap").item(i).getAttributes()
										.getNamedItem("StartTime") != null) {
									if (!lElement.getElementsByTagName("Lap").item(i).getAttributes()
											.getNamedItem("StartTime").getTextContent().isEmpty()) {
										lap.setStartTime(LocalDateTime
												.parse(lElement.getElementsByTagName("Lap").item(i).getAttributes()
														.getNamedItem("StartTime").getTextContent().substring(0, 19)));
									}
								}
								if (lElement.getElementsByTagName("MaximumSpeed").item(i) != null) {
									if (!lElement.getElementsByTagName("MaximumSpeed").item(i).getTextContent()
											.isEmpty()) {
										lap.setMaximumSpeed(Double.parseDouble(lElement
												.getElementsByTagName("MaximumSpeed").item(i).getTextContent()));
									}
								}
								if (lElement.getElementsByTagName("TotalTimeSeconds").item(i) != null) {
									if (!lElement.getElementsByTagName("TotalTimeSeconds").item(i).getTextContent()
											.isEmpty()) {
										lap.setTotalTimeSeconds(Double.parseDouble(lElement
												.getElementsByTagName("TotalTimeSeconds").item(i).getTextContent()));
									}
								}
								if (lElement.getElementsByTagName("DistanceMeters").item(i) != null) {
									if (!lElement.getElementsByTagName("DistanceMeters").item(i).getTextContent()
											.isEmpty()) {
										lap.setDistanceMetersTracks(Double.parseDouble(lElement
												.getElementsByTagName("DistanceMeters").item(i).getTextContent()));
									}
								}
								if (lElement.getElementsByTagName("Calories").item(i) != null) {
									if (!lElement.getElementsByTagName("Calories").item(i).getTextContent().isEmpty()) {
										lap.setCalories(Integer.parseInt(
												lElement.getElementsByTagName("Calories").item(i).getTextContent()));
									}
								}
								if (lElement.getElementsByTagName("AverageHeartRateBpm").item(i) != null) {
									if (!lElement.getElementsByTagName("AverageHeartRateBpm").item(i).getTextContent()
											.trim().isEmpty()) {
										lap.setAverageHeartRateBpm(
												Integer.parseInt(lElement.getElementsByTagName("AverageHeartRateBpm")
														.item(i).getTextContent().trim()));
									}
								}
								if (lElement.getElementsByTagName("MaximumHeartRateBpm").item(i) != null) {
									if (!lElement.getElementsByTagName("MaximumHeartRateBpm").item(i).getTextContent()
											.trim().isEmpty()) {
										lap.setMaximumHeartRateBpm(
												Integer.parseInt(lElement.getElementsByTagName("MaximumHeartRateBpm")
														.item(i).getTextContent().trim()));
									}
								}
								if (lElement.getElementsByTagName("Intensity").item(i) != null) {
									if (!lElement.getElementsByTagName("Intensity").item(i).getTextContent()
											.isEmpty()) {
										lap.setIntensity(
												lElement.getElementsByTagName("Intensity").item(i).getTextContent());
									}
								}
								if (lElement.getElementsByTagName("TriggerMethod").item(i) != null) {
									if (!lElement.getElementsByTagName("TriggerMethod").item(i).getTextContent()
											.isEmpty()) {
										lap.setTriggerMethod(lElement.getElementsByTagName("TriggerMethod").item(i)
												.getTextContent());
									}
								}
							}
							for (int j = 0; j < trackList.getLength(); j++) {
								Node trackNode = trackList.item(j);
								if (trackNode.getNodeType() == Node.ELEMENT_NODE) {
									Element tElement = (Element) node;
									// Create new Track Object
									track = new Track();
									if (tElement.getElementsByTagName("Time").item(i) != null) {
										if (!tElement.getElementsByTagName("Time").item(j).getTextContent().isEmpty()) {
											track.setTime(LocalDateTime.parse(tElement.getElementsByTagName("Time")
													.item(j).getTextContent().substring(0, 19)));
										}
									}
									if (tElement.getElementsByTagName("LatitudeDegrees").item(i) != null) {
										if (!tElement.getElementsByTagName("LatitudeDegrees").item(i).getTextContent()
												.isEmpty()) {
											track.setLatitudeDegrees(Double.parseDouble(tElement
													.getElementsByTagName("LatitudeDegrees").item(i).getTextContent()));
										}
									}
									if (tElement.getElementsByTagName("LongitudeDegrees").item(i) != null) {
										if (!tElement.getElementsByTagName("LongitudeDegrees").item(i).getTextContent()
												.isEmpty()) {
											track.setLongitudeDegrees(
													Double.parseDouble(tElement.getElementsByTagName("LongitudeDegrees")
															.item(i).getTextContent()));
										}
									}
									if (tElement.getElementsByTagName("AltitudeMeters").item(i) != null) {
										if (!tElement.getElementsByTagName("AltitudeMeters").item(i).getTextContent()
												.isEmpty()) {
											track.setAltitudeMeters(Double.parseDouble(tElement
													.getElementsByTagName("AltitudeMeters").item(i).getTextContent()));
										}
									}
									if (tElement.getElementsByTagName("DistanceMeters").item(i) != null) {
										if (!tElement.getElementsByTagName("DistanceMeters").item(i).getTextContent()
												.isEmpty()) {
											track.setDistanceMetersTracks(Double.parseDouble(tElement
													.getElementsByTagName("DistanceMeters").item(i).getTextContent()));
										}
									}
									if (tElement.getElementsByTagName("HeartRateBpm").item(i) != null) {
										if (!tElement.getElementsByTagName("HeartRateBpm").item(i).getTextContent()
												.isEmpty()) {
											track.setHeartRateBpm(
													Integer.parseInt(tElement.getElementsByTagName("HeartRateBpm")
															.item(i).getTextContent().trim()));
										}
									}
									if (tElement.getElementsByTagName("Speed").item(i) != null) {
										if (!tElement.getElementsByTagName("Speed").item(i).getTextContent()
												.isEmpty()) {
											track.setSpeed(Double.parseDouble(
													tElement.getElementsByTagName("Speed").item(i).getTextContent()));
										}
									}
									if (tElement.getElementsByTagName("RunCadence").item(i) != null) {
										if (!tElement.getElementsByTagName("RunCadence").item(i).getTextContent()
												.isEmpty()) {
											track.setRunCadence(Integer.parseInt(tElement
													.getElementsByTagName("RunCadence").item(i).getTextContent()));
										}
									}
								}
								tracks.add(track);

							}
							lap.setTrack(tracks);
							laps.add(lap);

						}
						activity.setLap(laps);
						activities.add(activity);
					}
				}

			}
		} catch (IOException | DirectoryIteratorException | SAXException ex) {
			System.err.println(ex);
		}
		return activities;
	}
}
