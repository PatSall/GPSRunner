package Main;


import controller.Controller;
import sports.ActivityList;
import gui.GUI;

public class GPSRunnerMain {

	public static void main(String[] args) {
		
		Controller controller = new Controller(new ActivityList(), new GUI());
		controller.getView().setController(controller);
		controller.getView().setVisible(true);
		controller.getView().updateChart(controller.getActivities().getActivities());
		controller.getView().refreshGui();
		controller.getActivities().readFiles();

	}
}
