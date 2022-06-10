package controller;

import gui.GUI;
import sports.ActivityList;

import java.util.Timer;

public class Controller {
	
	private final ActivityList  activities;
	private final GUI view;
	Timer timer = new Timer();
	public Controller(ActivityList activities, GUI view){
		this.activities = activities;
		this.view = view;
		
		activities.setController(this);
		view.setActivityList(activities.getActivities());
		view.setTrackGPS(activities.getTrackGPS());
		view.setController(this);
		timer.schedule(new UpdateUITask(view,activities), 0, 1000);
	}

	public ActivityList getActivities() {
		return activities;
	}

	public GUI getView() {
		return view;
	}
	public void setPath(String filepath) {
		System.out.println("Controller " + filepath);
		this.activities.setFilepath(filepath);
	}

}
