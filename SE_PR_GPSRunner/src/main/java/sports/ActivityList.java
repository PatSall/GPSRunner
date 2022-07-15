package sports;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import controller.Controller;

import java.util.*;
import javax.xml.parsers.*;
import java.io.*;
import java.nio.file.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Activity ist Teil der Struktur der TCX Files,
 * welche hierarchisch aufgebaut sind.
 * @author Patrick Sallaberger & Susanne Gumplmayr
 */
public class ActivityList {

	private static final int firstFiles = 10;
	private List<Activity> activities;

	private final List<TrackGPX> trackGPS;

	Controller parent;
	private ArrayList<FileTCX> tcxFiles;
	public String filepath = Paths.get(System.getProperty("user.home") + File.separator + "Testdaten").toString();

	/**
	 * @return
	 */
	public String getFilepath() {
		return filepath;
	}

	/**
	 * @param filepath
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
		tcxFiles = parseSaxTCXTimeStamp();
		activities = this.parseSaxFirstTCX();
		parseSaxTCX();
	}

	/**
	 * @param parent
	 */
	public void setController(Controller parent) {
		this.parent = parent;
	}

	/**
	 *
	 */
	public void readFiles() {
		parseSaxTCX();
	}

	/**
	 * @param activity
	 */
	public void addActivity(Activity activity) {
		activities.add(activity);
		activities.sort((o1, o2) -> o2.getLap().get(0).getStartTime().compareTo(o1.getLap().get(0).getStartTime()));
	}


	public ActivityList() {
		System.out.println("parse tcxFiles - für TimeStamps");
		Timestamp temp = new Timestamp(System.currentTimeMillis());
		tcxFiles = parseSaxTCXTimeStamp();
		System.out.println("DONE " + (new Timestamp(System.currentTimeMillis()).getTime() - temp.getTime()));


		System.out.println("parse die ersten tcxFiles");
		temp = new Timestamp(System.currentTimeMillis());
		activities = this.parseSaxFirstTCX();
		System.out.println("DONE " + (new Timestamp(System.currentTimeMillis()).getTime() - temp.getTime()));


		trackGPS = parseSaxGPX();
	}


	/**
	 * @return
	 */
	public List<Activity> parseDomTCX() {
		List<Activity> activities = new ArrayList<>();

		try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(filepath))) {
			List<String> filenames = new ArrayList<>();
			for (Path file : stream) {
				filenames.add(file.getFileName().toString());
			}

			filenames.parallelStream().forEach(file -> {

				 Document document = null;	
				 List<Lap> laps = new ArrayList<>();
					List<Track> tracks = new ArrayList<>();
					Activity activity;
					Lap lap;
					Track track;
					NodeList nList;
					NodeList lapList;
					NodeList trackList;
					int listLength;

					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = null;

					try {
						builder = factory.newDocumentBuilder();
					} catch (ParserConfigurationException e) {
						e.printStackTrace();
					}
				activity = null;
				track = null;

				try {
					assert builder != null;
					document = builder.parse(filepath + File.separator+ file);
				} catch (SAXException | IOException e) {
					e.printStackTrace();
				}
				assert document != null;
				document.getDocumentElement().normalize();

				nList = document.getElementsByTagName("Activity");
				
				listLength = nList.getLength();
				for (int temp = 0; temp < listLength; temp++) {
					Node node = nList.item(temp);

					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) node;

						activity = new Activity();
						activity.setActivity(eElement.getAttribute("Sport"));
						activity.setId(eElement.getElementsByTagName("Id").item(temp).getTextContent());
						//lapList = eElement.getElementsByTagName("Lap");
						lapList = ((Element) node).getElementsByTagName("Lap");

						for (int i = 0; i < lapList.getLength(); i++) {
							Node lapNode = lapList.item(i);

							if (lapNode.getNodeType() == Node.ELEMENT_NODE) {
								Element lElement = (Element) node;

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
										int k;
										if (lElement
											.getElementsByTagName("DistanceMeters").item(i).getParentNode().equals(lapNode)) {
										lap.setDistanceMetersTracks(Double.parseDouble(lElement
												.getElementsByTagName("DistanceMeters").item(i).getTextContent()));
										} else {
											    k = i;
												while (!lElement
														.getElementsByTagName("DistanceMeters").item(k).getParentNode().equals(lapNode)) {
													k++;
												}
												if (lElement
													.getElementsByTagName("DistanceMeters").item(k) != null)
												{
													lap.setDistanceMetersTracks(Double.parseDouble(lElement
															.getElementsByTagName("DistanceMeters").item(k).getTextContent()));
												}
											}
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

								trackList = ((Element) lapNode).getElementsByTagName("Trackpoint");

								for (int j = 0; j < trackList.getLength(); j++) {
									Node trackNode = trackList.item(j);
									if (trackNode.getNodeType() == Node.ELEMENT_NODE) {
										Element tElement = (Element) lapNode;
								
										track = new Track();		
										
										Element timeElement = (Element) tElement.getElementsByTagName("Time").item(j);									
										if (timeElement != null) {
											if (!timeElement.getTextContent().isEmpty()) {
												track.setTime(LocalDateTime.parse(timeElement.getTextContent().substring(0, 19)));
											}
										}
										
										
										Element latitudeDegreesElement = (Element) tElement.getElementsByTagName("LatitudeDegrees").item(j);
										Element longitudeDegreesElement = (Element) tElement.getElementsByTagName("LongitudeDegrees").item(j);										
										if (latitudeDegreesElement != null && longitudeDegreesElement != null) {
											if (!latitudeDegreesElement.getTextContent().isEmpty() && !longitudeDegreesElement.getTextContent().isEmpty()) {
												track.setPosition(new Position(
														Double.parseDouble(latitudeDegreesElement.getTextContent()),
														Double.parseDouble(longitudeDegreesElement.getTextContent())));
											}
										}
										
										Element altitudeMetersElement = (Element) tElement.getElementsByTagName("AltitudeMeters").item(j);										
										if (altitudeMetersElement != null) {
											if (!altitudeMetersElement.getTextContent()
													.isEmpty()) {
												track.setAltitudeMeters(Double.parseDouble(altitudeMetersElement.getTextContent()));
											}
										}
										
										Element distanceMetersElement = (Element) tElement.getElementsByTagName("DistanceMeters").item(j+1);
										if (distanceMetersElement != null) {
											if (distanceMetersElement.getTextContent()
													.isEmpty()) {
												track.setDistanceMetersTracks(Double.parseDouble(distanceMetersElement.getTextContent()));
											}
										}
										
										Element heartRateBpmElement = (Element) tElement.getElementsByTagName("HeartRateBpm").item(j);
										if (heartRateBpmElement != null) {
											if (!heartRateBpmElement.getTextContent().isEmpty()) {
												track.setHeartRateBpm(
														Integer.parseInt(heartRateBpmElement.getTextContent().trim()));
											}
										}

										Element speedElement = (Element) tElement.getElementsByTagName("Speed").item(j);
										Element runCadenceElement = (Element) tElement.getElementsByTagName("RunCadence").item(j);
										
										if (speedElement != null) {
											if (!speedElement.getTextContent().isEmpty()) {
												if (runCadenceElement != null) {
													track.setExtension(new Extension(
															Double.parseDouble(speedElement.getTextContent()),
															Integer.parseInt(runCadenceElement.getTextContent())));
												} else {
													track.setExtension(new Extension(
															Double.parseDouble(speedElement.getTextContent()), 0));
												}
											}
										}
									}
									tracks.add(track);
								}
								lap.setTrack(new ArrayList<>(tracks));
								tracks.clear();
								laps.add(lap);
							}
						}
						activity.setLap(new ArrayList<>(laps));
						laps.clear();
					}
					activities.add(activity);
				}
			 });
		} catch (IOException | DirectoryIteratorException ex) {
			//ex.printStackTrace();
			return activities;
		}
		return activities;
	}


	/**
	 *
	 */
	public void parseSaxTCX() {

		try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(filepath))) {
			Path file;
			List<Path> filesTCX = new ArrayList<>();

			for (int i = firstFiles; i < tcxFiles.size(); i++) {
				file = tcxFiles.get(i).getFile();
				System.out.println("first Files " + activities.size());
				if (file.getFileName().toString().endsWith(".tcx") && file != null) {
					filesTCX.add(file);
				}
			}
			if (filesTCX != null) {
				new ReadActivityThread(this, filesTCX).start();
			}
		} catch (IOException e) {
			//throw new RuntimeException(e);
		}
	}


	/**
	 * @return
	 */
	public ArrayList<FileTCX> parseSaxTCXTimeStamp() {
		ArrayList<FileTCX> tcxFiles = new ArrayList<>();
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser;
		FileTCX tcxFile;

		try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(filepath))) {
			saxParser = factory.newSAXParser();
			for (Path file : stream) {
				if (file.getFileName().toString().endsWith(".tcx")) {
					MapActivityObjectHandlerSaxTimestamp handlerSaxTCX = new MapActivityObjectHandlerSaxTimestamp();
					saxParser.parse(file.toString(), handlerSaxTCX);
					tcxFile = handlerSaxTCX.getFileResult();
					tcxFile.setFileName(file.getFileName().toString());
					tcxFile.setFile(file);
					tcxFiles.add(tcxFile);
				}
			}
		} catch (ParserConfigurationException |SAXException | IOException e) {
			//throw new RuntimeException(e);
			return tcxFiles;
		}
		tcxFiles.sort((o1, o2) -> o2.getStartTime().compareTo(o1.getStartTime()));
		return tcxFiles;
	}

	/**
	 * @return
	 */
	public List<Activity> parseSaxFirstTCX() {
		activities = new ArrayList<>();
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(filepath))) {
			Path file;
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = null;
			for (int i = 0; i < firstFiles; i++) {
				if (tcxFiles.size() - 1 >= i) {
					file = tcxFiles.get(i).getFile();
					if (file.getFileName().toString().endsWith(".tcx")) {
						try {
							saxParser = factory.newSAXParser();
						} catch (ParserConfigurationException | SAXException e) {
							e.printStackTrace();
						}
						MapActivityObjectHandlerSax handlerSax = new MapActivityObjectHandlerSax();
						try {
							saxParser.parse(file.toString(), handlerSax);
						} catch (SAXException | IOException | NullPointerException e) {
							e.printStackTrace();
						}
						activities.add(handlerSax.getActivityResult());
					}
				}
			}
		} catch (IOException e) {
			//throw new RuntimeException(e);
			return activities;
		}
		return activities;
	}

	/**
	 * @return
	 */
	public List<TrackGPX> parseSaxGPX () {

		List<TrackGPX> trackGPSses = new ArrayList<>();
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser;
		TrackGPX track;

		try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(filepath))) {
			saxParser = factory.newSAXParser();
			for (Path file : stream) {
				if (file.getFileName().toString().endsWith(".gpx")) {
					MapTrackGPXObjectHandlerSax handlerSax = new MapTrackGPXObjectHandlerSax();
					saxParser.parse(file.toString(), handlerSax);
					track = handlerSax.getTrackGPSResult();
					trackGPSses.add(track);
				}
			}
		} catch (SAXException | IOException | ParserConfigurationException e) {
			//throw new RuntimeException(e);
			return trackGPSses;
		}

		return trackGPSses;
	}

	/**
	 * @return liefert eine Liste von Aktivitäten
	 */
	public List<Activity> getActivities () {
		return this.activities;
	}

	/**
	 * @return liefert eine Liste von Tracks des GPX Files
	 */
	public List<TrackGPX> getTrackGPX() {
		return this.trackGPS;
	}


	/**
	 * @param activities setzt eine Liste von activities
	 */
	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}
}
