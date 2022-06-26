package controller;

import java.awt.EventQueue;
import java.util.TimerTask;

import gui.GUI;
import sports.ActivityList;

public class UpdateUITask extends TimerTask {

    GUI view;
    ActivityList activities;

    public UpdateUITask(GUI view, ActivityList activities) {
        this.view = view;
        this.activities = activities;

    }


    @Override
    public void run() {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                System.out.println("UPDATE JETZT:" + activities.getActivities().size() + " Aktivitäten");
                System.out.println("update Jet " + view.getActivities().size());
                view.setActivities(activities.getActivities());
            }
        });

    }

}
