package XO;


import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                frame XO = new frame();
                JFrame f = new JFrame("XO-The-Game");
                f.add(XO.getGui());
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);
                f.pack();
                f.resize(650,650);
                f.setMinimumSize(f.getSize());
                f.setMaximumSize(f.getSize());
                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
    }
}
