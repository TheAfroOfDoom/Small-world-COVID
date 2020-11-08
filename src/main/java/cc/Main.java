package cc;

import java.lang.Thread;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        GraphPanel gPanel = new GraphPanel(100);
        UIPanel uiPanel = new UIPanel();
        AppWindow win = new AppWindow();
        win.addGraphPanel(gPanel);
        win.addUIPanel(uiPanel);
        while (true) {
            while (!uiPanel.active) {
                if(uiPanel.restart){
                    gPanel.reset();
                    uiPanel.restart = false;
                    win.repaint();
                }
                Thread.sleep(1);
                gPanel.scale = uiPanel.scale;
            }
            while (uiPanel.active) {
                Thread.sleep(250);
                gPanel.scale = uiPanel.scale;
                gPanel.update(false);
                win.repaint();
            }
        }
    }
}
