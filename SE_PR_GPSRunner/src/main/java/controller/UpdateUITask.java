package controller;

import java.awt.EventQueue;
import java.util.TimerTask;

import gui.GUI;
import sports.ActivityList;


/**
 * Activity ist Teil der Struktur der TCX Files,
 * welche hierarchisch aufgebaut sind.
 * @author Patrick Sallaberger & Susanne Gumplmayr
 */
public class UpdateUITask extends TimerTask {

    GUI view;
    ActivityList activities;

    public UpdateUITask(GUI view, ActivityList activities) {
        this.view = view;
        this.activities = activities;

    }


    /**
     *
     */
    @Override
    public void run() {
        EventQueue.invokeLater(() -> {
            System.out.println("UPDATE JETZT:" + activities.getActivities().size() + " Aktivitäten");
            System.out.println("update getActivity " + view.getActivities().size());
            view.setActivities(activities.getActivities());
        });

    }

}
