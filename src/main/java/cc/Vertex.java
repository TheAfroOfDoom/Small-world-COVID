package cc;

import java.awt.Graphics;
import java.lang.Math;

public class Vertex {

    public static final int SIZE = 30;
    public static final int SIZE_RADIUS = SIZE / 2;

    public int radius;
    public int angle;

    Vertex(int radius, int angle){
        this.radius = radius;
        this.angle = angle;
    }

    public void draw(Graphics g) {
        int x = (int) ((double) this.radius * Math.cos(angle));
        int y = (int) ((double) this.radius * Math.sin(angle));

        g.fillOval(x - SIZE_RADIUS, y - SIZE_RADIUS, SIZE, SIZE);
    }

}