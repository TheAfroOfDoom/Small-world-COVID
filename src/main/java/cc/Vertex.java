package cc;

import java.awt.Graphics;
import java.lang.Math;

public class Vertex {

    public static final int SIZE = 6;
    public static final int SIZE_RADIUS = SIZE / 2;

    public double radius;
    public double angle;
    public int x;
    public int y;

    Vertex(double maxRadius){
        PolarPosition pp = new PolarPosition(maxRadius);
        this.radius = pp.getRadius();
        this.angle = pp.getAngle();
    }

    Vertex(int radius, int angle){
        this.radius = radius;
        this.angle = angle;
    }

    public void draw(Graphics g) {
        x = (int) (400 + (this.radius * Math.cos(angle)));
        y = (int) (400 + (this.radius * Math.sin(angle)));

        g.fillOval(x - SIZE_RADIUS, y - SIZE_RADIUS, SIZE, SIZE);
    }

}