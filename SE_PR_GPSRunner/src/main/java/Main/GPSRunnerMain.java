package Main;

import java.awt.EventQueue;

import controller.Controller;
import sports.ActivityList;
import gui.GUI;

public class GPSRunnerMain {

	public static void main(String[] args) {
		
		Controller controller = new Controller(new ActivityList(), new GUI());
		controller.getView().setVisible(true);
		controller.getView().updateChart(controller.getActivities().getActivities());
		controller.getView().refreshGui();
		
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				GUI g = new GUI();
//				g.setVisible(true);
//				g.setActivities(activities);
//
//			}
//		});
	}
}
