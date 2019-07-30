package bomberman;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.net.URL;
import java.util.LinkedList;
import java.util.Random;

public class Game extends JFrame implements ActionListener { //Graphic User Interface

    private MyFrame frame;
    private JLabel imgBackground_Board, imgMenu;
    private JPanel northPanel, southPanel, westPanel, eastPanel, menuPanel;
    private JButton bBack, bPlay, bExit, bAuthors, bOption;
    private JLabel[] imgSolid = new JLabel[82];
    private JLabel[] imgCrate = new JLabel[71];
    private JLabel imgCount;
    private JLabel[] imgBomb = new JLabel[20];
    private JLabel[] imgBoomC4 = new JLabel[113];
    private JLabel[] imgPowerup = new JLabel[24];
    private JLabel[] imgHearth = new JLabel[12];
    private JLabel[] imgKey = new JLabel[12];
    private JLabel[] imgBadge = new JLabel[4];
    private JLabel[] imgPoint = new JLabel[4];
    private JLabel imgGameOver;

    private boolean stoperan = true;

    //maksymalne ilosci bufow
    private int maxBuffPower = 10;
    private int maxBuffBomb = 10;
    private int maxBuffSpeed = 4;

    //aktualna liczba wydropionych bufow
    private int buffPowers = 0;
    private int buffBombs = 0;
    private int buffSpeed = 0;

    Random rand = new Random();

    //Tablica z powerupami
    private PowerUps powerupy[] = new PowerUps[maxBuffBomb + maxBuffPower + maxBuffSpeed];
    private Bomb bombs[] = new Bomb[20];
    private Bomb blow = new Bomb();

    //Tablica wybuchow
    private int tab[][] = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0},
            {0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0},
            {0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0},
            {0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0},
            {0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0},
            {0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0},
            {0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0},
            {0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0},
            {0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0},
            {0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0}};

    private int widthGameBoard = 1050;
    private int heightGameBoard = 910;
    private int frameWidth = 1350;
    private int frameHeight = 1000;
    private int marginLeft = 255;
    private int marginRight = 45;
    private int marginUp = 45;
    private int marginDown = 45;
    private Color frameColor = new Color(50, 50, 50);

    //Deklaracja graczy i ich miejsc
    private int howMuchPlayers;

    private Player p1 = new Player(70 + marginLeft, 70 + marginUp);
    private Player p2 = new Player(910 + marginLeft, 770 + marginUp);
    private Player p3 = new Player(70 + marginLeft, 770 + marginUp);
    private Player p4 = new Player(910 + marginLeft, 70 + marginUp);

    //Tablice obiektow dla blokow
    private SolidBlock SolidBoard = new SolidBlock();
    private int[][] SolidBlocksBoard = SolidBoard.getXYBoard();
    private Crate crate = new Crate();
    private int[][] crateBlocks = crate.getXYBoard();

    private int player1_X = 70;
    private int player1_Y = 70;

    private int czas = 0;
    private int czas2 = 0;
    private int hit1 = 0;
    private int hit2 = 0;
    private int hit3 = 0;
    private int hit4 = 0;

    private boolean p1ai, p2ai, p3ai, p4ai;

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

    private void TakeCare(Player p, int x, int y) {
        for (int i = 0; i < p.map.size(); i++) {
            if (p.map.get(i).x == x && p.map.get(i).y == y) {
                p.map.get(i).dang = true;
            }
        }

        int u = 0, d = 0, l = 0, r = 0;

        u = p.blocked2(x, y - 70, 1, SolidBlocksBoard, bombs, crateBlocks);
        d = p.blocked2(x, y + 70, 2, SolidBlocksBoard, bombs, crateBlocks);
        l = p.blocked2(x - 70, y, 3, SolidBlocksBoard, bombs, crateBlocks);
        r = p.blocked2(x + 70, y, 4, SolidBlocksBoard, bombs, crateBlocks);

        for (int k = 1; k < p.getPower(); k++) {
            if (u == 0) {
                for (Maps Maps : p.map) {
                    if (Maps.x == x && Maps.y == y - k * 70) {
                        Maps.dang = true;
                    }
                }
                u = p.blocked2(x, y - (k + 1) * 70, 1, SolidBlocksBoard, bombs, crateBlocks);
            }
            if (d == 0) {
                for (Maps Maps : p.map) {
                    if (Maps.x == x && Maps.y == y + k * 70) {
                        Maps.dang = true;
                    }
                }
                d = p.blocked2(x, y + (k + 1) * 70, 2, SolidBlocksBoard, bombs, crateBlocks);
            }
            if (l == 0) {
                for (Maps Maps : p.map) {
                    if (Maps.x == x - k * 70 && Maps.y == y) {
                        Maps.dang = true;
                    }
                }
                l = p.blocked2(x - (k + 1) * 70, y, 3, SolidBlocksBoard, bombs, crateBlocks);
            }
            if (r == 0) {
                for (Maps Maps : p.map) {
                    if (Maps.x == x + k * 70 && Maps.y == y) {
                        Maps.dang = true;
                    }
                }
                r = p.blocked2(x + (k + 1) * 70, y, 4, SolidBlocksBoard, bombs, crateBlocks);
            }
        }
    }

    private void Ai(Player p) {
        p.map.clear();
        drawmap(p, p.getX(), p.getY(), 0);
        Think(p);
    }


    private void walk(Player p) {
        int x = 0, y = 0;
        if (p.iks.size() > 0) {
            if (p.getX() > p.iks.getFirst() && p.getY() == p.igrek.getFirst()) {
                p.setPleft(true);
            }
            if (p.getX() < p.iks.getFirst() && p.getY() == p.igrek.getFirst()) {
                p.setPright(true);
            }
            if (p.getX() == p.iks.getFirst() && p.getY() > p.igrek.getFirst()) {
                p.setPup(true);
            }
            if (p.getX() == p.iks.getFirst() && p.getY() < p.igrek.getFirst()) {
                p.setPdown(true);
            }

            for (int l = 0; l < 20; l++) {
                if (p.getX() + 70 == tab[l][0] && p.getY() == tab[l][1]) {
                    p.setPright(false);
                }
                if (p.getX() == tab[l][0] && p.getY() + 70 == tab[l][1]) {
                    p.setPdown(false);
                }
                if (p.getX() - 70 == tab[l][0] && p.getY() == tab[l][1]) {
                    p.setPleft(false);
                }
                if (p.getX() == tab[l][0] && p.getY() - 70 == tab[l][1]) {
                    p.setPup(false);
                }
            }

            for (int l = 0; l < 20; l++) {
                if ((p.getX() - bombs[l].getX() >= -70 && p.getX() - bombs[l].getX() < 0) && p.getY() == bombs[l].getY() && p.getPright()) {
                    p.setPright(false);

                    x = p.iks.getFirst();
                    y = p.igrek.getFirst();
                    p.iks.clear();
                    p.igrek.clear();
                    p.priorytet = true;
                    p.iks.add(x - 70);
                    p.igrek.add(y);
                }
                if (p.getX() == bombs[l].getX() && (p.getY() - bombs[l].getY() >= -70 && p.getY() - bombs[l].getY() < 0) && p.getPdown()) {
                    p.setPdown(false);

                    x = p.iks.getFirst();
                    y = p.igrek.getFirst();
                    p.iks.clear();
                    p.igrek.clear();
                    p.priorytet = true;
                    p.iks.add(x);
                    p.igrek.add(y - 70);
                }
                if ((p.getX() - bombs[l].getX() <= 70 && p.getX() - bombs[l].getX() > 0) && p.getY() == bombs[l].getY() && p.getPleft()) {
                    p.setPleft(false);

                    x = p.iks.getFirst();
                    y = p.igrek.getFirst();
                    p.iks.clear();
                    p.igrek.clear();
                    p.priorytet = true;
                    p.iks.add(x + 70);
                    p.igrek.add(y);
                }
                if (p.getX() == bombs[l].getX() && (p.getY() - bombs[l].getY() <= 70 && p.getY() - bombs[l].getY() > 0) && p.getPup()) {
                    p.setPup(false);

                    x = p.iks.getFirst();
                    y = p.igrek.getFirst();
                    p.iks.clear();
                    p.igrek.clear();
                    p.priorytet = true;
                    p.iks.add(x);
                    p.igrek.add(y + 70);
                }
            }

            if (p.iks.size() > 0) {
                if (p.getX() == p.iks.getFirst() && p.getY() == p.igrek.getFirst()) { // Zatrzymanie gracza
                    p.setPleft(false);
                    p.setPright(false);
                    p.setPup(false);
                    p.setPdown(false);

                    if (p.iks.size() == 1) {
                        x = p.iks.getFirst();
                        y = p.igrek.getFirst();
                    }
                    p.iks.removeFirst();
                    p.igrek.removeFirst();
                }
            }

            if (p.iks.size() == 0) {
                if (!p.priorytet) {
                    if (czas - p.czassi > 300) {
                        p.czassi = czas;
                        p.plantBomb(bombs, czas, imgBomb);

                        p.map.clear();

                        drawmap(p, p.getX(), p.getY(), 0);

                        TakeCare(p, p.getX(), p.getY());

                        Think(p);
                    } else {
                        p.iks.add(x);
                        p.igrek.add(y);
                    }
                } else {
                    if (czas - p.czassi > 300) {
                        p.priorytet = false;
                        p.map.clear();

                        drawmap(p, p.getX(), p.getY(), 0);

                        Think(p);
                    } else {
                        p.iks.add(x);
                        p.igrek.add(y);
                    }
                }
            } else {
                int l = 0;
                while (l < 20) {
                    if (p.iks.getLast() == bombs[l].getX() && p.igrek.getLast() == bombs[l].getY()) {
                        p.setPdown(false);
                        p.setPup(false);
                        p.setPleft(false);
                        p.setPright(false);

                        while (p.iks.size() > 1) {
                            p.iks.removeLast();
                            p.igrek.removeLast();
                        }
                        p.priorytet = true;
                        l = 30;
                    }
                    l++;
                }
            }
        }
    }


    private int command(Player p, int i)//Wpisanie do tablicy drogi i rozkaz pójścia
    {
        p.iks.addFirst(p.map.get(i).x);
        p.igrek.addFirst(p.map.get(i).y);

        if (!(p.map.get(i).x == p.getX() && p.map.get(i).y == p.getY())) {
            for (int j = 0; j < p.map.size(); j++) {
                if (p.map.get(i).km - 1 == p.map.get(j).km && !(p.map.get(i).x - p.map.get(j).x > 70 || p.map.get(i).x - p.map.get(j).x < -70 || p.map.get(i).y - p.map.get(j).y > 70 || p.map.get(i).y - p.map.get(j).y < -70)) {

                    command(p, j);
                    return 1;
                }
            }
        }
        return 0;
    }


    private void Think(Player p)//Znalezienie najbardziej wartosciowego klocka
    {
        int i = 0, value = 0, km = 300;

        for (int j = 0; j < p.map.size(); j++) {
            for (int k = 0; k < powerupy.length; k++) {
                if (p.map.get(j).x == powerupy[k].getX() && p.map.get(j).y == powerupy[k].getY()) {
                    if (p.map.get(j).km < km && !p.map.get(j).dang) {
                        i = j;
                        p.priorytet = true;
                        km = p.map.get(j).km;
                    }
                }
            }
            if (!p.priorytet) {
                if (p.map.get(j).value > value && !p.map.get(j).dang && !(p.map.get(j).x == p.getX() && p.map.get(j).y == p.getY())) {
                    i = j;
                    value = p.map.get(j).value;
                }
            }
        }


        for (int l = 0; l < p.map.size(); l++) {
            //  System.out.println("                              " + p.map.get(l).x + " " + p.map.get(l).y + " " + p.map.get(l).value + " " + p.map.get(l).km + " " + p.map.get(l).dang);


        }
        if (i != 0) {
            // System.out.println("Wybor " + p.map.get(i).x + " " + p.map.get(i).y);
            command(p, i);
        }
    }

    private void writeInList(Player p, int x, int y, int km) {
        boolean b = false;
        for (int j = 0; j < p.map.size(); j++) {
            if (x == p.map.get(j).x && y == p.map.get(j).y) {
                if (km > p.map.get(j).km) {
                    b = true;
                }
            }
        }

        if (!b) {
            drawmap(p, x, y, km);
        }

    }

    private void drawmap(Player p, int x, int y, int km) {
        int value = 0;
        int u = 0, d = 0, l = 0, r = 0;
        boolean b = true;
        for (int k = 1; k < p.getPower(); k++) {
            if (u == 0) {
                u = p.blocked2(x, y - k * 70, 1, SolidBlocksBoard, bombs, crateBlocks);
            }
            if (d == 0) {
                d = p.blocked2(x, y + k * 70, 2, SolidBlocksBoard, bombs, crateBlocks);
            }
            if (l == 0) {
                l = p.blocked2(x - k * 70, y, 3, SolidBlocksBoard, bombs, crateBlocks);
            }
            if (r == 0) {
                r = p.blocked2(x + k * 70, y, 4, SolidBlocksBoard, bombs, crateBlocks);
            }
        }
        value += u / 10;
        value += d / 10;
        value += l / 10;
        value += r / 10;
        Maps mapp = new Maps(x, y, value, km);

        for (int i = 0; i < p.map.size(); i++) {
            if (x == p.map.get(i).x && y == p.map.get(i).y) {
                p.map.get(i).km = km;
                b = false;
            }
        }
        if (b) {
            p.map.add(mapp);
        }


        if (p.blocked(x, y - 70, 1, SolidBlocksBoard, bombs, crateBlocks) == 0) {
            writeInList(p, x, y - 70, km + 1);
        }
        if (p.blocked2(x, y + 70, 2, SolidBlocksBoard, bombs, crateBlocks) == 0) {
            writeInList(p, x, y + 70, km + 1);
        }
        if (p.blocked2(x - 70, y, 3, SolidBlocksBoard, bombs, crateBlocks) == 0) {
            writeInList(p, x - 70, y, km + 1);
        }
        if (p.blocked2(x + 70, y, 4, SolidBlocksBoard, bombs, crateBlocks) == 0) {
            writeInList(p, x + 70, y, km + 1);
        }
    }

    //Metoda sprawdzajaca strate zycia
    private int HitPlayer(int x, int y, Player p) {

        //Jezeli gracz znajduje sie na danym klocku(ktore sa wybuchem) traci zycie
        if (((x < p.getX() + 1) && (y < p.getY() + 59) && (x + 70 > p.getX() + 1) && (y + 70 > p.getY() + 59)) || ((x < p.getX() + 59) && (y < p.getY() + 59) && (x + 70 > p.getX() + 59) && (y + 70 > p.getY() + 59))) {

            if (p.getLives() > 0) {
                p.lostLive();
            }

            if (p.getLives() == 0) {
                if (p == p1) {
                    p.deletePlayer(p1.image, imgBadge[0], 1);
                }
                if (p == p2) {
                    p.deletePlayer(p2.image, imgBadge[1], 2);
                }
                if (p == p3) {
                    p.deletePlayer(p3.image, imgBadge[2], 3);
                }
                if (p == p4) {
                    p.deletePlayer(p4.image, imgBadge[3], 4);
                }

                if ((p1.getExist() && !p2.getExist() && !p3.getExist() && !p4.getExist()) || (!p1.getExist() && p2.getExist() && !p3.getExist() && !p4.getExist()) || (!p1.getExist() && !p2.getExist() && p3.getExist() && !p4.getExist()) || (!p1.getExist() && !p2.getExist() && !p3.getExist() && p4.getExist())) {

                    //imgGameOver.setVisible(true);
                    imgGameOver.setBounds(marginLeft + 140, marginUp + 280, 700, 280);

                    stoperan = true; //Koniec gry
                }
                //stoperan = true;
            }

            return czas;
        }

        return 0;
    }

    /**
     * Timer, odpowiada za pourszanie sie postaci, wybuch bomb,
     * sprawdzanie czy gracz otrzymal obrazenia oraz za poczatkowe odliczanie
     * oraz za znikanie obrazkow wybuchow
     */
    Timer timer = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (stoperan) {
                czas2++;
                if (czas2 == 430) {
                    imgCount.setVisible(false);
                    imgPoint[0].setVisible(false);
                    imgPoint[1].setVisible(false);
                    if (howMuchPlayers > 2) {
                        imgPoint[2].setVisible(false);
                        if (howMuchPlayers > 3) {
                            imgPoint[3].setVisible(false);
                        }
                    }
                    stoperan = false;
                }

            } else {

                if (p1ai && p1.getExist()) {
                    walk(p1);
                }
                if (p2ai && p2.getExist()) {
                    walk(p2);
                }
                if (p3ai && p3.getExist()) {
                    walk(p3);
                }
                if (p4ai && p4.getExist()) {
                    walk(p4);
                }

                czas++;//Z kazdym tikiem zwieksza zmienna czasu

                for (int i = 0; i < 20; i++) {
                    if (czas - bombs[i].time > 280 && bombs[i].exisst == 1)// Sprawdzamy czy ktoras z bomb nie wybuchla
                    {
                        blow.blowUp(i, bombs, imgBomb, czas, tab, SolidBlocksBoard, crateBlocks, imgCrate, imgBoomC4, powerupy, imgPowerup, maxBuffBomb, maxBuffSpeed, maxBuffPower, buffBombs, buffPowers, buffSpeed);// Jesli tak to bomba wybucha
                    }
                }

                for (int i = 0; i < 20; i++) {
                    if (hit1 == 0 && p1.getExist()) { //Jezeli nie uderzylo gracza 1 w przeciagu 300 tikow
                        hit1 = HitPlayer(tab[i][0], tab[i][1], p1);//Sprawdzamy czy dany wybuch zada obrazenie graczowi 1
                    }
                    if (hit2 == 0 && p2.getExist()) {
                        hit2 = HitPlayer(tab[i][0], tab[i][1], p2);//Sprawdzamy czy dany wybuch zada obrazenie graczowi 2
                    }
                    if (hit3 == 0 && p3.getExist()) {
                        hit3 = HitPlayer(tab[i][0], tab[i][1], p3);
                    }
                    if (hit4 == 0 && p4.getExist()) {
                        hit4 = HitPlayer(tab[i][0], tab[i][1], p4);
                    }
                }

                if (p1.getLives() != 3) {//Rysowanie serc graczy w zaleznosci od ich zyc
                    for (int i = 3 - p1.getLives(); i > 0; i--) {
                        imgHearth[3 - i].setBounds(marginLeft, marginUp, 32, 32);
                    }
                }
                if (p2.getLives() != 3) {
                    for (int i = 3 - p2.getLives(); i > 0; i--) {
                        imgHearth[3 - i + 3].setBounds(marginLeft, marginUp, 32, 32);
                    }
                }

                if (howMuchPlayers > 2) {
                    if (p3.getLives() != 3) {
                        for (int i = 3 - p3.getLives(); i > 0; i--) {
                            imgHearth[3 - i + 6].setBounds(marginLeft, marginUp, 32, 32);
                        }
                    }
                }

                if (howMuchPlayers > 3) {
                    if (p4.getLives() != 3) {
                        for (int i = 3 - p4.getLives(); i > 0; i--) {
                            imgHearth[3 - i + 9].setBounds(marginLeft, marginUp, 32, 32);
                        }
                    }
                }


                //Klawisze
                p1.movePlayer(p1.image, czas, SolidBlocksBoard, bombs, crateBlocks, powerupy, imgPowerup);
                p2.movePlayer(p2.image, czas, SolidBlocksBoard, bombs, crateBlocks, powerupy, imgPowerup);
                p3.movePlayer(p3.image, czas, SolidBlocksBoard, bombs, crateBlocks, powerupy, imgPowerup);
                p4.movePlayer(p4.image, czas, SolidBlocksBoard, bombs, crateBlocks, powerupy, imgPowerup);



                //resetujemy te argumenty tablicy wybuchow gdzie wybuch trwal 100 tikow
                for (int i = 0; i < 40; i++) {

                    if (czas - tab[i][2] > 100 && tab[i][0] != 0 && tab[i][1] != 0)// Jezeli wybuch byl na mapie przez ponad sekunde
                    {
                        imgBoomC4[i].setBounds(marginLeft, marginUp, 70, 70);// wrzucamy go na 0,0
                        //i resetujemy to
                        tab[i][0] = 0;
                        tab[i][1] = 0;
                        tab[i][2] = 0;
                    }
                }


                if (czas - hit1 == 200) {
                    hit1 = 0;//resety flag co do uderzenia gracza
                }
                if (czas - hit2 == 200) {
                    hit2 = 0;//resety flag co do uderzenia gracza
                }
                if (czas - hit3 == 200) {
                    hit3 = 0;//resety flag co do uderzenia gracza
                }
                if (czas - hit4 == 200) {
                    hit4 = 0;//resety flag co do uderzenia gracza
                }
            }
        }
    });


    public Game(int players, boolean p1ai, boolean p2ai, boolean p3ai, boolean p4ai) {

        this.p1ai = p1ai;
        this.p2ai = p2ai;
        this.p3ai = p3ai;
        this.p4ai = p4ai;
        czas2=(4-players)*5;

        //CREATE FRAME
        frame = new MyFrame();
        frame.setFocusable(true);
        frame.addKeyListener(new MyFrame());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        howMuchPlayers = players;

        ///////////////PANELS////////////////////////////////////////

        //MENU


        //West Panel
        westPanel = new JPanel();
        westPanel.setLayout(new FlowLayout());
        westPanel.setPreferredSize(new Dimension(marginLeft, frameHeight));
        westPanel.setBackground(frameColor);

        //North Panel
        northPanel = new JPanel();
        northPanel.setPreferredSize(new Dimension(frameWidth, marginUp));
        northPanel.setBackground(frameColor);

        //South Panel
        southPanel = new JPanel();
        southPanel.setPreferredSize(new Dimension(frameWidth, marginDown));
        southPanel.setBackground(frameColor);

        //East Panel
        eastPanel = new JPanel();
        eastPanel.setPreferredSize(new Dimension(marginRight, frameHeight));
        eastPanel.setBackground(frameColor);

        ////////////////////BUTTONS////////////////////////////////////////

        //PLAY
        bPlay = new JButton("Play");
        bPlay.setBounds(50, 850, 200, 50);
        frame.add(bPlay);
        bPlay.setVisible(false);
        bPlay.addActionListener(this);

        //BACK
        bBack = new JButton("Back");
        bBack.setBounds(40, 930, 200, 50);
        frame.add(bBack);
        bBack.setIcon(createToolIcon("/images/back.png"));
        bBack.addActionListener(e ->
        {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        });


        GameBoard map = new GameBoard();
        //add panels to frame
        frame.getContentPane().add(westPanel, BorderLayout.WEST);
        frame.getContentPane().add(northPanel, BorderLayout.NORTH);
        frame.getContentPane().add(eastPanel, BorderLayout.EAST);
        frame.getContentPane().add(southPanel, BorderLayout.SOUTH);
        frame.getContentPane().add(map, BorderLayout.CENTER);

        frame.getContentPane().setPreferredSize(new Dimension(frameWidth, frameHeight));
        frame.pack();
        frame.setVisible(true);
    }


    class GameBoard extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponents(g);
            //g.drawImage(imgBackground_Board, 0, 0, this);
        }

        public GameBoard() {
            //draw map
            SolidBlock SolidBoard = new SolidBlock();
            int SolidBlocksBoard[][] = SolidBoard.getXYBoard();


            imgCount = new JLabel();
            imgCount.setIcon(createToolIcon("/images/Count.gif"));
            imgCount.setBounds(marginLeft + 355, marginUp + 280, 250, 360);
            frame.add(imgCount);

            imgPoint[0] = new JLabel();
            imgPoint[0].setIcon(createToolIcon("/images/p1point.gif"));
            imgPoint[0].setBounds(marginLeft + 70, marginUp, 70, 135);
            frame.add(imgPoint[0]);

            imgPoint[1] = new JLabel();
            imgPoint[1].setIcon(createToolIcon("/images/p2point.gif"));
            imgPoint[1].setBounds(p2.getX(), p2.getY() - 70, 70, 135);
            frame.add(imgPoint[1]);

            if (howMuchPlayers > 2) {
                imgPoint[2] = new JLabel();
                imgPoint[2].setIcon(createToolIcon("/images/p3point.gif"));
                imgPoint[2].setBounds(p3.getX(), p3.getY() - 70, 70, 135);
                frame.add(imgPoint[2]);
                if (howMuchPlayers > 3) {
                    imgPoint[3] = new JLabel();
                    imgPoint[3].setIcon(createToolIcon("/images/p4point.gif"));
                    imgPoint[3].setBounds(p4.getX(), p4.getY() - 70, 70, 135);
                    frame.add(imgPoint[3]);

                }


            }

            imgGameOver = new JLabel();
            imgGameOver.setIcon(createToolIcon("/images/go2.gif"));
            imgGameOver.setBounds(1500, 900, 700, 280);
            frame.add(imgGameOver);
            // imgGameOver.setVisible(false);


            for (int i = 0; i < 82; i++) {
                imgSolid[i] = new JLabel();
                imgSolid[i].setIcon(createToolIcon("/images/wall2.png"));
                imgSolid[i].setBounds(SolidBlocksBoard[i][0] + marginLeft, SolidBlocksBoard[i][1] + marginUp, 70, 70);
                frame.add(imgSolid[i]);
            }

            for (int i = 0; i < 113; i++)//wpisuje w tablice obrazkow obrazek wybuchu
            {
                imgBoomC4[i] = new JLabel();
                imgBoomC4[i].setIcon(createToolIcon("/images/boomcross.jpg"));
                frame.add(imgBoomC4[i]);
            }

            Crate crate = new Crate();
            int crateBlocks[][] = crate.getXYBoard();


            for (int i = 0; i < 71; i++) {
                imgCrate[i] = new JLabel();
                imgCrate[i].setIcon(createToolIcon("/images/box.png"));
                imgCrate[i].setBounds(crateBlocks[i][0] + marginLeft, crateBlocks[i][1] + marginUp, 70, 70);
                frame.add(imgCrate[i]);
            }

            //player1
            p1.setExist(true);
            p1.image = new JLabel();
            p1.image.setIcon(createToolIcon("/images/player-down.JPG"));
            p1.image.setBounds(p1.getX(), p1.getY(), 60, 60);
            frame.add(p1.image);

            p2.setExist(true);
            p2.image = new JLabel();
            p2.image.setIcon(createToolIcon("/images/computer-down.JPG"));
            p2.image.setBounds(p2.getX(), p2.getY(), 70, 70);
            frame.add(p2.image);

            if (howMuchPlayers > 2) {
                p3.setExist(true);
                p3.image = new JLabel();
                p3.image.setIcon(createToolIcon("/images/computer-down.JPG"));
                p3.image.setBounds(p3.getX(), p3.getY(), 70, 70);
                frame.add(p3.image);

                imgBadge[2] = new JLabel();
                imgBadge[2].setIcon(createToolIcon("/images/p3pl.gif"));
                imgBadge[2].setBounds(-20, 540, 150, 60);//ustawiam serce gracza 2 z lewej dolnej strony
                frame.add(imgBadge[2]);

                for (int i = 6; i < 9; i++) {
                    imgHearth[i] = new JLabel();
                    imgHearth[i].setIcon(createToolIcon("/images/heart_full.png"));
                    imgHearth[i].setBounds((i - 6) * 35, 600, 32, 32);//ustawiam serce gracza 2 z lewej dolnej strony
                    frame.add(imgHearth[i]);
                }


                imgKey[2] = new JLabel();
                imgKey[2].setIcon(createToolIcon("/images/p3key.gif"));
                imgKey[2].setBounds(110, 540, 140, 90);//ustawiam serce gracza 1 z lewej gornej strony
                frame.add(imgKey[2]);


                if (howMuchPlayers > 3) {
                    p4.setExist(true);
                    p4.image = new JLabel();
                    p4.image.setIcon(createToolIcon("/images/computer-down.JPG"));
                    p4.image.setBounds(p4.getX(), p4.getY(), 70, 70);
                    frame.add(p4.image);

                    imgBadge[3] = new JLabel();
                    imgBadge[3].setIcon(createToolIcon("/images/p4pl.gif"));
                    imgBadge[3].setBounds(-20, 740, 150, 60);//ustawiam serce gracza 2 z lewej dolnej strony
                    frame.add(imgBadge[3]);


                    for (int i = 9; i < 12; i++) {
                        imgHearth[i] = new JLabel();
                        imgHearth[i].setIcon(createToolIcon("/images/heart_full.png"));
                        imgHearth[i].setBounds((i - 9) * 35, 800, 32, 32);//ustawiam serce gracza 2 z lewej dolnej strony
                        frame.add(imgHearth[i]);
                    }

                    imgKey[3] = new JLabel();
                    imgKey[3].setIcon(createToolIcon("/images/p4key.gif"));
                    imgKey[3].setBounds(110, 740, 140, 90);//ustawiam serce gracza 1 z lewej gornej strony
                    frame.add(imgKey[3]);

                }

            }

            for (int i = 0; i < 24; i++)//wpisuje obrazki powerupow
            {
                imgPowerup[i] = new JLabel();
                imgPowerup[i].setIcon(createToolIcon("/images/powB.png"));
                frame.add(imgPowerup[i]);
                powerupy[i] = new PowerUps();
            }

            for (int i = 0; i < 6; i++) {
                imgHearth[i] = new JLabel();
                imgHearth[i].setIcon(createToolIcon("/images/heart_full.png"));
                imgHearth[i].setBounds(i % 3 * 35, ((i / 3) + 1) * 200, 32, 32);//ustawiam serce gracza 1 z lewej gornej strony
                frame.add(imgHearth[i]);
            }
            imgBadge[0] = new JLabel();
            imgBadge[0].setIcon(createToolIcon("/images/p1pl.gif"));
            imgBadge[0].setBounds(-20, 140, 150, 60);//ustawiam serce gracza 2 z lewej dolnej strony
            frame.add(imgBadge[0]);

            imgBadge[1] = new JLabel();
            imgBadge[1].setIcon(createToolIcon("/images/p2pl.gif"));
            imgBadge[1].setBounds(-20, 340, 150, 60);//ustawiam serce gracza 2 z lewej dolnej strony
            frame.add(imgBadge[1]);


            imgKey[0] = new JLabel();
            imgKey[0].setIcon(createToolIcon("/images/p1key.gif"));
            imgKey[0].setBounds(110, 140, 140, 90);//ustawiam serce gracza 1 z lewej gornej strony
            frame.add(imgKey[0]);

            imgKey[1] = new JLabel();
            imgKey[1].setIcon(createToolIcon("/images/p2key.gif"));
            imgKey[1].setBounds(110, 340, 140, 90);//ustawiam serce gracza 1 z lewej gornej strony
            frame.add(imgKey[1]);


            for (int i = 0; i < 20; i++) {// obrazki bomb
                imgBomb[i] = new JLabel();
                imgBomb[i].setIcon(createToolIcon("/images/Bomb.gif"));
                imgBomb[i].setBounds(marginLeft, marginUp, 70, 70);
                frame.add(imgBomb[i]);
                //tab[i]=0;
                bombs[i] = new Bomb();
            }
            imgBackground_Board = new JLabel();
            imgBackground_Board.setIcon(createToolIcon("/images/Background_BoardGame.jpg"));
            imgBackground_Board.setBounds(marginLeft, marginUp, 1050, 910);
            frame.add(imgBackground_Board);
            timer.start();


            if (p1ai && p1.getExist()) {
                Ai(p1);
            }
            if (p2ai && p2.getExist()) {
                Ai(p2);
            }
            if (p3ai && p3.getExist()) {
                Ai(p3);
            }
            if (p4ai && p4.getExist()) {
                Ai(p4);
            }
            System.out.println(frameHeight);
            System.out.println(frameWidth);
        }

    }

    class MyFrame extends JFrame implements KeyListener {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (p1.getExist() && !p1ai) {
                if (key == KeyEvent.VK_UP) {//Jezeli jest nacisniety przycisk w gore
                    p1.setPup(true);// zmieniamy flage gracza 1 do chodzenia w gore
                }
                if (key == KeyEvent.VK_DOWN) {
                    p1.setPdown(true); //zmieniamy flage gracza 1 do chodzenia w dol
                }
                if (key == KeyEvent.VK_LEFT) {
                    p1.setPleft(true);//zmieniamy flage gracza 1 do chodzenia w lewo
                }
                if (key == KeyEvent.VK_RIGHT) {
                    p1.setPright(true);//zmieniamy flage gracza 1 do chodzenia w prawo
                }
            }

            if (p2.getExist() && !p2ai) {
                if (key == KeyEvent.VK_W) {
                    p2.setPup(true);
                }
                if (key == KeyEvent.VK_S) {
                    p2.setPdown(true);
                }

                if (key == KeyEvent.VK_A) {
                    p2.setPleft(true);
                }
                if (key == KeyEvent.VK_D) {
                    p2.setPright(true);
                }
            }

            if (p3.getExist() && !p3ai) {
                if (key == KeyEvent.VK_H) {
                    p3.setPup(true);
                }
                if (key == KeyEvent.VK_N) {
                    p3.setPdown(true);
                }

                if (key == KeyEvent.VK_B) {
                    p3.setPleft(true);
                }
                if (key == KeyEvent.VK_M) {
                    p3.setPright(true);
                }
            }

            if (p4.getExist() && !p4ai) {
                if (key == KeyEvent.VK_NUMPAD5) {
                    p4.setPup(true);
                }
                if (key == KeyEvent.VK_NUMPAD2) {
                    p4.setPdown(true);
                }
                if (key == KeyEvent.VK_NUMPAD1) {
                    p4.setPleft(true);
                }
                if (key == KeyEvent.VK_NUMPAD3) {
                    p4.setPright(true);
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();

            if (p1.getExist() && !p1ai) {
                if (key == KeyEvent.VK_CONTROL) {//plantowanie paki gracza 1
                    p1.plantBomb(bombs, czas, imgBomb);
                }
                //Gdy ktorys z przyciskow zostanie puszczony zmieniamy flagi
                if (key == KeyEvent.VK_UP) {
                    p1.setPup(false);
                    if (!(p1.getPdown() || p1.getPleft() || p1.getPright())) {
                        p1.image.setIcon(createToolIcon("/images/player-up.JPG"));
                    }
                }
                if (key == KeyEvent.VK_DOWN) {
                    p1.setPdown(false);
                    if (!(p1.getPleft() || p1.getPright() || p1.getPup())) {
                        p1.image.setIcon(createToolIcon("/images/player-down.JPG"));
                    }
                }
                if (key == KeyEvent.VK_RIGHT) {
                    p1.setPright(false);
                    if (!(p1.getPdown() || p1.getPleft() || p1.getPup())) {
                        p1.image.setIcon(createToolIcon("/images/player-right.JPG"));
                    }
                }
                if (key == KeyEvent.VK_LEFT) {
                    p1.setPleft(false);
                    if (!(p1.getPdown() || p1.getPright() || p1.getPup())) {
                        p1.image.setIcon(createToolIcon("/images/player-left.JPG"));
                    }
                }
            }
            //P2

            if (p2.getExist() && !p2ai) {
                if (key == KeyEvent.VK_W) {
                    p2.setPup(false);
                    if (!(p2.getPdown() || p2.getPleft() || p2.getPright())) {
                        p2.image.setIcon(createToolIcon("/images/computer-up.JPG"));
                    }
                }
                if (key == KeyEvent.VK_S) {
                    p2.setPdown(false);
                    if (!(p2.getPleft() || p2.getPright() || p2.getPup())) {
                        p2.image.setIcon(createToolIcon("/images/computer-down.JPG"));
                    }
                }
                if (key == KeyEvent.VK_D) {
                    p2.setPright(false);
                    if (!(p2.getPdown() || p2.getPleft() || p2.getPup())) {
                        p2.image.setIcon(createToolIcon("/images/computer-right.JPG"));
                    }
                }
                if (key == KeyEvent.VK_A) {
                    p2.setPleft(false);
                    if (!(p2.getPdown() || p2.getPright() || p2.getPup())) {
                        p2.image.setIcon(createToolIcon("/images/computer-left.JPG"));
                    }
                }
                if (key == KeyEvent.VK_Q) {
                    p2.plantBomb(bombs, czas, imgBomb);
                }
            }


            if (p3.getExist() && !p3ai) {
                if (key == KeyEvent.VK_H) {
                    p3.setPup(false);
                    if (!(p3.getPdown() || p3.getPleft() || p3.getPright())) {
                        p3.image.setIcon(createToolIcon("/images/computer-up.JPG"));
                    }
                }
                if (key == KeyEvent.VK_N) {
                    p3.setPdown(false);
                    if (!(p3.getPleft() || p3.getPright() || p3.getPup())) {
                        p3.image.setIcon(createToolIcon("/images/computer-down.JPG"));
                    }
                }
                if (key == KeyEvent.VK_M) {
                    p3.setPright(false);
                    if (!(p3.getPdown() || p3.getPleft() || p3.getPup())) {
                        p3.image.setIcon(createToolIcon("/images/computer-right.JPG"));
                    }
                }
                if (key == KeyEvent.VK_B) {
                    p3.setPleft(false);
                    if (!(p3.getPdown() || p3.getPright() || p3.getPup())) {
                        p3.image.setIcon(createToolIcon("/images/computer-left.JPG"));
                    }
                }
                if (key == KeyEvent.VK_SPACE) {
                    p3.plantBomb(bombs, czas, imgBomb);
                }
            }

            if (p4.getExist() && !p4ai) {
                if (key == KeyEvent.VK_NUMPAD5) {
                    p4.setPup(false);
                    if (!(p4.getPdown() || p4.getPleft() || p4.getPright())) {
                        p4.image.setIcon(createToolIcon("/images/computer-up.JPG"));
                    }
                }
                if (key == KeyEvent.VK_NUMPAD2) {
                    p4.setPdown(false);
                    if (!(p4.getPleft() || p4.getPright() || p4.getPup())) {
                        p4.image.setIcon(createToolIcon("/images/computer-down.JPG"));
                    }
                }
                if (key == KeyEvent.VK_NUMPAD3) {
                    p4.setPright(false);
                    if (!(p4.getPdown() || p4.getPleft() || p4.getPup())) {
                        p4.image.setIcon(createToolIcon("/images/computer-right.JPG"));
                    }
                }
                if (key == KeyEvent.VK_NUMPAD1) {
                    p4.setPleft(false);
                    if (!(p4.getPdown() || p4.getPright() || p4.getPup())) {
                        p4.image.setIcon(createToolIcon("/images/computer-left.JPG"));
                    }
                }
                if (key == KeyEvent.VK_NUMPAD0) {
                    p4.plantBomb(bombs, czas, imgBomb);
                }


            }


            if (key == KeyEvent.VK_P) {
                stoperan = !stoperan;
            }

            if (key == KeyEvent.VK_ENTER && stoperan) {

                //  bomberman.Menu menue = new bomberman.Menu();
                // menue.setVisible(true);
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                //this = null;

            }


        }

        @Override
        public void keyTyped(KeyEvent e) {
        }
    }

    class Menu extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponents(g);
            // g.drawImage(imgMenu, 0, 0, this);
        }
    }


    @Override
    public void actionPerformed(ActionEvent event) {
    }
}