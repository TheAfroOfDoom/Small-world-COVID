package cc;

import java.lang.Thread;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        GraphPanel gPanel = new GraphPanel(100);
        AppWindow win = new AppWindow();
        win.add(gPanel);
        while(true){
            Thread.sleep(250);
            gPanel.update(false);
            win.repaint();
        }
    }
}
