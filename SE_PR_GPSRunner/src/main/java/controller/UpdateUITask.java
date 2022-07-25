package controller;

import java.awt.EventQueue;
import java.util.TimerTask;

import gui.GUI;
import sports.ActivityList;

public class UpdateUITask extends TimerTask {

    GUI view;
    ActivityList activities;

    /**
     * @param view represents the GUI-object
     * @param activities a List of all actual activities
     */
    public UpdateUITask(GUI view, ActivityList activities) {
        this.view = view;
        this.activities = activities;
    }


    /**
     * implements the abstract method run() from TimerTask to be executable in a separate thread
     */
    @Override
    public void run() {
        EventQueue.invokeLater(() -> {
            view.setActivities(activities.getActivities());
        });

    }

}
