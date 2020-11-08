package cc;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.lang.Math;
import java.util.ArrayList;

public class Vertex {

    public static final int SIZE = 6;
    public static final int SIZE_RADIUS = SIZE / 2;

    public double radius;
    public double angle;
    public int x;
    public int y;
    public ArrayList<Integer> connections;
    public boolean infected;
    public int exposed;

    Vertex(double maxRadius) {
        PolarPosition pp = new PolarPosition(maxRadius);
        this.radius = pp.getRadius();
        this.angle = pp.getAngle();
        this.connections = new ArrayList<>();
    }

    Vertex(double radius, double angle) {
        this.radius = radius;
        this.angle = angle;
        this.connections = new ArrayList<>();
    }

    public void draw(Graphics g) {
        int scale = 20;
        draw(g, scale);
    }

    public void draw(Graphics g, int scale) {
        Color prevColor = g.getColor();
        x = 400 + scale * (int) (this.radius * Math.cos(angle));
        y = 400 + scale * (int) (this.radius * Math.sin(angle));
        if (infected) {
            g.setColor(new Color(255, 0, 0));
        }
        g.fillOval(x - SIZE_RADIUS, y - SIZE_RADIUS, SIZE, SIZE);
        g.setColor(new Color(125, 125, 125));
        Font prevFont = g.getFont();
        g.drawString(String.valueOf(exposed), x - SIZE_RADIUS, y - SIZE_RADIUS);
        //g.setFont(prevFont.deriveFont(prevFont.getSize2D() - 1));
        //g.setColor(new Color(255, 255, 255));
        //g.drawString(String.valueOf(exposed), x - SIZE_RADIUS, y - SIZE_RADIUS);
        g.setFont(prevFont);
        g.setColor(prevColor);
    }

}