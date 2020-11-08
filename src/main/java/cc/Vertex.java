package cc;

import java.awt.Graphics;
import java.lang.Math;

public class Vertex {

    public static final int SIZE = 30;
    public static final int SIZE_RADIUS = SIZE / 2;

    public int radius;
    public double angle;
    public int x;
    public int y;

    Vertex(int maxRadius){
        PolarPosition pp = new PolarPosition(maxRadius);
        this.radius = (int) pp.getRadius();
        this.angle = pp.getAngle();
    }

    Vertex(int radius, int angle){
        this.radius = radius;
        this.angle = angle;
    }

    public void draw(Graphics g) {
        x = 400 + (int) ((double) this.radius * Math.cos(angle));
        y = 400 + (int) ((double) this.radius * Math.sin(angle));

        g.fillOval(x - SIZE_RADIUS, y - SIZE_RADIUS, SIZE, SIZE);
    }

}