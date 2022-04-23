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
import sports.Activity;
import sports.TrackGPS;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import javax.swing.table.DefaultTableModel;
//import javax.swing.plaf.synth.ColorType;

//import org.w3c.dom.css.RGBColor;

public class GUI extends JFrame {
	
	List<Activity> activityList;
	List<TrackGPS> trackGPS;
	
	Filter[] sportsFilter = new Filter[6];
	Filter[] distanceFilter = new Filter[7];
	
	JScrollPane upperPanel;
	JTable table;
	DefaultTableModel model;
	JPanel lowerPanel;
	JPanel sidePanel;
	ChartPanel chartPanel;
	Collection<Activity> activities;
	
	String[] columnNames = new String[]{"name","activity", "date","distance","time","speed","bpm"};

	public void setActivities(Collection<Activity> activities) {
		this.activities = activities;
		DefaultCategoryDataset cd = new DefaultCategoryDataset();

		//mit Streams activities transformieren
		cd.addValue(3.5,"stefan", "distance");
		cd.addValue(5,"Jan", "distance");

		JFreeChart chart = ChartFactory.createBarChart("Distance Overview",
				null, "km", cd, PlotOrientation.VERTICAL, true, true,
				false);
		chartPanel.setChart(chart);
	}

	public GUI() {
	
		setTitle("GPSRunner");
		setSize(500,500);
		//setSize(new Dimension());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());

		model = new DefaultTableModel(new Object[0][7], columnNames);
		
		table = new JTable(model);
		table.setFillsViewportHeight(true);
		upperPanel = new JScrollPane(table);
		lowerPanel = new JPanel();
		sidePanel = new JPanel();
		
		chartPanel= new ChartPanel(null);
		chartPanel.setPreferredSize(new Dimension(300,300));
		lowerPanel.add(chartPanel);
		
		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, upperPanel, lowerPanel);
		JSplitPane sp2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sp, sidePanel);
		
		this.add(sp2, BorderLayout.CENTER);
		
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
		JMenu open = new JMenu("Open...");
		
		menu.add(file);
		menu.add(tracks);
		menu.add(segments);
		//menu.add(graph);
		//menu.add(view);
		//menu.add(years);
		//menu.add(columns);
		
		setJMenuBar(menu);
		
		//File
		JMenuItem neo = new JMenuItem("New");
		JMenuItem tcx = new JMenuItem(".tcx");
		tcx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser("C:\\Users\\patri\\OneDrive\\Uni\\WINF\\4. Semester\\Software Engineering\\Testfiles\\Test");
			    chooser.showOpenDialog(null);
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
		JCheckBoxMenuItem tenM = new JCheckBoxMenuItem("10m", true);
		JCheckBoxMenuItem twentyM = new JCheckBoxMenuItem("20m", true);
		JCheckBoxMenuItem fiftyM = new JCheckBoxMenuItem("50m", true);
		JCheckBoxMenuItem hundredM = new JCheckBoxMenuItem("100m", true);
		JCheckBoxMenuItem quarterKm = new JCheckBoxMenuItem("250m",true);
		JCheckBoxMenuItem halfKm = new JCheckBoxMenuItem("500m", true);
		JCheckBoxMenuItem oneKm = new JCheckBoxMenuItem("1000m", true);
		
		distanceFilter[0] = new Filter(tenM.getLabel(), tenM.getState(), tenM);
		distanceFilter[1] = new Filter(twentyM.getLabel(), twentyM.getState(), twentyM);
		distanceFilter[2] = new Filter(fiftyM.getLabel(), fiftyM.getState(), fiftyM);
		distanceFilter[3] = new Filter(hundredM.getLabel(), hundredM.getState(), hundredM);
		distanceFilter[4] = new Filter(quarterKm.getLabel(), quarterKm.getState(), quarterKm);
		distanceFilter[5] = new Filter(halfKm.getLabel(), halfKm.getState(), halfKm);
		distanceFilter[6] = new Filter(oneKm.getLabel(), oneKm.getState(), oneKm);
		
		tenM.addActionListener(action());
		twentyM.addActionListener(action());
		fiftyM.addActionListener(action());
		hundredM.addActionListener(action());
		quarterKm.addActionListener(action());
		halfKm.addActionListener(action());
		oneKm.addActionListener(action());

		//add to menu-elems
		file.add(neo);
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
		
		//add to submenu-elems
		open.add(tcx);
	}
	
	public void refreshGui() {

		model.setDataVector(table(), columnNames);
		model.fireTableChanged(null);
		
	}
	
	
	public Object[][] table() {
		
		Object[][] data = new Object[activityList.size()][7];
	
		for (int i = 0; i < activityList.size(); i++ ) {
			for(int j = 0; j < sportsFilter.length; j++) {
				if(sportsFilter[0].getState() || (sportsFilter[j].getName().equals(activityList.get(i).getActivity()) && sportsFilter[j].getState())) {
					for(int k = 6; k >= 0; k--) {
						if(distanceFilter[k].getState() && getDistance(k) >= activityList.get(i).getLap().get(0).getDistanceMetersTracks()) {
							data[i][0] = activityList.get(i).getId();
							data[i][1] = activityList.get(i).getActivity();
							data[i][2] = activityList.get(i).getLap().get(0).getStartTime();			
							data[i][3] = activityList.get(i).getLap().get(0).getDistanceMetersTracks();
							data[i][4] = activityList.get(i).getLap().get(0).getTotalTimeSeconds();
							data[i][5] = activityList.get(i).getLap().get(0).getMaximumSpeed();
							data[i][6] = activityList.get(i).getLap().get(0).getMaximumHeartRateBpm();
							break;
						}
					}
				}
			}
		}
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
				}
				for(int i = 0; i < sportsFilter.length; i++) {
					System.out.println(sportsFilter[i].name+ " | "+sportsFilter[i].state);
				}
				
				for(int i = 0; i < distanceFilter.length; i++) {
					System.out.println(distanceFilter[i].name+ " | "+distanceFilter[i].state);
				}
				System.out.println();
				refreshGui();
			}
			
		};
	}
	
	public int getDistance(int index) {
		if(index == 6) {
			return 1000;
		} else if (index == 5) {
			return 500;
		}else if (index == 4) {
			return 250;
		}else if (index == 3) {
			return 100;
		}else if (index == 2) {
			return 50;
		}else if (index == 1) {
			return 20;
		}else if (index == 0) {
			return 10;
		}
		return 0;
	}
	
	public class Filter{
		private String name;
		private boolean state;
		private JCheckBoxMenuItem button;

		public Filter(String name, boolean state, JCheckBoxMenuItem button) {
			this.name = name;
			this.state = state;
			this.button = button;
		}

		public boolean getState() {
			return this.state;
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
