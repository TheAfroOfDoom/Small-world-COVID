package cc;

import java.lang.Thread;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        GraphPanel gPanel = new GraphPanel(10);
        AppWindow win = new AppWindow();
        win.add(gPanel);
    }
}
