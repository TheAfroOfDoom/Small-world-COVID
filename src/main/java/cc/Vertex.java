package cc;

import java.awt.Graphics;

public class Vertex {

    public int x;
    public int y;

    Vertex(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.fillOval(x-15,y-15,30,30);
    }

}