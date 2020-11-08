package cc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class UIPanel extends JPanel implements ActionListener {

    public int height;
    public int width;
    public boolean start;

    public UIPanel() {
        super();
        start = false;
        height = 800;
        width = 200;
        setPreferredSize(new Dimension(width, height));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 10));
        JButton btnStart = new JButton("Start");
        btnStart.addActionListener(this);
        add(btnStart, BorderLayout.NORTH);
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("clicked");
        start = true;
    }
}
