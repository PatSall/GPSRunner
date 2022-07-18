package gui;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.viewer.Waypoint;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;


/**
 * RoutePainter is part of the map
 * @author Susanne Gumplmayr
 */
public class RoutePainter implements Painter<JXMapViewer> {
    private final Color color = Color.blue;

    private final ArrayList<Waypoint> waypoint;

    /**
     * @param waypoint in Waypoint format
     * a list of waypoints
     */
    public RoutePainter(ArrayList<Waypoint> waypoint)
    {
        this.waypoint = new ArrayList<>(waypoint);
    }


    /**
     * @param g graphic in Graphics2D format
     * @param map in JXMapViewer format
     * @param w weight in Integer format
     * @param h height in Integer format
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
     * @param g graphic in Graphics2D format
     * @param map in JXMapViewer format
     */
    private void drawRoute(Graphics2D g, JXMapViewer map)
    {
        double lastX = 0;
        double lastY = 0;

        boolean first = true;

        for (Waypoint wp : waypoint)
        {
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