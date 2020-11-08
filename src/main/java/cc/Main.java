package cc;

import java.lang.Thread;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        GraphPanel gPanel = new GraphPanel(100, 5);
        UIPanel uiPanel = new UIPanel();
        AppWindow win = new AppWindow();
        win.addGraphPanel(gPanel);
        win.addUIPanel(uiPanel);
        uiPanel.scale = (int) gPanel.scale;
        while (true) {
            while (!uiPanel.active) {
                if(uiPanel.restart){
                    gPanel.reset();
                    uiPanel.restart = false;
                    win.repaint();
                }
                Thread.sleep(1);
                if(gPanel.scale != uiPanel.scale){
                    gPanel.scale = uiPanel.scale;
                    win.repaint();
                }
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
