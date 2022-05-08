package sports;

import java.time.LocalDateTime;
import java.util.List;

public class TrackPoint {

    private Double elem;
    private LocalDateTime time;


    public TrackPoint () {

    }

    public TrackPoint(Double elem, LocalDateTime time) {
        this.elem = elem;
        this.time = time;
    }

    public TrackPoint(Double elem) {
        this.elem = elem;
        this.time = null;
    }

    public Double getElem() {
        return elem;
    }

    public void setElem(Double elem) {
        this.elem = elem;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }


    @Override
    public String toString() {
        return "TrackPoint{" +
                "elem=" + elem +
                ", time=" + time +
                '}';
    }
}
