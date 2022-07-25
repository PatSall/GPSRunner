package controller;

import gui.GUI;
import sports.ActivityList;

import java.util.Timer;

public class Controller {
	
	private final ActivityList  activities;
	private final GUI view;
	Timer timer = new Timer();
	
	/**
	 * @param activities List of all read activities
	 * @param view the object of the graphical user interface
	 * creates an object from the controller data type and sets the activity and trackGPS fields in the GUI.
	 * it also starts a schedule from the class java.util.timer which calls the Constructor of UpdateUITask every second.
	 */
	public Controller(ActivityList activities, GUI view){

		this.activities = activities;
		this.view = view;

		activities.setController(this);
		view.setActivities(activities.getActivities());
		view.setTrackGPS(activities.getTrackGPX());
		view.setController(this);
		timer.schedule(new UpdateUITask(view, activities), 0, 1000);
	}

	/**
	 * @return a List of activities;
	 */
	public ActivityList getActivities() {
		return activities;
	}

	/**
	 * @return the GUI object
	 */
	public GUI getView() {
		return view;
	}
	
	/**
	 * @param stands for a string with a path within the operating system
	 * it sets a new filepath in the datamodel.
	 */
	public void setPath(String filepath) {
		this.activities.setFilepath(filepath);
	}

}
