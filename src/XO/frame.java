package XO;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class frame extends JFrame implements WindowListener,ActionListener {
    private List<JButton> buttonsList= new ArrayList<JButton>();
    private int numClicks=0;
    private JTextField text = new JTextField(20);
    public Container confContainer(){
        Container cont = new Container();
        cont.setLayout(new GridLayout(3,3));
        cont.setPreferredSize(new Dimension(200,200));

        //Elements:
            for(int i=0;i<9;++i){
                buttonsList.add(new JButton(""));
                buttonsList.get(i).setBackground((i%2==0)?new Color(255,255,255):new Color(0,0,0));
                buttonsList.get(i).setPreferredSize(new Dimension(10,10));
                buttonsList.get(i).addActionListener(this);
                cont.add(buttonsList.get(i));
            }

        return cont;
    }
    public frame(){
        super("Jakieś okienko");
        setLayout(new FlowLayout(1));
        Container cont = confContainer();
//        BufferedImage image = null;
//        image = ImageIO.read("path");
        add(text);
        text.setEditable(false);
        text.setVisible(true);
        add(cont);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,300);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        numClicks++;
//        Label texxt = new Label("Button "\+numClicks+"x")
        text.setText("Przycik został kliknię "+numClicks+" razy.");
    }

    @Override
    public void windowOpened(WindowEvent e) {
        dispose();
        System.exit(0);
    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}

