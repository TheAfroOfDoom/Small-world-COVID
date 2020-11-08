package cc;

import java.awt.*;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;

public class GraphPanel extends JPanel {

    ArrayList<Vertex> infectedVerts;
    ArrayList<Vertex> verts;
    int frameNumber;
    int maxRadius;
    int dimension;
    int nVerts;
    HyperbolicRandomGraphGenerator hrgg;

    public GraphPanel(int nVerts) {
        super();
        frameNumber = 0;
        dimension = 800;
        setPreferredSize(new Dimension(dimension, dimension));
        setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 30));

        double averageDegree = 5;
        double curvature = -1;
        hrgg = new HyperbolicRandomGraphGenerator(nVerts, averageDegree, curvature);

        maxRadius = (int) hrgg.getMaxRadius();
        this.nVerts = nVerts;
        verts = new ArrayList<Vertex>(Arrays.asList(hrgg.getCopy()));
        infectedVerts = new ArrayList<>();
        update(true);
    }

    public void reset() {
        verts = new ArrayList<Vertex>(Arrays.asList(hrgg.getCopy()));
        infectedVerts = new ArrayList<>();
        frameNumber = 0;
        update(true);
    }

    public void paint(Graphics g) {
        for (Vertex vert : this.verts) {
            for (int index : vert.connections) {
                Color prev = g.getColor();
                g.setColor(new Color(0, 0, 0, 50));
                g.drawLine(vert.x, vert.y, verts.get(index).x, verts.get(index).y);
                g.setColor(prev);
            }
        }
        for (Vertex vert : this.verts) {
            vert.draw(g);
        }
        g.drawOval((dimension / 2) - maxRadius, (dimension / 2) - maxRadius, 2 * maxRadius, 2 * maxRadius);
        g.drawString("frame:", 50, 740);
        g.drawString(String.valueOf(frameNumber), 50, 750);
        g.drawString("% inf:", 50, 720);
        g.drawString(String.valueOf(100 * infectedVerts.size() / verts.size()), 50, 730);
    }

    public void update(boolean init) {
        if (init) {
            int index = (int) (Math.random() * nVerts);
            verts.get(index).infected = true;
            infectedVerts.add(verts.get(index));
        } else {
            frameNumber++;
            ArrayList<Integer> exposed = new ArrayList<>();
            for (Vertex infVert : infectedVerts) {
                int randomConnect = infVert.connections.get((int) (Math.random() * infVert.connections.size()));
                exposed.add(randomConnect);
            }
            for (Integer vertIdx : exposed) {
                Vertex vertExposed = verts.get(vertIdx);
                vertExposed.exposed++;
                if (Math.random() > 0.75) {
                    if (!infectedVerts.contains(vertExposed)) {
                        vertExposed.infected = true;
                        infectedVerts.add(vertExposed);
                    }
                }
            }
        }
    }
}
