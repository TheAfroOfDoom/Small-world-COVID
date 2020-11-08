package cc;

import java.awt.*;

import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.lang.Math;
import java.util.ArrayList;

public class GraphPanel extends JPanel {

    ArrayList<Vertex> infectedVerts;
    ArrayList<Vertex> verts;
    int maxRadius;
    int dimension;
    int nVerts;

    public GraphPanel(int nVerts) {
        super();
        dimension = 800;
        setPreferredSize(new Dimension(dimension, dimension));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 10));

        double averageDegree = 5;
        double curvature = -1;
        HyperbolicRandomGraphGenerator hrgg = new HyperbolicRandomGraphGenerator(nVerts, averageDegree, curvature);

        maxRadius = (int) hrgg.getMaxRadius();
        this.nVerts = nVerts;
        verts = new ArrayList<>();
        for (Vertex vert : hrgg.vertices) {
            verts.add(vert);
        }
        infectedVerts = new ArrayList<>();
        update(true);
    }

    public void paint(Graphics g) {
        for (Vertex vert : this.verts) {
            vert.draw(g);
            for (int index : vert.connections) {
                g.drawLine(vert.x, vert.y, verts.get(index).x, verts.get(index).y);
            }
        }
        g.drawOval((dimension / 2) - maxRadius, (dimension / 2) - maxRadius, 2 * maxRadius, 2 * maxRadius);
    }

    public void update(boolean init) {
        if (init) {
            int index = (int) (Math.random() * nVerts);
            verts.get(index).infected = true;
            infectedVerts.add(verts.get(index));
        } else {
            for (Vertex infVert : infectedVerts) {
                if (Math.random() > 0.75) {
                    Vertex exposed = verts
                            .get(infVert.connections.get((int) Math.random() * infVert.connections.size()));
                    if (!infectedVerts.contains(exposed)) {
                        exposed.infected = true;
                        infectedVerts.add(exposed);
                    }
                }
            }
        }
    }
}
