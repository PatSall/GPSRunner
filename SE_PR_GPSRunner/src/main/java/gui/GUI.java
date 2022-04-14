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

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
import javax.swing.JSplitPane;
//import javax.swing.plaf.synth.ColorType;

//import org.w3c.dom.css.RGBColor;

public class GUI extends JFrame {
	
	JPanel upperPanel = new JPanel();
	JPanel lowerPanel = new JPanel();
	JPanel sidePanel = new JPanel();
	ChartPanel chartPanel= new ChartPanel(null);
	Collection<Activity> activities = new ArrayList<>();


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
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setLayout(new BorderLayout());
		
		JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, upperPanel, lowerPanel);
		JSplitPane sp2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sp, sidePanel);

		chartPanel.setPreferredSize(new Dimension(200,200));

		lowerPanel.add(chartPanel);
		
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
		menu.add(graph);
		menu.add(view);
		menu.add(years);
		menu.add(columns);
		
		setJMenuBar(menu);
		
		//File
		JMenuItem neo = new JMenuItem("New");
		JMenuItem tcx = new JMenuItem(".tcx");
		tcx.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				JFileChooser chooser = new JFileChooser("C:\\Users\\patri\\OneDrive\\Uni\\WINF\\4. Semester\\Software Engineering\\Software Engineering - Team");
			    chooser.showOpenDialog(null);
			    
//				Desktop desktop = Desktop.getDesktop();
//				URL url = null;
//				try {
//					url = new URL("https://kusss.jku.at");
//				} catch (MalformedURLException e1) {
//					e1.printStackTrace();
//				}
//				try {
//					desktop.browse(url.toURI());
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				} catch (URISyntaxException e1) {
//					e1.printStackTrace();
//				}
			} 
		});
		//Tracks
		JMenuItem running = new JMenuItem("Running");
		JMenuItem skiing = new JMenuItem("Skiing");
		JMenuItem diving = new JMenuItem("Diving");
		JMenuItem flying = new JMenuItem("Flying");
		JMenuItem hiking = new JMenuItem("Hiking");
		
		//Segments
		JMenuItem tenM = new JMenuItem("10m");
		JMenuItem twentyM = new JMenuItem("20m");
		JMenuItem fiftyM = new JMenuItem("50m");
		JMenuItem hundredM = new JMenuItem("100m");
		JMenuItem quarterKm = new JMenuItem("250m");
		JMenuItem halfKm = new JMenuItem("500m");
		JMenuItem oneKm = new JMenuItem("1000m");		
		
		//add to menu-elems
		file.add(neo);
		file.add(open);
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


//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			
//			public void run() {
//				GUI g = new GUI();
//				g.setVisible(true);
//			}
//		});
//	}

}
