package controller;

import gui.GUI;
import sports.ActivityList;

public class Controller {
	
	private ActivityList activities;
	private GUI view;
	
	public Controller(ActivityList activities, GUI view){
		this.activities = activities;
		this.view = view;
		
		activities.setController(this);
		view.setActivityList(activities.getActivities());
		view.setTrackGPS(activities.getTrackGPS());
		view.setController(this);
	}

	public ActivityList getActivities() {
		return activities;
	}

	public GUI getView() {
		return view;
	}
	public void setPath(String filepath) {
		this.activities.setFilepath(filepath);
	}
	
	public void setActivities() {
		this.view.setActivities(activities.getActivities());
	}
}
