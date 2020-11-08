package cc;

import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.lang.Math;
import java.util.ArrayList;

public class GraphPanel extends JPanel {

    Vertex[] verts;
    ArrayList<Vertex> infectedVerts;
    int[][] edgeArray;
    int maxRadius;
    int dimension;
    int size;

    public GraphPanel(int nVerts) {
        super();
        dimension = 800;
        setPreferredSize(new Dimension(dimension, dimension));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 10));
        size = 10;
        maxRadius = 300;
        edgeArray = GraphGenerator.generate(size);
        verts = new Vertex[size];
        for (int i = 0; i < verts.length; i++) {
            verts[i] = new Vertex(maxRadius);
        }
        for (int[] edge : edgeArray) {
            verts[edge[0]].connections.add(edge[1]);
        }
        infectedVerts = new ArrayList<>();
        update(true);
    }

    public void paint(Graphics g) {
        for (Vertex vert : verts) {
            vert.draw(g);
        }
        for (int[] edge : edgeArray) {
            g.drawLine(verts[edge[0]].x, verts[edge[0]].y, verts[edge[1]].x, verts[edge[1]].y);
        }
        g.drawOval((dimension / 2) - maxRadius, (dimension / 2) - maxRadius, 2 * maxRadius, 2 * maxRadius);
    }

    public void update(boolean init) {
        if (init) {
            int index = (int) (Math.random() * size);
            verts[index].infected = true;
            infectedVerts.add(verts[index]);
        } else {
            for (Vertex infVert : infectedVerts) {
                if (Math.random() > 0.75) {
                    Vertex exposed = verts[infVert.connections.get((int) Math.random() * infVert.connections.size())];
                    if (!infectedVerts.contains(exposed)) {
                        exposed.infected = true;
                        infectedVerts.add(exposed);
                    }
                }
            }
        }
    }
}
