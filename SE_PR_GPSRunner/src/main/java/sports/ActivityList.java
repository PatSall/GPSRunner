package sports;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import controller.Controller;

import javax.xml.parsers.*;
import java.io.*;
import java.nio.file.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ActivityList {

	private List<Activity> activities;
	private List<TrackGPS> trackGPS;
	//public static String homePath = System.getProperty("user.home");
	private Controller parent;
	private ArrayList<FileTCX> tcxFiles;

	public String filepath = Paths.get(System.getProperty("user.home") + File.separator + "Testdaten").toString();

	public String getFilepath() {
		return filepath;
	}

	//TODO
	public void setFilepath(String filepath) {
		this.filepath = filepath;
		this.setActivities(parseSaxTCX());
		parent.setActivities();
//		this.parseActivitiesXML();
//		parent.setActivities();
	}

	public void setController(Controller parent) {
		this.parent = parent;
	}

	public ActivityList() {
		Timestamp temp = new Timestamp(System.currentTimeMillis());
		/*final List<Activity>*/

		//activities = parseTCX();
		tcxFiles = parseSaxTCXTimeStamp();
		activities = parseSaxTCX();
		//tcxFiles = parseDOMTCXTimeStamp();

		System.out.println(new Timestamp(System.currentTimeMillis()).getTime() - temp.getTime());
		//System.out.println(homePath);

		/*for (int j= 0; j < activities.size(); j++) {
			System.out.println(activities.get(j).getId());
			for (int i = 0; i < activities.get(j).getLap().size(); i++) {
				//System.out.println(activities.get(j).getLap().get(i).getDistanceMetersTracks());
				//System.out.println(activities.get(j).getLap().get(i).getStartTime());
				//System.out.println(activities.get(j).getLap().get(i).getCalories());
				for (int k = 0; k < activities.get(j).getLap().get(i).getTrack().size(); k++) {
					System.out.println(activities.get(j).getLap().get(i).getTrack().get(k).getTime());
				}
			}
		}*/

		trackGPS = parseGPX();
		//for (TrackGPS t : trackGPS) {
		//System.out.println(trackGPS.toString());
		//System.out.println();
		//System.out.println(t.toString());
		//System.out.println(t.getName());
		//}
	}

	private List<TrackGPS> parseGPX() {
		List<TrackGPS> trackGPSs = new ArrayList<>();
		//try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get("C:\\temp\\Files\\"))) {
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(filepath))) {
			//for (Path file : stream) {
			List<String> filenames = new ArrayList<String>();
			for (Path file : stream) {
				filenames.add(file.getFileName().toString());
			}

			//for (Path file : stream) {
			filenames.parallelStream().forEach(file -> {
				Document document = null;
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

				try {
					document = builder.parse(filepath + File.separator + file);
				} catch (SAXException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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

						nSegmentList = ((Element) node).getElementsByTagName("trkpt");
						for (int i = 0; i < nSegmentList.getLength(); i++) {
							Node trackSegmentNode = nSegmentList.item(i);
							if (trackSegmentNode.getNodeType() == Node.ELEMENT_NODE) {
								Element lElement = (Element) node;
								trackSegment = new TrackSegment();
								if (lElement.getElementsByTagName("ele").item(i) != null) {
									if (!lElement.getElementsByTagName("ele").item(i).getTextContent()
											.isEmpty()) {
										if (lElement.getElementsByTagName("ele").item(i) != null && lElement.getElementsByTagName("time").item(i) != null) {
											trackSegment.addTrackPoint(new TrackPoint(
													Double.parseDouble(lElement.getElementsByTagName("ele")
															.item(i).getTextContent()),
													LocalDateTime.parse(lElement.getElementsByTagName("time")
															.item(i).getTextContent().substring(0, 19))));
										} else {
											trackSegment.addTrackPoint(new TrackPoint(
													Double.parseDouble(lElement.getElementsByTagName("ele")
															.item(i).getTextContent()), null));
										}
									}
								}
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
							}
							trackSegments.add(trackSegment);
						}
						trackGPS.setTrackSegments(new ArrayList<>(trackSegments));
						trackSegments.clear();
						trackGPSs.add(trackGPS);
					}
				}
			});
		} catch (IOException | DirectoryIteratorException ex) {
			System.err.println(ex);
		}
		return trackGPSs;
	}

	private List<Activity> parseTCX() {
		List<Activity> activities = new ArrayList<>();

		//try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get("C:\\temp\\Files\\"))) {
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(filepath))) {
			//for (Path file : stream) {
			List<String> filenames = new ArrayList<String>();
			for (Path file : stream) {
				filenames.add(file.getFileName().toString());
			}
			//for (Path file : stream) {
			filenames.parallelStream().forEach(file -> {

				Document document = null;
				List<Lap> laps = new ArrayList<>();
				List<Track> tracks = new ArrayList<>();
				Activity activity = null;
				Lap lap = null;
				Track track = null;
				NodeList nList = null;
				NodeList lapList = null;
				NodeList trackList = null;
				int listLength = 0;

				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = null;

				try {
					builder = factory.newDocumentBuilder();
				} catch (ParserConfigurationException e) {
					e.printStackTrace();
				}


				activity = null;
				lap = null;
				track = null;
				nList = null;
				lapList = null;
				trackList = null;
				/*if (!file.getFileName().toString().endsWith(".tcx")) {
					break;
				}*/
				try {
					document = builder.parse(filepath + File.separator + file);
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
						//	for (int i = 0; i < 1; i++) {
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
										int k = 0;
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
													.getElementsByTagName("DistanceMeters").item(k) != null) {
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
									//Node trackNode = lapNode.appendChild(trackList.item(j));
									Node trackNode = trackList.item(j);
									if (trackNode.getNodeType() == Node.ELEMENT_NODE) {
										//Element tElement = (Element) node;
										Element tElement = (Element) lapNode;

										// Create new Track Object
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

										Element distanceMetersElement = (Element) tElement.getElementsByTagName("DistanceMeters").item(j + 1);
										if (distanceMetersElement != null) {
											if (distanceMetersElement.getTextContent()
													.isEmpty()) {
												if (distanceMetersElement != null) {
													track.setDistanceMetersTracks(Double.parseDouble(distanceMetersElement.getTextContent()));
												}
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
				//}
			});
		} catch (IOException | DirectoryIteratorException ex) {
			System.err.println(ex);
		}
		return activities;
	}


	public List<Activity> parseSaxTCX() {

		List<Activity> activities = new ArrayList<>();
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = null;
		Activity activity = null;

		try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(filepath))) {
			saxParser = factory.newSAXParser();
			Path file = null;
			for (int i= 0; i< tcxFiles.size(); i++ ) {
				file = tcxFiles.get(i).getFile();
				//for (Path file : stream) {

				if (file.getFileName().toString().endsWith(".tcx")) {
					MapActivityObjectHandlerSax handlerSax = new MapActivityObjectHandlerSax();
					saxParser.parse(file.toString(), handlerSax);
					activity = handlerSax.getActivityResult();
					activities.add(activity);
				}

				//}
			}
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		} catch (SAXException | IOException e) {
			throw new RuntimeException(e);
		}
		//Parser in einen Task starten , aktivitylist in einen task einlest , liste aktuallisiert
		//gui Message achtung neue liste arbiete mit dieser - methode in main , parentObjekt , Parser , pro File
		// oder gui starten und dann einlesen
		// zusäzliches Parse und liste zusammenbaut
		return activities;
	}


	public ArrayList<FileTCX> parseSaxTCXTimeStamp() {
		ArrayList<FileTCX> tcxFiles = new ArrayList<>();
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = null;
		FileTCX tcxFile = null;

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
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		} catch (SAXException | IOException e) {
			throw new RuntimeException(e);
		}
		Collections.sort(tcxFiles,(o1, o2) -> o2.getStartTime().compareTo(o1.getStartTime()));
		return tcxFiles;
	}

	public ArrayList<FileTCX> parseDOMTCXTimeStamp() {
		Document document = null;
		ArrayList<FileTCX> tcxFiles = new ArrayList<>();
		FileTCX tcxFile = null;

		DocumentBuilder builder = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();


		try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(filepath))) {
			NodeList nFileList = null;
			for (Path file : stream) {
				document.getDocumentElement().normalize();
				factory = DocumentBuilderFactory.newInstance();
				nFileList = document.getElementsByTagName("Lap");
				builder = null;
				try {
					builder = factory.newDocumentBuilder();
				} catch (ParserConfigurationException e) {
					e.printStackTrace();
				}

				tcxFile = null;
				try {
					document = builder.parse(filepath + File.separator + file);
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				document.getDocumentElement().normalize();
				NodeList fileList = null;



				Node fileNode = fileList.item(0);
				if (file.getFileName().toString().endsWith(".tcx")) {
						tcxFile = new FileTCX();
						//Element tElement = (Element) node;
						Element tElement = (Element) fileNode;
						if (fileNode.getNodeType() == Node.ELEMENT_NODE) {
							Element lElement = (Element) fileList;
							if (lElement.getElementsByTagName("Lap").item(0).getAttributes()
									.getNamedItem("StartTime") != null) {
								if (lElement.getElementsByTagName("Lap").item(0).getAttributes()
										.getNamedItem("StartTime").getTextContent().isEmpty()) {
									tcxFile.setStartTime(LocalDateTime
											.parse(lElement.getElementsByTagName("Lap").item(0).getAttributes()
													.getNamedItem("StartTime").getTextContent().substring(0, 19)));
								}
							}
						}
				}
				tcxFile.setFileName(file.getFileName().toString());
				tcxFiles.add(tcxFile);
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		Collections.sort(tcxFiles,(o1, o2) -> o2.getStartTime().compareTo(o1.getStartTime()));
		return tcxFiles;
	}


	public List<Activity> getActivities () {
		return this.activities;
	}

	public List<TrackGPS> getTrackGPS () {
		return this.trackGPS;
	}

	
	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}
}
