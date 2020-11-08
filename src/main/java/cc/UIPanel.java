package cc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class UIPanel extends JPanel implements ActionListener {

    public int height;
    public int width;
    public boolean active;
    public boolean restart;
    public int scale;

    public UIPanel() {
        super();
        active = false;
        restart = false;
        height = 800;
        width = 200;
        setPreferredSize(new Dimension(width, height));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 10));
        JButton btnStart = new JButton("Start");
        btnStart.setActionCommand("start");
        btnStart.addActionListener(this);
        add(btnStart, BorderLayout.NORTH);
        JButton btnRestart = new JButton("Restart");
        btnRestart.setActionCommand("restart");
        btnRestart.addActionListener(this);
        add(btnRestart, BorderLayout.NORTH);
        JSlider sliderScale = new JSlider(JSlider.HORIZONTAL, 0, 50, 25);
        sliderScale.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                scale = ((JSlider) e.getSource()).getValue();
                System.out.println(scale);
            }
        });
        add(sliderScale, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("start")) {
            active = true;
        } else if (cmd.equals("restart")) {
            active = false;
            restart = true;
        }
    }
}
