package sports;

/**
 * Extension ist Teil der Struktur der TCX Files,
 * welche hierarchisch aufgebaut sind.
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
     * @return liefert Speed im Double Format
     */
    public Double getSpeed() {
        return speed;
    }

    /**
     * @param speed setzt Speed im Double Format
     */
    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    /**
     * @return liefert RunCadence im Integer Format
     */
    public Integer getRunCadence() {
        return runCadence;
    }

    /**
     * @param runCadence setzt RunCadence im Integer Format
     */
    public void setRunCadence(Integer runCadence) {
        this.runCadence = runCadence;
    }


    /**
     * @return String bestehend aus allen Extension Attributen
     */
    @Override
    public String toString() {
        return "Extension{" +
                "speed=" + speed +
                ", runCadence=" + runCadence +
                '}';
    }
}
