package Main;

import java.awt.EventQueue;

import controller.Controller;
import sports.ActivityList;
import gui.GUI;

public class GPSRunnerMain {

	public static void main(String[] args) {
		
		Controller controller = new Controller(new ActivityList(), new GUI());
		controller.view.setVisible(true);
		controller.view.setActivities(controller.activities.activities);
		controller.view.refreshGui();
		
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
