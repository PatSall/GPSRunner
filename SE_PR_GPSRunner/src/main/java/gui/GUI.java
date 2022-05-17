package gui;

//import java.awt.EventQueue;
//import java.awt.*;
//import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

import controller.Controller;
import sports.Activity;
import sports.TrackGPS;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JCheckBoxMenuItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

//import java.io.File;
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.net.URISyntaxException;
//import java.net.URL;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.Popup;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableModel;
//import javax.swing.plaf.synth.ColorType;

//import org.w3c.dom.css.RGBColor;

public class GUI extends JFrame {
	
	List<Activity> activityList;
	List<TrackGPS> trackGPS;
	Object[][] actualData;
	
	Filter[] sportsFilter = new Filter[6];
	Filter[] distanceFilter = new Filter[10];
	Filter[] graphFilter = new Filter[3];
	
	JScrollPane upperPanel;
	JTable table;
	DefaultTableModel model;
	JPanel lowerPanel;
	JPanel lowerDetailPanel;
	ChartPanel chartPanel;
	Collection<Activity> activities;
	
	JCheckBoxMenuItem distance;
	JCheckBoxMenuItem bpm;
	JCheckBoxMenuItem speed;
	DefaultCategoryDataset cd;
	
	Controller parent = null;
	
	String[] columnNames = new String[]{"name","activity","date","distance","time","speed","bpm"};
	
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
					yAxe = "Miles per Hour";
				}
			}
		}
		for (int i = 0; i < activityList.size(); i++) {
			if (activityList.get(i).showInGui(sportsFilter, distanceFilter)) {
				cd.addValue(activityList.get(i).showInGraph(graphFilter),activityList.get(i).getId(), xAxe);
			}
		}
		
		JFreeChart chart = ChartFactory.createBarChart(graphName,
				null, yAxe, cd, PlotOrientation.VERTICAL, true, true,
				false);
		chartPanel.setChart(chart);
	}
	
	public void setController(Controller parent) {
		this.parent = parent;
	}
	
	public GUI() {
		
		setTitle("GPSRunner");
		setSize(500,500);
		//setSize(new Dimension());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		//Vielleicht Umstieg auf...
		//setLayout(new CardLayout());

		model = new DefaultTableModel(new Object[0][7], columnNames);
		
		table = new JTable(model);
		table.setFillsViewportHeight(true);
		upperPanel = new JScrollPane(table);
		lowerPanel = new JPanel();
		lowerDetailPanel = new JPanel();
		
		table.addMouseListener(click());
		
		cd = new DefaultCategoryDataset();
		chartPanel= new ChartPanel(null);
		chartPanel.setPreferredSize(new Dimension(650, 400));
		lowerPanel.add(chartPanel);
		
		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, upperPanel, lowerPanel);
		
		this.add(sp, BorderLayout.CENTER);
		
		//menu elements
		JMenuBar menu = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu tracks = new JMenu("Tracks");
		JMenu segments = new JMenu("Segments");
		JMenu graph = new JMenu("Graph");
		JMenu view = new JMenu("View");
		JMenu years = new JMenu("Years");
		JMenu columns = new JMenu("Columns");

		//submenu(s) elements
		JMenu open = new JMenu("Directory...");
		
		menu.add(file);
		menu.add(tracks);
		menu.add(segments);
		menu.add(graph);
		//menu.add(view);
		//menu.add(years);
		//menu.add(columns);
		
		setJMenuBar(menu);
		
		//File
		
		JMenuItem tcx = new JMenuItem("change directory");
		tcx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser("C:\\Users\\patri\\OneDrive\\Uni\\WINF\\4. Semester\\Software Engineering\\Testfiles\\Test");
			    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.showOpenDialog(null);
				if (chooser.getSelectedFile() != null) {
				    System.out.println(chooser.getSelectedFile().getPath());
				    parent.setPath(chooser.getSelectedFile().getPath());
			 }
			}
		});
		
		//Tracks
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

		all.addActionListener(action());
		running.addActionListener(action());
		skiing.addActionListener(action());
		diving.addActionListener(action());
		flying.addActionListener(action());
		hiking.addActionListener(action());
		
		//Segments
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
		
		//Graph
		distance = new JCheckBoxMenuItem("distance", true);
		bpm = new JCheckBoxMenuItem("bpm");
		speed = new JCheckBoxMenuItem("speed");
		
		distance.addActionListener(action());
		bpm.addActionListener(action());
		speed.addActionListener(action());
		
		graphFilter[0] = new Filter(distance.getLabel(), distance.getState(), distance);
		graphFilter[1] = new Filter(bpm.getLabel(), bpm.getState(), bpm);
		graphFilter[2] = new Filter(speed.getLabel(), speed.getState(), speed);

		//add to menu-elems
		file.add(open);
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
		
		//add to submenu-elems
		open.add(tcx);
	}
	
	public MouseListener click() {
		return new MouseListener() {
			@Override
			public void mousePressed(MouseEvent me) {
			    JTable table =(JTable) me.getSource();
			    Point p = me.getPoint();
			    int row = table.rowAtPoint(p);
			    System.out.println(row);
			    getEvent((String) actualData[row][0]);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		};
	}
	
	public void refreshGui() {
		model.setDataVector(table(), columnNames);
		model.fireTableChanged(null);
		this.updateChart(activityList);
	}
	
	public Object[][] table() {
		int counter = 0;
		int countEntries = 0;
		for (int i = 0; i < activityList.size(); i++ ) {
			if (activityList.get(i).showInGui(sportsFilter, distanceFilter)) {
				countEntries++;
			}
		}
		Object[][] data = new Object[countEntries][7];
		for (int i = 0; i < activityList.size(); i++ ) {
			if (activityList.get(i).showInGui(sportsFilter, distanceFilter)) {
				data[counter][0] = activityList.get(i).getId();
				data[counter][1] = activityList.get(i).getActivity();
				data[counter][2] = activityList.get(i).averageLap(activityList.get(i).getLap()).getStartTime();			
				data[counter][3] = Math.round(activityList.get(i).averageLap(activityList.get(i).getLap()).getDistanceMetersTracks()*100)/100.0;
				data[counter][4] = activityList.get(i).averageLap(activityList.get(i).getLap()).getTotalTimeSeconds();
				data[counter][5] = Math.round(activityList.get(i).averageLap(activityList.get(i).getLap()).getMaximumSpeed()*100)/100.0;
				data[counter][6] = activityList.get(i).averageLap(activityList.get(i).getLap()).getMaximumHeartRateBpm();
				counter++;
			}
		}
		actualData = data;
		return data;
	}
	
	public void setActivityList(List<Activity> activityList) {
		this.activityList = activityList;
	}
	
	public void setTrackGPS(List<TrackGPS> trackGPS) {
		this.trackGPS = trackGPS;
	}
	
	public void createPanels() {
		
	}
	
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
				}
//				for(int i = 0; i < sportsFilter.length; i++) {
//					System.out.println(sportsFilter[i].name+ " | "+sportsFilter[i].state);
//				}
//				
//				for(int i = 0; i < distanceFilter.length; i++) {
//					System.out.println(distanceFilter[i].name+ " | "+distanceFilter[i].state);
//				}
//				System.out.println();
				refreshGui();
			}
		};
	}
	
	//TODO
	public void getEvent(String name) {
		for(Activity a : activityList) {
			if(name.equals(a.getId())) {
				//return Trackpoints
				System.out.println(name);
			}
		}
	}
	
	public void setActivities(List<Activity> activityList) {
		this.activityList = activityList; 
		refreshGui();
	}
	
	public class Filter{
		private String name;
		private boolean state;
		private JCheckBoxMenuItem button;
		private int number;

		public Filter(String name, boolean state, JCheckBoxMenuItem button) {
			this(name, state, button, 0);
		}
		public Filter(String name, boolean state, JCheckBoxMenuItem button, int number) {
			this.name = name;
			this.state = state;
			this.button = button;
			this.number = number;
		}

		public boolean getState() {
			return this.state;
		}
		
		public int getNumber() {
			return this.number;
		}

		public String getName() {
			return this.name;
		}
		
		public JCheckBoxMenuItem getButton() {
			return this.button;
		}

		public void setState(boolean state) {
			this.state = state;
		}
	}
}
