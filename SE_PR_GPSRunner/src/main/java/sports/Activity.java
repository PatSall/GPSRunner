package sports;

import java.util.*;


public class Activity {

    private static final long serialVersionUID = 1L;
    private List<Lap> lap;
    private String activity;
    private String id;

    public Activity() {

    }

    public Activity(String activity) {
        this.activity = activity;
    }


    public String getActivity() {
        return activity;
    }

    public void setActivity(String sport) {
        this.activity = sport;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Lap> getLap() {
        return lap;
    }

    public void setLap(List<Lap> laps) {
        this.lap = laps;
    }

    @Override
    public String toString () {
        return "Activity " + this.activity + "\n ID " + this.id + "\n Lap" +  this.lap;
    }
}
