package bomberman;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Menu extends JFrame {

    private JButton buttonStart, button2, button3, button4, ai1, ai2, ai3, ai4;

    int players = 2;
    boolean p1Ai = false;
    boolean p2Ai = false;
    boolean p3Ai = false;
    boolean p4Ai = false;

    private static ImageIcon createToolIcon(String path) {
        URL url = System.class.getResource(path);
        if (url == null) {
            System.err.println("Unable to load Image: " + path);
        }
        ImageIcon icon = new ImageIcon(url);
        Image img = icon.getImage();
        ImageIcon newIcon = new ImageIcon(img);
        return newIcon;
    }

    Menu() {
        setLayout(null);

        setSize(400, 400);
        setLocation(600, 300);
        setTitle("Bomberman");
        setResizable(false);

        buttonStart = new JButton("Start");
        button2 = new JButton("2 Players");
        button3 = new JButton("3 Players");
        button4 = new JButton("4 Players");

        ai1 = new JButton();
        ai2 = new JButton();
        ai3 = new JButton();
        ai4 = new JButton();

        buttonStart.setBounds(100, 300, 190, 28);
        buttonStart.setIcon(createToolIcon("/images/play.png"));

        button2.setBounds(100, 170, 190, 28);
        button2.setIcon(createToolIcon("/images/menu2player.png"));
        button3.setBounds(100, 200, 190, 28);
        button3.setIcon(createToolIcon("/images/menu3player.png"));
        button4.setBounds(100, 230, 190, 28);
        button4.setIcon(createToolIcon("/images/menu4player.png"));

        ai1.setBounds(100, 260, 38, 28);
        ai1.setIcon(createToolIcon("/images/1Ai.png"));
        add(ai1);
        ai2.setBounds(150, 260, 38, 28);
        ai2.setIcon(createToolIcon("/images/2Ai.png"));
        add(ai2);
        ai3.setBounds(200, 260, 38, 28);
        ai3.setIcon(createToolIcon("/images/3Ai.png"));
        add(ai3);
        ai4.setBounds(250, 260, 38, 28);
        ai4.setIcon(createToolIcon("/images/4Ai.png"));

        JLabel menue = new JLabel();

        add(ai4);
        add(buttonStart);

        add(button2);
        add(button3);
        add(button4);

        ai1.setVisible(false);
        ai2.setVisible(false);
        ai3.setVisible(false);
        ai4.setVisible(false);

        menue.setIcon(createToolIcon("/images/menue.png"));
        menue.setBounds(0, 0, 400, 400);
        add(menue);

        button2.addActionListener(e ->
        {
            //button2.setVisible(false);
            button2.setIcon(createToolIcon("/images/menu2chosen.png"));
            button3.setIcon(createToolIcon("/images/menu3player.png"));
            button4.setIcon(createToolIcon("/images/menu4player.png"));

            ai1.setVisible(true);
            ai2.setVisible(true);
            ai3.setVisible(false);
            ai4.setVisible(false);

            players = 2;

        });

        button3.addActionListener(e ->
        {
            //button2.setVisible(false);
            button2.setIcon(createToolIcon("/images/menu2player.png"));
            button3.setIcon(createToolIcon("/images/menu3chosen.png"));

            button4.setIcon(createToolIcon("/images/menu4player.png"));
            players = 3;
            ai1.setVisible(true);
            ai2.setVisible(true);
            ai3.setVisible(true);
            ai4.setVisible(false);

        });

        button4.addActionListener(e ->
        {
            //button2.setVisible(false);
            button2.setIcon(createToolIcon("/images/menu2player.png"));
            button3.setIcon(createToolIcon("/images/menu3player.png"));
            button4.setIcon(createToolIcon("/images/menu4chosen.png"));
            players = 4;
            ai1.setVisible(true);
            ai2.setVisible(true);
            ai3.setVisible(true);
            ai4.setVisible(true);

        });

        ai1.addActionListener(e ->
        {
            if (!p1Ai) {
                ai1.setIcon(createToolIcon("/images/1Aic.png"));
            } else {
                ai1.setIcon(createToolIcon("/images/1Ai.png"));
            }
            p1Ai = !p1Ai;

        });

        ai2.addActionListener(e ->
        {
            if (!p2Ai) {
                ai2.setIcon(createToolIcon("/images/2Aic.png"));
            } else {
                ai2.setIcon(createToolIcon("/images/2Ai.png"));
            }
            p2Ai = !p2Ai;

        });

        ai3.addActionListener(e ->
        {
            if (!p3Ai) {
                ai3.setIcon(createToolIcon("/images/3Aic.png"));
            } else {
                ai3.setIcon(createToolIcon("/images/3Ai.png"));
            }
            p3Ai = !p3Ai;

        });

        ai4.addActionListener(e ->
        {
            if (!p4Ai) {
                ai4.setIcon(createToolIcon("/images/4Aic.png"));
            } else {
                ai4.setIcon(createToolIcon("/images/4Ai.png"));
            }
            p4Ai = !p4Ai;

        });

        buttonStart.addActionListener(e ->
        {
            Game game = new Game(players, p1Ai, p2Ai, p3Ai, p4Ai);
        });
    }
}
