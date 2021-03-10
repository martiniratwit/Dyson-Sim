import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//test
public class MyPanel extends JPanel {

    final int PANEL_WIDTH = 600;
    final int PANEL_HEIGHT = 500;

    Image backroundImage;

    MyPanel() {

        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        backroundImage = new ImageIcon("Backround.jpg").getImage();


    }

    public void paint(Graphics g) {
        Graphics g2D =  (Graphics2D) g;

        g2D.drawImage(backroundImage,0,0,null);
    }
}
