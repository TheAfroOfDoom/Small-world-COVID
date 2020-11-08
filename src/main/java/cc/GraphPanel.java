package cc;

import java.awt.*;

import java.util.Arrays;

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

        double averageDegree = 5; double curvature = -1;
        HyperbolicRandomGraphGenerator hrgg =
            new HyperbolicRandomGraphGenerator(nVerts, averageDegree, curvature);

        this.verts = Arrays.copyOf(hrgg.vertices, hrgg.vertices.length, Vertex[].class);
        this.maxRadius = (int) hrgg.getMaxRadius();
        
        edgeArray = hrgg.GenerateEdges();
    }

    public void paint(Graphics g) {
        for (Vertex vert : this.verts) {
            vert.draw(g);
        }
        for (int[] edge : this.edgeArray) {
            g.drawLine(verts[edge[0]].x, verts[edge[0]].y, verts[edge[1]].x, verts[edge[1]].y);
        }
        g.drawOval((dimension / 2) - maxRadius, (dimension / 2) - maxRadius, 2 * maxRadius, 2 * maxRadius);
    }
}
