package gui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;



import controller.Controller;
import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;
import sports.Activity;
import sports.Lap;
import sports.Track;
import sports.TrackGPX;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

import java.util.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.jxmapviewer.painter.*;

import java.time.Month;

import sports.TrackPoint;
import sports.TrackSegment;


/**
 * @author Patrick Sallaberger
 *
 */
public class GUI extends JFrame {

	List<Activity> activityList;
	List<TrackGPX> trackGPS;
	Object[][] actualData;
	Object[][] actualDetail;

	JCheckBoxMenuItem undefined;
	Filter[] sportsFilter = new Filter[6];
	Filter[] distanceFilter = new Filter[10];
	Filter[] graphFilter = new Filter[3];
	Filter[] viewFilter = new Filter[3];

	JTabbedPane tabbedPanel;
	JScrollPane upperPanel;
	JTable table;
	DefaultTableModel model;
	JPanel lowerPanel;
	JScrollPane lowerDetailPanel;

	JPanel lowerMapPanel;

	Map mapModel;
	JTable detailTable;
	DefaultTableModel detailModel;
	ChartPanel chartPanel;
	Collection<Activity> activities;

	JCheckBoxMenuItem distance;
	JCheckBoxMenuItem bpm;
	JCheckBoxMenuItem speed;
	DefaultCategoryDataset cd;

	JCheckBoxMenuItem day;
	JCheckBoxMenuItem month;
	JCheckBoxMenuItem year;

	Controller parent = null;

	String[] columnNames = new String[]{"name","activity","date","distance (m)","time","pace","bpm"};
	String[] detailColumnNames = new String[] {"distancemeter tracks","altitude meters","time","bpm","meters per second","Cadence","longitude", "latitude"};

	String[][] aggrListNames;
	double[][] aggrList;

	/**
	 * @param activities as a collection of activity-data
	 * generates a graph
	 */
	public void updateChart(Collection<Activity> activities) {
		this.activities = activities;
		cd.clear();

		String xAxe =  "";
		String yAxe = "";
		String graphName = "";
		for(int i = 0; i < graphFilter.length; i++) {
			if(graphFilter[i].getState()) {
				graphName = graphFilter[i].getName();
				if(graphFilter[i].getName().equals("distance")) {
					yAxe = "Meter";
				} else if(graphFilter[i].getName().equals("bpm")) {
					yAxe = "Beats per Minute";
				} else if(graphFilter[i].getName().equals("speed")) {
					yAxe = "Meters per Minute";
				}
			}
		}
		String temp = "";
		for(int i = 0; i < viewFilter.length; i++) {
			if(viewFilter[i].getState()) {
				temp = viewFilter[i].getName();
			}
		}
		if(temp.equals("")) {
			for (int i = 0; i < activityList.size(); i++) {
				if (activityList.get(i).showInGui(sportsFilter, distanceFilter)) {
					cd.addValue(activityList.get(i).showInGraph(graphFilter), activityList.get(i).getId(), xAxe);
				}
			}
		} else {
			aggrList(temp);
			for(int i = 0; i < aggrList.length; i++) {
				for(int j = 0; j < aggrList[i].length; j++) {
					if(aggrListNames[i][j] != null) {
						cd.addValue(aggrList[i][j], aggrListNames[i][j], xAxe);
					}
				}
			}
		}


		JFreeChart chart = ChartFactory.createBarChart(graphName, null, yAxe, cd, PlotOrientation.VERTICAL, true, true, false);
		chartPanel.setChart(chart);
	}

	public void setController(Controller parent) {
		this.parent = parent;
	}

	/**
	 * builds the grapical user interface
	 */
	@SuppressWarnings("deprecation")
	public GUI() {

		setTitle("GPSRunner");
		setSize(500,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setLayout(new BorderLayout());

		model = new DefaultTableModel(new Object[0][7], columnNames);
		detailModel = new DefaultTableModel(new Object[0][8], detailColumnNames);

		table = new JTable(model);
		table.setFillsViewportHeight(true);
		detailTable = new JTable(detailModel);
		detailTable.setFillsViewportHeight(true);
		upperPanel = new JScrollPane(table);

		lowerDetailPanel = new JScrollPane(detailTable);

		lowerMapPanel = new JPanel();
		mapModel = new Map() ;
		tabbedPanel = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.WRAP_TAB_LAYOUT);

		table.addMouseListener(click());

		cd = new DefaultCategoryDataset();
		chartPanel= new ChartPanel(null);
		chartPanel.setSize(new Dimension(tabbedPanel.getWidth(), tabbedPanel.getHeight()));
		lowerPanel = chartPanel;
		tabbedPanel.addTab("Charts", lowerPanel);
		tabbedPanel.addTab("Details", lowerDetailPanel);
		tabbedPanel.addTab("Map", lowerMapPanel);

		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT,upperPanel, tabbedPanel);
		this.add(sp, BorderLayout.CENTER);

		JMenuBar menu = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu tracks = new JMenu("Tracks");
		JMenu segments = new JMenu("Segments");
		JMenu graph = new JMenu("Graph");
		JMenu view = new JMenu("View");
		
		JMenu open = new JMenu("Directory...");

		menu.add(file);
		menu.add(tracks);
		menu.add(segments);
		menu.add(graph);
		menu.add(view);

		setJMenuBar(menu);

		JMenuItem tcx = new JMenuItem("change directory");
		tcx.addActionListener(new ActionListener() {
			/**
			 * @param e the event to be processed
			 * sets the selected path in the model
			 */
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser(parent.getActivities().getFilepath());
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.showOpenDialog(null);
				if (chooser.getSelectedFile() != null) {
					parent.setPath(chooser.getSelectedFile().getPath());
				}
			}
		});

		undefined = new JCheckBoxMenuItem("(undefined)", false);
		JCheckBoxMenuItem all = new JCheckBoxMenuItem("All", true);
		JCheckBoxMenuItem running = new JCheckBoxMenuItem("Running");
		JCheckBoxMenuItem skiing = new JCheckBoxMenuItem("Skiing");
		JCheckBoxMenuItem diving = new JCheckBoxMenuItem("Diving");
		JCheckBoxMenuItem flying = new JCheckBoxMenuItem("Flying");
		JCheckBoxMenuItem hiking = new JCheckBoxMenuItem("Hiking");

		sportsFilter[0] = new Filter(all.getLabel(), all.getState(), all);
		sportsFilter[1] = new Filter(running.getLabel(), running.getState(), running);
		sportsFilter[2] = new Filter(skiing.getLabel(), skiing.getState(), skiing);
		sportsFilter[3] = new Filter(diving.getLabel(), diving.getState(), diving);
		sportsFilter[4] = new Filter(flying.getLabel(), flying.getState(), flying);
		sportsFilter[5] = new Filter(hiking.getLabel(), hiking.getState(), hiking);

		undefined.addActionListener(action());
		all.addActionListener(action());
		running.addActionListener(action());
		skiing.addActionListener(action());
		diving.addActionListener(action());
		flying.addActionListener(action());
		hiking.addActionListener(action());

		day = new JCheckBoxMenuItem("day", false);
		month = new JCheckBoxMenuItem("month", false);
		year = new JCheckBoxMenuItem("year", false);

		day.addActionListener(action());
		month.addActionListener(action());
		year.addActionListener(action());

		viewFilter[0] = new Filter(day.getLabel(),day.getState(),day);
		viewFilter[1] = new Filter(month.getLabel(),month.getState(),month);
		viewFilter[2] = new Filter(year.getLabel(),year.getState(),year);

		JCheckBoxMenuItem tenM = new JCheckBoxMenuItem("< 10m", true);
		JCheckBoxMenuItem twentyM = new JCheckBoxMenuItem("< 20m", true);
		JCheckBoxMenuItem fiftyM = new JCheckBoxMenuItem("< 50m", true);
		JCheckBoxMenuItem hundredM = new JCheckBoxMenuItem("< 100m", true);
		JCheckBoxMenuItem quarterKm = new JCheckBoxMenuItem("< 250m",true);
		JCheckBoxMenuItem halfKm = new JCheckBoxMenuItem("< 500m", true);
		JCheckBoxMenuItem oneKm = new JCheckBoxMenuItem("< 1000m", true);
		JCheckBoxMenuItem twoHalfKm = new JCheckBoxMenuItem("< 2500m", true);
		JCheckBoxMenuItem fiveKm = new JCheckBoxMenuItem("< 5000m", true);
		JCheckBoxMenuItem moreThan = new JCheckBoxMenuItem("> 5000m", true);

		distanceFilter[0] = new Filter(tenM.getLabel(), tenM.getState(), tenM, 10);
		distanceFilter[1] = new Filter(twentyM.getLabel(), twentyM.getState(), twentyM, 20);
		distanceFilter[2] = new Filter(fiftyM.getLabel(), fiftyM.getState(), fiftyM, 50);
		distanceFilter[3] = new Filter(hundredM.getLabel(), hundredM.getState(), hundredM, 100);
		distanceFilter[4] = new Filter(quarterKm.getLabel(), quarterKm.getState(), quarterKm, 250);
		distanceFilter[5] = new Filter(halfKm.getLabel(), halfKm.getState(), halfKm, 500);
		distanceFilter[6] = new Filter(oneKm.getLabel(), oneKm.getState(), oneKm, 1000);
		distanceFilter[7] = new Filter(twoHalfKm.getLabel(), twoHalfKm.getState(), twoHalfKm, 2500);
		distanceFilter[8] = new Filter(fiveKm.getLabel(), fiveKm.getState(), fiveKm, 5000);
		distanceFilter[9] = new Filter(moreThan.getLabel(), moreThan.getState(), moreThan, Integer.MAX_VALUE);

		tenM.addActionListener(action());
		twentyM.addActionListener(action());
		fiftyM.addActionListener(action());
		hundredM.addActionListener(action());
		quarterKm.addActionListener(action());
		halfKm.addActionListener(action());
		oneKm.addActionListener(action());
		twoHalfKm.addActionListener(action());
		fiveKm.addActionListener(action());
		moreThan.addActionListener(action());

		distance = new JCheckBoxMenuItem("distance", true);
		bpm = new JCheckBoxMenuItem("bpm");
		speed = new JCheckBoxMenuItem("speed");

		distance.addActionListener(action());
		bpm.addActionListener(action());
		speed.addActionListener(action());

		graphFilter[0] = new Filter(distance.getLabel(), distance.getState(), distance);
		graphFilter[1] = new Filter(bpm.getLabel(), bpm.getState(), bpm);
		graphFilter[2] = new Filter(speed.getLabel(), speed.getState(), speed);
		
		file.add(open);
		tracks.add(undefined);
		tracks.add(all);
		tracks.add(running);
		tracks.add(skiing);
		tracks.add(diving);
		tracks.add(flying);
		tracks.add(hiking);
		segments.add(tenM);
		segments.add(twentyM);
		segments.add(fiftyM);
		segments.add(hundredM);
		segments.add(quarterKm);
		segments.add(halfKm);
		segments.add(oneKm);
		segments.add(twoHalfKm);
		segments.add(fiveKm);
		segments.add(moreThan);
		graph.add(distance);
		graph.add(bpm);
		graph.add(speed);
		view.add(day);
		view.add(month);
		view.add(year);

		open.add(tcx);
	}

	/**
	 * calls refresh-functions with the chosen activity
	 */
	public MouseListener click() {
		return new MouseListener() {
			@Override
			public void mousePressed(MouseEvent me) {
				JTable table =(JTable) me.getSource();
				Point p = me.getPoint();
				int row = table.rowAtPoint(p);
				if(row != -1) {
					getEvent((String) actualData[row][0]);
					refreshDetails();
					refreshMap(getEventMap((String) actualData[row][0]));
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}
		};
	}

	/**
	 * refreshes table and chart
	 */
	public void refreshGui() {
		model.setDataVector(table(), columnNames);
		model.fireTableChanged(null);
		this.updateChart(activityList);
	}

	/**
	 * refreshes the detail-table
	 */
	public void refreshDetails() {
		detailModel.setDataVector(actualDetail, detailColumnNames);
	}
	public void refreshMap(ArrayList<Waypoint> waypoints) {
		mapModel.map.getMainMap().removeAll();
		lowerMapPanel.removeAll();

		if (waypoints.size() != 0) {
			mapModel.map.getMainMap().add(new Map(waypoints));
		}
		mapModel.map.repaint();
	}

	/**
	 * @return an array from datatype Object
	 * generates an array for the table
	 */
	public Object[][] table() {
		int counter = 0;
		int countEntries = 0;


		for (int i = 0; i < activityList.size(); i++ ) {
			if (activityList.get(i).showInGui(sportsFilter, distanceFilter)) {
				countEntries++;
			}
		}
		int activityListBound = countEntries;
		if(undefined.getState()) {
			for(int i = 0; i < trackGPS.size(); i++) {
				countEntries++;
			}
		}
		Object[][] data = new Object[countEntries][7];
		if(undefined.getState()) {
			for(int i = 0; i < trackGPS.size(); i++) {
				data[counter][0] = trackGPS.get(i).getName();
				data[counter][1] = "(undefined)";
				data[counter][2] = "";
				data[counter][3] = "";
				data[counter][4] = "";
				data[counter][5] = "";
				data[counter][6] ="";
				counter++;
			}
		}

		for (int i = 0; i < activityListBound; i++ ) {
			if (activityList.get(i).showInGui(sportsFilter, distanceFilter)) {

				data[counter][0] = activityList.get(i).getId();
				data[counter][1] = activityList.get(i).getActivity();
				data[counter][2] = activityList.get(i).averageLap(activityList.get(i).getLap()).getStartTime();
				data[counter][3] = Math.round(activityList.get(i).averageLap(activityList.get(i).getLap()).getDistanceMetersTracks() * 100) / 100.0;
				String minutes = "";
				if (((activityList.get(i).averageLap(activityList.get(i).getLap()).getTotalTimeSeconds() / 3600) % 1 * 60) < 10) {
					minutes = "0" + (int) ((activityList.get(i).averageLap(activityList.get(i).getLap()).getTotalTimeSeconds() / 3600) % 1 * 60);
				} else {
					minutes += (int) ((activityList.get(i).averageLap(activityList.get(i).getLap()).getTotalTimeSeconds() / 3600) % 1 * 60);
				}
				data[counter][4] = (int) (activityList.get(i).averageLap(activityList.get(i).getLap()).getTotalTimeSeconds() / 3600) + ":" + minutes;
				data[counter][5] = Math.round((activityList.get(i).averageLap(activityList.get(i).getLap()).getMaximumSpeed() * 60) * 100) / 100.0;
				data[counter][6] = activityList.get(i).averageLap(activityList.get(i).getLap()).getMaximumHeartRateBpm();
				counter++;
			}

		}


		actualData = data;
		return data;
	}

	/**
	 * @param activityList contains all actual activities
	 * sets the local variable activityList
	 */
	public void setActivityList(List<Activity> activityList) {

		this.activityList = activityList;
	}

	/**
	 * @param trackGPS contains all actual TrackGPS Files
	 * sets the local variable trackGPS
	 */
	public void setTrackGPS(List<TrackGPX> trackGPS) {
		this.trackGPS = trackGPS;
	}

	/**
	 * sets actual filters and refreshes the GUI
	 */
	public ActionListener action() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(sportsFilter[0].button == e.getSource()) {
					sportsFilter[0].setState(!sportsFilter[0].state);
				} else if(sportsFilter[1].button == e.getSource()) {
					sportsFilter[1].setState(!sportsFilter[1].state);
				} else if(sportsFilter[2].button == e.getSource()) {
					sportsFilter[2].setState(!sportsFilter[2].state);
				} else if(sportsFilter[3].button == e.getSource()) {
					sportsFilter[3].setState(!sportsFilter[3].state);
				} else if(sportsFilter[4].button == e.getSource()) {
					sportsFilter[4].setState(!sportsFilter[4].state);
				} else if(sportsFilter[5].button == e.getSource()) {
					sportsFilter[5].setState(!sportsFilter[5].state);
				} else if(distanceFilter[0].button == e.getSource()) {
					distanceFilter[0].setState(!distanceFilter[0].state);
				} else if(distanceFilter[1].button == e.getSource()) {
					distanceFilter[1].setState(!distanceFilter[1].state);
				} else if(distanceFilter[2].button == e.getSource()) {
					distanceFilter[2].setState(!distanceFilter[2].state);
				} else if(distanceFilter[3].button == e.getSource()) {
					distanceFilter[3].setState(!distanceFilter[3].state);
				} else if(distanceFilter[4].button == e.getSource()) {
					distanceFilter[4].setState(!distanceFilter[4].state);
				} else if(distanceFilter[5].button == e.getSource()) {
					distanceFilter[5].setState(!distanceFilter[5].state);
				} else if(distanceFilter[6].button == e.getSource()) {
					distanceFilter[6].setState(!distanceFilter[6].state);
				} else if(distanceFilter[7].button == e.getSource()) {
					distanceFilter[7].setState(!distanceFilter[7].state);
				} else if(distanceFilter[8].button == e.getSource()) {
					distanceFilter[8].setState(!distanceFilter[8].state);
				} else if(distanceFilter[9].button == e.getSource()) {
					distanceFilter[9].setState(!distanceFilter[9].state);
				} else if(graphFilter[0].button == e.getSource()) {
					graphFilter[0].setState(true);
					graphFilter[1].setState(false);
					graphFilter[2].setState(false);
					distance.setState(true);
					bpm.setState(false);
					speed.setState(false);
				} else if(graphFilter[1].button == e.getSource()) {
					graphFilter[0].setState(false);
					graphFilter[1].setState(true);
					graphFilter[2].setState(false);
					bpm.setState(true);
					distance.setState(false);
					speed.setState(false);
				} else if(graphFilter[2].button == e.getSource()) {
					graphFilter[0].setState(false);
					graphFilter[1].setState(false);
					graphFilter[2].setState(true);
					distance.setState(false);
					bpm.setState(false);
					speed.setState(true);
				} else if(viewFilter[0].button == e.getSource()) {
					viewFilter[0].setState(!viewFilter[0].getState());
					day.setState(viewFilter[0].getState());
					viewFilter[1].setState(false);
					month.setState(false);
					viewFilter[2].setState(false);
					year.setState(false);
				} else if(viewFilter[1].button == e.getSource()) {
					viewFilter[0].setState(false);
					day.setState(false);
					viewFilter[1].setState(!viewFilter[1].getState());
					month.setState(viewFilter[1].getState());
					viewFilter[2].setState(false);
					year.setState(false);
				} else if(viewFilter[2].button == e.getSource()) {
					viewFilter[0].setState(false);
					day.setState(false);
					viewFilter[1].setState(false);
					month.setState(false);
					viewFilter[2].setState(!viewFilter[2].getState());
					year.setState(viewFilter[2].getState());
				}
				refreshGui();
			}
		};
	}

	/**
	 * @param name represents the ID of the activity
	 * shows the details of the selected event
	 */
	public void getEvent(String name) {
		for(Activity a : activityList) {
			if(name!=null && name.equals(a.getId())) {
				List<Lap> list = a.getLap();
				int counter = 0;
				for(Lap l : list) {
					counter += l.getTrack().size();
				}
				actualDetail = new Object[counter][8];
				int position = 0;
				for(Lap l : list) {
					List<Track> temp = l.getTrack();
					for(Track track : temp) {

						actualDetail[position][0] = null;
						actualDetail[position][1] = null;
						actualDetail[position][2] = null;
						actualDetail[position][3] = null;
						actualDetail[position][4] = null;
						actualDetail[position][5] = null;
						actualDetail[position][6] = null;
						actualDetail[position][7] = null;
						if(track.getDistanceMetersTracks() != null)
							actualDetail[position][0] = Math.round(track.getDistanceMetersTracks()*100)/100.0;
						if(track.getAltitudeMeters() != null)
							actualDetail[position][1] = track.getAltitudeMeters();
						if(track.getTime() != null)
							actualDetail[position][2] = track.getTime();
						if(track.getHeartRateBpm() != null)
							actualDetail[position][3] = track.getHeartRateBpm();


						if(track.getExtension() != null) {
							if(track.getExtension().getSpeed() != null) {
								actualDetail[position][4] = Math.round(track.getExtension().getSpeed() * 100) / 100.0;
							}
							if(track.getExtension().getRunCadence() != null) {
								actualDetail[position][5] = track.getExtension().getRunCadence();
							}
						}
						if(track.getPosition() != null) {
							actualDetail[position][6] = track.getPosition().getLongitudeDegrees();
							actualDetail[position][7] = track.getPosition().getLatitudeDegrees();
						}
						position++;
					}
				}
			}
		}

		for(TrackGPX a : trackGPS) {
			if(name != null && name.equals(a.getName())) {
				List<TrackSegment> list = a.getTrackSegments();
				int counter = 0;
				for(TrackSegment t : list) {
					for(TrackPoint tp : t.getTrackPoint()) {
						counter++;
					}
				}
				actualDetail = new Object[counter][8];
				int position = 0;
				for(TrackSegment t : list) {
					List<TrackPoint> temp = t.getTrackPoint();
					for(TrackPoint track : temp) {

						actualDetail[position][0] = null;
						actualDetail[position][1] = null;
						actualDetail[position][2] = null;
						actualDetail[position][3] = null;
						actualDetail[position][4] = null;
						actualDetail[position][5] = null;
						actualDetail[position][6] = null;
						actualDetail[position][7] = null;

						if(track.getTrackPointLon() != null) {
							actualDetail[position][6] = track.getTrackPointLon();
						}
						if(track.getTrackPointLat() != null) {
							actualDetail[position][7] = track.getTrackPointLat();
						}
						position++;
					}
				}
			}
		}
	}

	/**
	 * @param name represents the ID of the activity
	 * @return List of all waypoints from the selected activity
	 */
	public ArrayList<Waypoint> getEventMap(String name) {
		ArrayList<Waypoint> waypoints = new ArrayList<>();
		for(Activity a : activityList) {
			if(name != null && name.equals(a.getId())) {
				List<Lap> list = a.getLap();
				Waypoint waypoint;
				for(Lap l : list) {
					List<Track> temp = l.getTrack();
					for(Track track : temp) {
						if (track.getPosition().getLatitudeDegrees() != null) {
							waypoint = (new DefaultWaypoint(new GeoPosition(track.getPosition().getLatitudeDegrees(), track.getPosition().getLongitudeDegrees())));
							waypoints.add(waypoint);
						}
					}
				}
			}
		}
		for(TrackGPX a : trackGPS) {
			if(name != null && name.equals(a.getName())) {
				List<TrackSegment> list = a.getTrackSegments();
				Waypoint waypoint;
				for(TrackSegment l : list) {
					List<TrackPoint> temp = l.getTrackPoint();
					for(TrackPoint track : temp) {
						if (track.getTrackPointLat() != null) {
							waypoint = (new DefaultWaypoint(new GeoPosition(track.getTrackPointLat(), track.getTrackPointLon())));
							waypoints.add(waypoint);
						}
					}
				}
			}
		}
		return waypoints;
	}

	/**
	 * @param activityList contains all actual activities
	 * sets the local variable activityList and calls refreshGui
	 */
	public void setActivities(List<Activity> activityList) {
		this.activityList = activityList;
		refreshGui();
	}

	/**
	 * @return the actual list of activities
	 */
	public List<Activity> getActivities() {
		return activityList;
	}

	/**
	 * @return the name of the set view-filter
	 */
	public String activeView() {
		for(int i = 0; i < viewFilter.length; i++) {
			if(viewFilter[i].getState()) {
				return viewFilter[i].getName();
			}
		}
		return "";
	}

	/**
	 * @param view represents the name of the set view-filter
	 * aggregates the data according to the set view-filter
	 */
	public void aggrList(String view) {
		String filter = "";
		for(int i = 0; i < graphFilter.length; i++) {
			if(graphFilter[i].getState()) {
				filter = graphFilter[i].getName();
			}
		}
		if(view.equals("day")) {
			aggrList = new double[10][365];
			aggrListNames = new String[10][365];
			for(int i = 0; i < activityList.size(); i++) {
				if (activityList.get(i).showInGui(sportsFilter, distanceFilter)) {
					int day = activityList.get(i).averageLap(activityList.get(i).getLap()).getStartTime().getDayOfYear();
					int dayOfMonth = activityList.get(i).averageLap(activityList.get(i).getLap()).getStartTime().getDayOfMonth();
					Month month = activityList.get(i).averageLap(activityList.get(i).getLap()).getStartTime().getMonth();
					int year = activityList.get(i).averageLap(activityList.get(i).getLap()).getStartTime().getYear();
					for(int j = 0; j < aggrList.length; j++){
						if(aggrListNames[j][day-1] == null) {
							aggrListNames[j][day-1] = dayOfMonth +"."+month+" "+year;
							if(filter.equals("distance")) {
								aggrList[j][day-1] = activityList.get(i).averageLap(activityList.get(i).getLap()).getDistanceMetersTracks();
							} else if(filter.equals("bpm")) {
								aggrList[j][day-1] = (double) activityList.get(i).averageLap(activityList.get(i).getLap()).getMaximumHeartRateBpm();
							} else if(filter.equals("speed")) {
								aggrList[j][day-1] = (double) activityList.get(i).averageLap(activityList.get(i).getLap()).getMaximumSpeed()*60;
							}
						} else if(aggrListNames[j][day-1] != null && aggrListNames[j][day-1].equals(dayOfMonth +"."+month+" "+year)) {
							if(filter.equals("distance")) {
								aggrList[j][day-1] += activityList.get(i).averageLap(activityList.get(i).getLap()).getDistanceMetersTracks();
							} else if(filter.equals("bpm")) {
								aggrList[j][day-1] += (double) activityList.get(i).averageLap(activityList.get(i).getLap()).getMaximumHeartRateBpm();
							} else if(filter.equals("speed")) {
								aggrList[j][day-1] += (aggrList[j][day-1]+(double) activityList.get(i).averageLap(activityList.get(i).getLap()).getMaximumSpeed()*60)/2;
							}
						} else if (j == aggrList.length-1) {
							extendList();
						}
					}
				}
			}
		} else if (view.equals("month")) {
			aggrList = new double[10][12];
			aggrListNames = new String[10][12];
			for(int i = 0; i < activityList.size(); i++) {
				if (activityList.get(i).showInGui(sportsFilter, distanceFilter)) {
					int month = activityList.get(i).averageLap(activityList.get(i).getLap()).getStartTime().getMonth().getValue();
					Month month2 = activityList.get(i).averageLap(activityList.get(i).getLap()).getStartTime().getMonth();
					int year = activityList.get(i).averageLap(activityList.get(i).getLap()).getStartTime().getYear();
					for(int j = 0; j < aggrList.length; j++){
						if(aggrListNames[j][month-1] == null) {
							aggrListNames[j][month-1] = month2 +" "+year;
							if(filter.equals("distance")) {
								aggrList[j][month-1] = activityList.get(i).averageLap(activityList.get(i).getLap()).getDistanceMetersTracks();
							} else if(filter.equals("bpm")) {
								aggrList[j][month-1] = (double) activityList.get(i).averageLap(activityList.get(i).getLap()).getMaximumHeartRateBpm();
							} else if(filter.equals("speed")) {
								aggrList[j][month-1] = (double) activityList.get(i).averageLap(activityList.get(i).getLap()).getMaximumSpeed()*60;
							}
						} else if(aggrListNames[j][month-1] != null && aggrListNames[j][month-1].equals(month2 +" "+year)) {
							if(filter.equals("distance")) {
								aggrList[j][month-1] += activityList.get(i).averageLap(activityList.get(i).getLap()).getDistanceMetersTracks();
							} else if(filter.equals("bpm")) {
								aggrList[j][month-1] += (double) activityList.get(i).averageLap(activityList.get(i).getLap()).getMaximumHeartRateBpm();
							} else if(filter.equals("speed")) {
								aggrList[j][month-1] = (aggrList[j][month-1]+(double) activityList.get(i).averageLap(activityList.get(i).getLap()).getMaximumSpeed()*60)/2;
							}
						} else if (j == aggrList.length-1) {
							extendList();
						}
					}
				}
			}
		} else {
			aggrList = new double[10][1];
			aggrListNames = new String[10][1];
			for(int i = 0; i < activityList.size(); i++) {
				if (activityList.get(i).showInGui(sportsFilter, distanceFilter)) {
					int year = activityList.get(i).averageLap(activityList.get(i).getLap()).getStartTime().getYear();
					for(int j = 0; j < aggrList.length; j++){
						if(aggrListNames[j][0] != null && aggrListNames[j][0].equals(""+year)) {
							if(filter.equals("distance")) {
								aggrList[j][0] += activityList.get(i).averageLap(activityList.get(i).getLap()).getDistanceMetersTracks();
							} else if(filter.equals("bpm")) {
								aggrList[j][0] += (double) activityList.get(i).averageLap(activityList.get(i).getLap()).getMaximumHeartRateBpm();
							} else if(filter.equals("speed")) {
								aggrList[j][0] = (aggrList[j][0]+(double) activityList.get(i).averageLap(activityList.get(i).getLap()).getMaximumSpeed()*60)/2;
							}
							break;
						} else if(aggrListNames[j][0] == null) {
							aggrListNames[j][0] = ""+year;
							if(filter.equals("distance")) {
								aggrList[j][0] = activityList.get(i).averageLap(activityList.get(i).getLap()).getDistanceMetersTracks();
							} else if(filter.equals("bpm")) {
								aggrList[j][0] = (double) activityList.get(i).averageLap(activityList.get(i).getLap()).getMaximumHeartRateBpm();
							} else if(filter.equals("speed")) {
								aggrList[j][0] = (double) activityList.get(i).averageLap(activityList.get(i).getLap()).getMaximumSpeed()*60;
							}
							break;
						} else if (j == aggrList.length-1) {
							extendList();
						}
					}
				}
			}
		}
	}

	/**
	 * extends the aggrList- and the aggrListNames-array
	 */
	public void extendList() {
		double[][] temp = new double[aggrList.length + 10][aggrList[0].length];
		String[][] tempNames = new String[aggrListNames.length + 10][aggrListNames[0].length];
		for(int i = 0; i < aggrList.length; i++) {
			for(int j = 0; j < aggrList[i].length; j++) {
				tempNames[i][j] = aggrListNames[i][j];
				temp[i][j] = aggrList[i][j];
			}
		}
		aggrListNames = tempNames;
		aggrList = temp;
	}

	/**
	 * @author Patrick Sallaberger
	 * an inner class to define the properties of a filter
	 */
	public class Filter{
		private final String name;
		private  boolean state;
		private final JCheckBoxMenuItem button;
		private final int number;

		/**
		 * @param name represents the name of the button
		 * @param state as a boolean value
		 * @param button contains the used button-object
		 * calls the constructor with the three parameters and 0
		 */
		public Filter(String name, boolean state, JCheckBoxMenuItem button) {
			this(name, state, button, 0);
		}
		
		/**
		 * @param name represents the name of the button
		 * @param state as a boolean value
		 * @param button contains the used button-object
		 * @param number contains a value used as upper or lower bound
		 */
		public Filter(String name, boolean state, JCheckBoxMenuItem button, int number) {
			this.name = name;
			this.state = state;
			this.button = button;
			this.number = number;
		}

		/**
		 * @return the actual state of the filter-object
		 */
		public boolean getState() {
			return this.state;
		}

		/**
		 * @return the number of the filter-object
		 */
		public int getNumber() {
			return this.number;
		}

		/**
		 * @return the name of the filter-object
		 */
		public String getName() {
			return this.name;
		}

		/**
		 * @return the button-object of the filter-object
		 */
		public JCheckBoxMenuItem getButton() {
			return this.button;
		}

		/**
		 * @param state as a boolean value
		 * sets the state of the filter-object
		 */
		public void setState(boolean state) {
			this.state = state;
		}
	}


	/**
	 * @author Susanne Gumplmayr
	 *
	 */
	public class Map extends JXMapViewer {

		private  final JXMapKit map;
		ArrayList<Waypoint> waypoints;
		RoutePainter routePainter;
		
		public Map () {
			this.map = new JXMapKit();
			this.map.setDefaultProvider(JXMapKit.DefaultProviders.OpenStreetMaps);
			map.setDataProviderCreditShown(true);
			map.setAddressLocationShown(true);
			lowerMapPanel.setLayout(new BorderLayout());
			lowerMapPanel.add(map, BorderLayout.CENTER);
			map.setVisible(true);
		}

		public Map (ArrayList<Waypoint> waypoints) {
			this.waypoints = waypoints;
			Set<Waypoint> waypointsFirst = new HashSet<>();
			this.map = new JXMapKit();
			this.map.setDefaultProvider(JXMapKit.DefaultProviders.OpenStreetMaps);
			this.map.setCenterPosition(waypoints.get(0).getPosition());
			WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<>();

			waypointsFirst.add(waypoints.get(0));
			waypointsFirst.add(waypoints.get(waypoints.size()-1));
			waypointPainter.setWaypoints(waypointsFirst);
			routePainter = new RoutePainter(this.waypoints);
			List<Painter<JXMapViewer>> painters = new ArrayList<>();
			painters.add(routePainter);
			painters.add(waypointPainter);

			CompoundPainter<JXMapViewer> painter = new CompoundPainter<>(painters);
			map.getMainMap().setOverlayPainter(painter);
			lowerMapPanel.setLayout(new BorderLayout());
			lowerMapPanel.add(map, BorderLayout.CENTER);
		}
	}
}
