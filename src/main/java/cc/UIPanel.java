package cc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class UIPanel extends JPanel implements ActionListener, ItemListener {

    public int height;
    public int width;
    private JButton btnStart;
    public boolean active;
    public boolean restart;
    public boolean genNew;
    public volatile int scale;
    public volatile int speed;
    private JCheckBox checkExpo;
    public boolean toggleExpo;
    private JCheckBox checkMask;
    public boolean toggleMask;

    public UIPanel() {
        super();
        active = false;
        restart = false;
        height = 800;
        width = 200;
        setPreferredSize(new Dimension(width, height));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 10));
        btnStart = new JButton("Start");
        btnStart.setActionCommand("start");
        btnStart.addActionListener(this);
        add(btnStart, BorderLayout.NORTH);
        JButton btnRestart = new JButton("Restart");
        btnRestart.setActionCommand("restart");
        btnRestart.addActionListener(this);
        add(btnRestart, BorderLayout.NORTH);
        JButton btnNewGraph = new JButton("Gen New Graph");
        btnNewGraph.setActionCommand("genNew");
        btnNewGraph.addActionListener(this);
        add(btnNewGraph, BorderLayout.NORTH);
        JSlider sliderScale = new JSlider(JSlider.HORIZONTAL, 10, 110, 50);
        JLabel lblScale = new JLabel("Scale");
        lblScale.setLabelFor(sliderScale);
        sliderScale.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                scale = ((JSlider) e.getSource()).getValue();
                //System.out.println(scale);
            }
        });
        add(lblScale);
        add(sliderScale, BorderLayout.SOUTH);
        JSlider sliderSpeed = new JSlider(JSlider.HORIZONTAL, 250, 1000, 500);
        JLabel lblSpeed = new JLabel("Sim Speed");
        lblScale.setLabelFor(sliderSpeed);
        sliderSpeed.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                speed = ((JSlider) e.getSource()).getValue();
                //System.out.println(scale);
            }
        });
        add(lblSpeed);
        add(sliderSpeed, BorderLayout.SOUTH);
        checkExpo = new JCheckBox("Toggle Exposure Count");
        checkExpo.addItemListener(this);
        add(checkExpo, BorderLayout.SOUTH);
        checkMask = new JCheckBox("Toggle Mask Wearing");
        checkMask.addItemListener(this);
        add(checkMask, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("start")) {
            active = (active) ? false : true;
            if (active) {
                btnStart.setText("Stop");
            } else {
                btnStart.setText("Start");
            }
        } else if (cmd.equals("restart")) {
            active = false;
            restart = true;
            btnStart.setText("Start");
        } else if (cmd.equals("genNew")){
            active = false;
            genNew = true;
            btnStart.setText("Start");
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        if (source == checkExpo) {
            toggleExpo = (e.getStateChange() == 1) ? true : false;
        } else if (source == checkMask) {
            toggleMask = (e.getStateChange() == 1) ? true : false;
        }
    }
}
