package XO;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class frame{
    //Constructors:
    public frame(){
        initialGui();
        initialListeners();
    }

    //Main field class:
    public class field extends JButton {
        private char x;
        public field(){
            super();
        }
        public field(String text){
            super(text);
            addActionListener(cl);
            setPreferredSize(new Dimension(10,10));
        }
        public void used(char x){
            this.x = x;
            setEnabled(false);
//            setText(Character.toString(x));
            setFont(new Font("Calibri", Font.PLAIN, 64));
        }
        private void newGame(){
            x = 'E';
            setEnabled(true);
            setIcon(null);
        }

        public char getXChar() {
            return x;
        }
    }

    //Gui-Creating:
    private JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JLabel message = new JLabel("Koji the game!");
    private List<field> buttonsList= new ArrayList<field>();

    //Action Buttons:
    private JButton newGame = new JButton("New Game");
    private JButton resign = new JButton("Resign");

    //Action Listeners:
    private Resign resignL = new Resign();
    private NewGame newGameL = new NewGame();
    private ClickListener cl = new ClickListener();

    //Images:
    private ImageIcon xImage;
    private ImageIcon oImage;

    //Play State
    private boolean player = false;
    private boolean isEnded = false;
    private int clicked = 0;
    WinChecker wc = new WinChecker();

    //guiGetter:
    public final JComponent getGui() {
        return gui;
    }

    //resetClicks counter
    public void resetClicked(){
        clicked = 0;
    }

    //Initializations:
    private final void initialListeners(){
        newGame.addActionListener(newGameL);
        resign.addActionListener(resignL);
    }
    private final void initialGui(){
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);
        tools.add(newGame);

        tools.addSeparator();
        tools.addSeparator();
        tools.addSeparator();
        tools.add(resign);
        tools.addSeparator();
        tools.add(message);
        gui.add(confContainer());
        importImages();
    }

    //ImportImages function:
    private void importImages(){
        BufferedImage bi;
        try {
            bi = ImageIO.read(new File("/home/kamil/IdeaProjekts/XO-The-Games/src/XO/img/x.png"));
            xImage = new ImageIcon(bi);
        }
        catch (IOException e) { }

        try {
            bi = ImageIO.read(new File("/home/kamil/IdeaProjekts/XO-The-Games/src/XO/img/o.png"));
            oImage = new ImageIcon(bi);
        }
        catch (IOException e) { }
    }



    //Play fieldInitialization:
    public Container confContainer(){
        Container cont = new Container();
        cont.setLayout(new GridLayout(3,3));
        cont.setPreferredSize(new Dimension(600,600));
        //dodawanie przycisków!
        for(int i=0;i<9;++i){
            buttonsList.add(new field(""));
            buttonsList.get(i).setBackground((i%2==0)?new Color(255,255,255):new Color(0,0,0));
            cont.add(buttonsList.get(i));
        }
        return cont;
    }

    //Win Checking Class:
    class WinChecker{
        private int xCount=0,yCount=0;
        public void checkRows(){
            for(int x=0;x<3;++x){
                xCount = 0;
                yCount = 0;
                for(int y=x*3,yC = y+3;y<yC;++y) {
                    if (buttonsList.get(y).getXChar() == 'X')
                        ++xCount;
                    else if (buttonsList.get(y).getXChar() == 'Y')
                        ++yCount;
                }
                if(xCount==3)new JOptionPane().showMessageDialog(null, "Koniec gry!\n Wygrał X", "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
                else if(yCount == 3)new JOptionPane().showMessageDialog(null, "Koniec gry!\n Wygrał Y", "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }





    //Action Clickers classes:
    class ClickListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            field f = (field)e.getSource();
            if(player == false){
                message.setText("Tura O");
                f.used('X');
                f.setIcon(xImage);
            }
            else{
                message.setText("Tura X");
                f.used('O');
                f.setIcon(oImage);
            }

            player = !player;

            if(++clicked==9){
                JOptionPane message = new JOptionPane();
                message.showMessageDialog(null, "Koniec gry!\n Mamy remis!", "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
            }
            wc.checkRows();
        }
    }
    class Resign implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
        if(player==true){
            JOptionPane message = new JOptionPane();
            message.showMessageDialog(null, "Wygrał X,\n przez poddanie ze strony O!","Wygrana:", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane message = new JOptionPane();
            message.showMessageDialog(null, "Wygrał O,\n przez poddanie ze strony X!", "Wygrana:", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    }

    class NewGame implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            for(int i=0;i<9;++i){
                buttonsList.get(i).newGame();

            }
            resetClicked();
        }
    }
}

