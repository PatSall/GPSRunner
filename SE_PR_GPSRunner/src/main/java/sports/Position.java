package sports;


/**
 * Position ist Teil der Struktur der TCX Files,
 * welche hierarchisch aufgebaut sind.
 * @author Susanne Gumplmayr
 */
public class Position {
    private  Double latitudeDegrees;
    private  Double longitudeDegrees;

    public Position () {

    }

    public Position(Double latitudeDegrees, Double longitudeDegrees) {
        this.latitudeDegrees = latitudeDegrees;
        this.longitudeDegrees = longitudeDegrees;
    }

    /**
     * @return liefert Latitude Degress im Double Format
     */
    public Double getLatitudeDegrees() {
        return latitudeDegrees;
    }

    /**
     * @param latitudeDegrees setzt Latitude Degress im Double Format
     */
    public void setLatitudeDegrees(Double latitudeDegrees) {
        this.latitudeDegrees = latitudeDegrees;
    }

    /**
     * @return liefert Longitude Degress im Double Format
     */
    public Double getLongitudeDegrees() {
        return longitudeDegrees;
    }

    /**
     * @param longitudeDegrees setzt Longitude Degress im Double Format
     */
    public void setLongitudeDegrees(Double longitudeDegrees) {
        this.longitudeDegrees = longitudeDegrees;
    }

    /**
     * @return String bestehend aus allen Position Attributen
     */
    @Override
    public String toString() {
        return "Position{" +
                "latitudeDegrees=" + latitudeDegrees +
                ", longitudeDegrees=" + longitudeDegrees +
                '}';
    }
}
