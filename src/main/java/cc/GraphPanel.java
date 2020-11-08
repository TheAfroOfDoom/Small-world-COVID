package cc;

import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class GraphPanel extends JPanel {

    Vertex[] verts;
    int[][] edgeArray;
    int maxRadius;
    int dimension;

    public GraphPanel(int nVerts) {
        super();
        dimension = 800;
        setPreferredSize(new Dimension(dimension, dimension));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 10));
        int size = 10;
        maxRadius = 300;
        edgeArray = GraphGenerator.generate(size);
        verts = new Vertex[size];
        for (int i = 0; i < verts.length; i++) {
            verts[i] = new Vertex(maxRadius);
        }
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
}
