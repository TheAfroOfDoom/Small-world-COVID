package cc;

import java.awt.*;
import java.lang.Math;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class GraphPanel extends JPanel {

    Vertex[] verts;
    int[][] edgeArray;

    public GraphPanel(int nVerts) {
        super();
        setPreferredSize(new Dimension(800, 800));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 10));
        int size = 10;
        edgeArray = GraphGenerator.generate(size);
        verts = new Vertex[size];
        for (int i = 0; i < verts.length; i++) {
            verts[i] = new Vertex((int) (100 + Math.random() * 600), (int) (100 + Math.random() * 600));
        }
    }

    public void paint(Graphics g) {
        for (Vertex vert : verts) {
            vert.draw(g);
        }
        for (int[] edge : edgeArray) {
            g.drawLine(verts[edge[0]].x, verts[edge[0]].y, verts[edge[1]].x, verts[edge[1]].y);
        }
    }
}
