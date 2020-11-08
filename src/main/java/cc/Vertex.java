package cc;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.lang.Math;
import java.util.ArrayList;

public class Vertex implements Comparable<Vertex> {

    public static final int SIZE = 6;
    public static final int SIZE_RADIUS = SIZE / 2;

    public double radius;
    public double angle;
    public int x;
    public int y;
    public ArrayList<Integer> connections;
    public boolean infected;
    public boolean active;
    public int exposed;

    Vertex(double maxRadius) {
        PolarPosition pp = new PolarPosition(maxRadius);
        this.radius = pp.getRadius();
        this.angle = pp.getAngle();
        this.connections = new ArrayList<>();
        this.active = false;
    }

    Vertex(double radius, double angle) {
        this.radius = radius;
        this.angle = angle;
        this.connections = new ArrayList<>();
        this.active = false;
    }

    public int compareTo(Vertex v) {
        return -1 * this.connections.size() - v.connections.size();
    }

    public void calcPos(double scale){
        x = 400 + (int) (scale * this.radius * Math.cos(angle));
        y = 400 + (int) (scale * this.radius * Math.sin(angle));
    }

    public void draw(Graphics g, boolean tE) {
        double scale = 20;
        draw(g, scale, tE);
    }

    public void draw(Graphics g, double scale, boolean tE) {
        Color prevColor = g.getColor();
        if (infected) {
            g.setColor(new Color(255, 0, 0));
        }
        g.fillOval(x - SIZE_RADIUS, y - SIZE_RADIUS, SIZE, SIZE);
        if (tE) {
            g.setColor(new Color(125, 125, 125));
            Font prevFont = g.getFont();
            g.drawString(String.valueOf(exposed), x - SIZE_RADIUS, y - SIZE_RADIUS);
            // g.setFont(prevFont.deriveFont(prevFont.getSize2D() - 1));
            // g.setColor(new Color(255, 255, 255));
            // g.drawString(String.valueOf(exposed), x - SIZE_RADIUS, y - SIZE_RADIUS);
            g.setFont(prevFont);
        }
        g.setColor(prevColor);
    }

}