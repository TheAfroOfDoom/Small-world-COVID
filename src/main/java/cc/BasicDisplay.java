package cc;

import java.awt.*;
import javax.swing.JFrame;
import org.jgrapht.graph.DefaultEdge;
import java.util.Set;
import java.lang.Math;

public class BasicDisplay extends Canvas {

    public void paint(Graphics g) {
        int size = 10;
        Set<DefaultEdge> edges = GraphGenerator.generate(size);
        Vertex[] verts = new Vertex[size];
        for (int i = 0; i < size; i++) {
            verts[i] = new Vertex((int) (100 + Math.random() * 600), (int) (100 + Math.random() * 600));
            verts[i].draw(g);
        }
        for (DefaultEdge edge : edges) {
            int[] vNums = vNums(edge);
            g.drawLine(verts[vNums[0]].x, verts[vNums[0]].y, verts[vNums[1]].x, verts[vNums[1]].y);
        }
    }

    public static int[] vNums(DefaultEdge edge){
        int[] vNums = new int[2];
        String edgeString = edge.toString();
        edgeString = edgeString.replace("(", "");
        edgeString = edgeString.replace(")", "");
        edgeString = edgeString.replace("v", "");
        String[] vStrings = edgeString.split(" : ");
        vNums[0] = Integer.valueOf(vStrings[0]);
        vNums[1] = Integer.valueOf(vStrings[1]);
        return vNums;
    }

    public static void main(String[] args) {
        BasicDisplay m = new BasicDisplay();
        JFrame f = new JFrame();
        f.add(m);
        f.setSize(800, 800);
        // f.setLayout(null);
        f.setVisible(true);
    }

}
