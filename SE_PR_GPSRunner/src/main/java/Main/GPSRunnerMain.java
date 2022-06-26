package Main;


import controller.Controller;
import sports.ActivityList;
import gui.GUI;

public class GPSRunnerMain {

	public static void main(String[] args) {
		// Variable merken und aufrufe überflüssig
		Controller controller = new Controller(new ActivityList(), new GUI());
		controller.getView().setController(controller);
		controller.getView().setVisible(true);
		controller.getView().updateChart(controller.getActivities().getActivities());
		controller.getView().refreshGui();
		// Controller ist noc nciht ferrig mit refresh GUI
		controller.getActivities().readFiles();


	}
}
