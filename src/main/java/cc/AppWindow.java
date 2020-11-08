
package cc;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Component;

public class AppWindow {

    public JFrame frame;

    public AppWindow() {
        frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void add(Component comp){
        frame.add(comp, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    public void repaint() {
        frame.repaint();
    }
}