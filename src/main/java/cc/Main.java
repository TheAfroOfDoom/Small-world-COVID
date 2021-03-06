package cc;

import java.lang.Thread;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        GraphPanel gPanel = new GraphPanel(200, 5);
        UIPanel uiPanel = new UIPanel();
        AppWindow win = new AppWindow();
        win.addGraphPanel(gPanel);
        win.addUIPanel(uiPanel);
        uiPanel.scale = (int) gPanel.scale;
        while (true) {
            while (!uiPanel.active) {
                if (uiPanel.restart) {
                    gPanel.reset();
                    uiPanel.restart = false;
                }
                if (uiPanel.genNew) {
                    gPanel.genNew(200, 5);
                    uiPanel.genNew = false;
                }
                if (gPanel.scale != uiPanel.scale) {
                    gPanel.scale = uiPanel.scale;
                }
                if (gPanel.toggleExposure != uiPanel.toggleExpo) {
                    gPanel.toggleExposure = uiPanel.toggleExpo;
                }
                win.repaint();
                Thread.sleep(1);
            }
            while (uiPanel.active) {
                Thread.sleep(1050 - uiPanel.speed);
                gPanel.scale = uiPanel.scale;
                gPanel.toggleExposure = uiPanel.toggleExpo;
                gPanel.toggleMask = uiPanel.toggleMask;
                gPanel.update(false);
                win.repaint();
            }
        }
    }
}
