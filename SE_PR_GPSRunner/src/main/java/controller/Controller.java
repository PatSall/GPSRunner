package controller;

import gui.GUI;
import sports.ActivityList;

public class Controller {
	
	private ActivityList activities;
	private GUI view;
	
	public Controller(ActivityList activities, GUI view){
		this.activities = activities;
		this.view = view;
		
		view.setActivityList(activities.getActivities());
		view.setTrackGPS(activities.getTrackGPS());
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
		this.activities.getActivities();
		this.view.setActivities(activities.getActivities());;
	}
}
