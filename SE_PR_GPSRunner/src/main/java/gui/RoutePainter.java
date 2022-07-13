package gui;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.viewer.Waypoint;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;


/**
 * RoutePainter ist Teil der Map,
 * welche Methoden enthält diese überschrieben werden
 * @author Susanne Gumplmayr
 */
public class RoutePainter implements Painter<JXMapViewer> {
    private final Color color = Color.blue;

    private final ArrayList<Waypoint> waypoint;

    /**
     * @param waypoint
     */
    public RoutePainter(ArrayList<Waypoint> waypoint)
    {
        this.waypoint = new ArrayList<>(waypoint);
    }

    /**
     * @param g
     * @param map
     * @param w
     * @param h
     */
    @Override
    public void paint(Graphics2D g, JXMapViewer map, int w, int h)
    {
        g = (Graphics2D) g.create();

        Rectangle rect = map.getViewportBounds();
        g.translate(-rect.x, -rect.y);

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(4));

        drawRoute(g, map);

        g.setColor(color);
        g.setStroke(new BasicStroke(2));

        drawRoute(g, map);

        g.dispose();
    }

    /**
     * @param g
     * @param map gehört
     */
    private void drawRoute(Graphics2D g, JXMapViewer map)
    {
        double lastX = 0;
        double lastY = 0;

        boolean first = true;

        for (Waypoint wp : waypoint)
        {
            // convert geo-coordinate to world bitmap pixel
            Point2D pt = map.getTileFactory().geoToPixel(wp.getPosition(), map.getZoom());

            if (first)
            {
                first = false;
            }
            else
            {
                g.draw(new Line2D.Double(lastX, lastY,pt.getX(), pt.getY()));

            }

            lastX = pt.getX();
            lastY = pt.getY();
        }
    }
}