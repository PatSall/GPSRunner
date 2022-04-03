package sports;

import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;



// order of the fields in XML
/*@XmlType(propOrder = {"sport", "destination", "startTime",
                "totalTimeSeconds", "distanceMeters",
        "maximumSpeed","calories" , "averageHeartRateBpm",
        "maximumHeartRateBpm", "intensity", "triggerMethod"
})*/
//@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;
    private String sport;

    private String destination;

    private String startTime;

    private Double totalTimeSeconds;

    private Double distanceMeters;

    private Double maximumSpeed;

    private Integer calories;

    private Integer averageHeartRateBpm;

    private Integer maximumHeartRateBpm;

    private String intensity;

    private String triggerMethod;

    public Activity() {

    }

    public Activity(String sport, String destination, String startTime, Double totalTimeSeconds, Double distanceMeters, Double maximumSpeed, Integer calories, Integer averageHeartRateBpm, Integer maximumHeartRateBpm, String intensity, String triggerMethod) {
        this.sport = sport;
        this.destination = destination;
        this.startTime = startTime;
        this.totalTimeSeconds = totalTimeSeconds;
        this.distanceMeters = distanceMeters;
        this.maximumSpeed = maximumSpeed;
        this.calories = calories;
        this.averageHeartRateBpm = averageHeartRateBpm;
        this.maximumHeartRateBpm = maximumHeartRateBpm;
        this.intensity = intensity;
        this.triggerMethod = triggerMethod;
    }

    @XmlAttribute
    public String getSport() {
        return sport;
    }
    @XmlElement(name = "Id")
    public String getDestination() {
        return destination;
    }
   @XmlElement(name = "Lap")
    public String getStartTime() {
        return startTime;
    }

    @XmlElement(name = "TotalTimeSeconds")
    public Double getTotalTimeSeconds() {
        return totalTimeSeconds;
    }

    @XmlElement(name = "DistanceMeters")
    public Double getDistanceMeters() {
        return distanceMeters;
    }


    @XmlElement(name = "MaximumSpeed")
    public Double getMaximumSpeed() {
        return maximumSpeed;
    }

    @XmlElement(name = "Calories")
    public Integer getCalories() {
        return calories;
    }

    public Integer getAverageHeartRateBpm() {
        return averageHeartRateBpm;
    }

    @XmlElement(name = "MaximumHeartRateBpm")
    public Integer getMaximumHeartRateBpm() {
        return maximumHeartRateBpm;
    }

    @XmlElement(name = "Intensity")
    public String getIntensity() {
        return intensity;
    }

    @XmlElement(name = "TriggerMethod")
    public String getTriggerMethod() {
        return triggerMethod;
    }


    public void setSport(String sport) {
        this.sport = sport;
    }


    public void setDestination(String destination) {
        this.destination = destination;
    }


    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }


    public void setTotalTimeSeconds(Double totalTimeSeconds) {
        this.totalTimeSeconds = totalTimeSeconds;
    }


    public void setDistanceMeters(Double distanceMeters) {
        this.distanceMeters = distanceMeters;
    }


    public void setMaximumSpeed(Double maximumSpeed) {
        this.maximumSpeed = maximumSpeed;
    }


    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    @XmlElement(name = "AverageHeartRateBpm")
    public void setAverageHeartRateBpm(Integer averageHeartRateBpm) {
        this.averageHeartRateBpm = averageHeartRateBpm;
    }


    public void setMaximumHeartRateBpm(Integer maximumHeartRateBpm) {
        this.maximumHeartRateBpm = maximumHeartRateBpm;
    }


    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }


    public void setTriggerMethod(String triggerMethod) {
        this.triggerMethod = triggerMethod;
    }

    @Override
    public String toString () {
        return "Activity " + this.sport + " destination " +
        this.destination + " \n startTime " +
        this.startTime + " totalTimeSeconds " +
        this.totalTimeSeconds + " distanceMeters " +
        this.distanceMeters + " maximumSpeed " +
        this.maximumSpeed + " \n calories " +
        this.calories + " averageHeartRateBpm " +
        this.averageHeartRateBpm + " maximumHeartRateBpm " +
        this.maximumHeartRateBpm + " intensity " +
        this.intensity + " triggerMethod " +
        this.triggerMethod ;
    }

    void beforeUnmarshal(Unmarshaller unmarshaller, Object parent) {
        System.out.println("Before Unmarshaller Callback");
    }

    void afterUnmarshal(Unmarshaller unmarshaller, Object parent) {
        System.out.println("After Unmarshaller Callback");
    }
}
