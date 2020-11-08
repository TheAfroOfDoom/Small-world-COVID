package cc;

import java.lang.Thread;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        GraphPanel gPanel = new GraphPanel(100);
        UIPanel uiPanel = new UIPanel();
        AppWindow win = new AppWindow();
        win.addGraphPanel(gPanel);
        win.addUIPanel(uiPanel);
        while(!uiPanel.start){
            Thread.sleep(10);
        }
        while(uiPanel.start){
            Thread.sleep(250);
            gPanel.update(false);
            win.repaint();
        }
    }
}
