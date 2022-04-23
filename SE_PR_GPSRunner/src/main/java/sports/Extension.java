package sports;

public class Extension {

    private  Double speed;
    private  Integer runCadence;

    public Extension () {};

    public Extension(Double speed, Integer runCadence) {
        this.speed = speed;
        this.runCadence = runCadence;
    }


    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Integer getRunCadence() {
        return runCadence;
    }

    public void setRunCadence(Integer runCadence) {
        this.runCadence = runCadence;
    }


    @Override
    public String toString() {
        return "Extension{" +
                "speed=" + speed +
                ", runCadence=" + runCadence +
                '}';
    }
}
