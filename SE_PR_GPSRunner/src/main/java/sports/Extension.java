package sports;

/**
 * Extension is a part of the TCX Data Structure
 * the TCX Data Structure is structured hierarchically
 * @author Susanne Gumplmayr
 */
public class Extension {

    private  Double speed;
    private  Integer runCadence;

    public Extension () {}

    public Extension(Double speed, Integer runCadence) {
        this.speed = speed;
        this.runCadence = runCadence;
    }


    /**
     * @return speed in Double format
     */
    public Double getSpeed() {
        return speed;
    }

    /**
     * @param speed in Double format
     */
    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    /**
     * @return run cadence in Integer format
     */
    public Integer getRunCadence() {
        return runCadence;
    }

    /**
     * @param runCadence in Integer format
     */
    public void setRunCadence(Integer runCadence) {
        this.runCadence = runCadence;
    }


    /**
     * @return String of the extension elements
     */
    @Override
    public String toString() {
        return "Extension{" +
                "speed=" + speed +
                ", runCadence=" + runCadence +
                '}';
    }
}
