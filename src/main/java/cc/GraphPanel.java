package cc;

import java.awt.*;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.lang.Math;
import java.util.ArrayList;

public class GraphPanel extends JPanel {

    ArrayList<Vertex> infectedVerts;
    ArrayList<Vertex> verts;
    int frameNumber;
    int maxRadius;
    int dimension;
    int nVerts;
    int scale;

    public GraphPanel(int nVerts) {
        this(nVerts, 30);
    }

    public GraphPanel(int nVerts, int scale) {
        super();
        frameNumber = 0;
        dimension = 800;
        setPreferredSize(new Dimension(dimension, dimension));
        setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 30));
        this.scale = scale;

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
            for (int index : vert.connections) {
                Color prev = g.getColor();
                g.setColor(new Color(0, 0, 0, 50));
                g.drawLine(vert.x, vert.y, verts.get(index).x, verts.get(index).y);
                g.setColor(prev);
            }
        }
        for (Vertex vert : this.verts) {
            vert.draw(g, scale);
        }
        g.drawOval((dimension / 2) - scale * maxRadius, (dimension / 2) - scale * maxRadius, scale * 2 * maxRadius, scale * 2 * maxRadius);
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
