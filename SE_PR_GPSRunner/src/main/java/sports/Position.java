package sports;


/**
 * Position is a part of the TCX Data Structure
 * the TCX Data Structure is structured hierarchically
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
     * @return latitude in Double format
     * unit in degrees
     */
    public Double getLatitudeDegrees() {
        return latitudeDegrees;
    }

    /**
     * @param latitudeDegrees in Double format
     * unit in degrees
     */
    public void setLatitudeDegrees(Double latitudeDegrees) {
        this.latitudeDegrees = latitudeDegrees;
    }

    /**
     * @return longitude in Double format
     * unit in degrees
     */
    public Double getLongitudeDegrees() {
        return longitudeDegrees;
    }

    /**
     * @param longitudeDegrees in Double format
     * unit in degrees
     */
    public void setLongitudeDegrees(Double longitudeDegrees) {
        this.longitudeDegrees = longitudeDegrees;
    }

    /**
     * @return String of the position elements
     */
    @Override
    public String toString() {
        return "Position{" +
                "latitudeDegrees=" + latitudeDegrees +
                ", longitudeDegrees=" + longitudeDegrees +
                '}';
    }
}
