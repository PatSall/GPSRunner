package controller;

import gui.GUI;
import sports.ActivityList;

public class Controller {
	
	public ActivityList activities;
	public GUI view;
	
	public Controller(ActivityList activities, GUI view){
		this.activities = activities;
		this.view = view;
		
		view.setActivityList(activities.activities);
		view.setTrackGPS(activities.trackGPS);
	}
}
