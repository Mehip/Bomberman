package bomberman;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.LinkedList;

public class Player {

    private int bombs;
    private int lives;
    private int power;
    private int x;
    private int y;
    private int speed;
    int manyBombs;
    int blokada = 0;

    JLabel image;

    private int marginLeft = 255, marginRight = 45, marginUp = 45, marginDown = 45;

    private boolean exist;
    private boolean pright;
    private boolean pleft;
    private boolean pup;
    private boolean pdown;
    boolean priorytet;

    int czassi = 0;

    LinkedList<Maps> map = new LinkedList<Maps>();
    LinkedList<Integer> iks = new LinkedList<Integer>();
    LinkedList<Integer> igrek = new LinkedList<Integer>();

    Player(int x, int y) {
        bombs = 2;
        lives = 3;
        power = 3;
        this.x = x;
        this.y = y;
        manyBombs = 0;
        speed = 1;

        pright = false;
        pleft = false;
        pup = false;
        pdown = false;
        exist = false;
        priorytet = false;

    }

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

    private void addBombs() {
        bombs++;
    }

    void lostLive() {
        lives--;
    }

    private void addPower() {
        power++;
    }

    private void addSpeed() {
        speed++;
    }

    private int getBombs() {
        return bombs;
    }

    int getLives() {
        return lives;
    }

    private void addXY(int xx, int yy) {
        x += xx;
        y += yy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPower() {
        return power;
    }

    private int getSpeed() {
        return speed;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public boolean getExist() {
        return exist;
    }

    public void setPright(boolean pright) {
        this.pright = pright;
    }

    public void setPleft(boolean pleft) {
        this.pleft = pleft;
    }

    public void setPup(boolean pup) {
        this.pup = pup;
    }

    public void setPdown(boolean pdown) {
        this.pdown = pdown;
    }

    public boolean getPright() {
        return pright;
    }

    public boolean getPleft() {
        return pleft;
    }

    public boolean getPup() {
        return pup;
    }

    public boolean getPdown() {
        return pdown;
    }

    /**
     * Metoda sprawdzająca czy w tym miejscu nie bedzie sciany, skrzyni lub bomby
     *
     * @param x
     * @param y
     * @param kierunek
     * @param SolidBlocksBoard
     * @param bombs
     * @param crateBlocks
     * @return Zwraca wartość blokady, jeżeli równe 0 to postać moze się poruszać
     */
    public int blocked(int x, int y, int kierunek, int SolidBlocksBoard[][], Bomb bombs[], int crateBlocks[][]) {
        blokada = 0;//Zmienna od informacji o tym czy mozna sie poryuszyc. 0 to oznacza ze mozna

        for (int i = 0; i < 82; i++) {// Sprawdzamy czy jakis solid blok nie bedzie na miejscu
            if (((SolidBlocksBoard[i][0] + marginLeft < x + 1) && (SolidBlocksBoard[i][1] + marginUp < y + 1) && (SolidBlocksBoard[i][0] + marginLeft + 70 > x + 1) && (SolidBlocksBoard[i][1] + marginUp + 70 > y + 1)) || ((SolidBlocksBoard[i][0] + marginLeft < x + 59) && (SolidBlocksBoard[i][1] + marginUp < y + 59) && (SolidBlocksBoard[i][0] + marginLeft + 70 > x + 59) && (SolidBlocksBoard[i][1] + marginUp + 70 > y + 59)) || ((SolidBlocksBoard[i][0] + marginLeft < x) && (SolidBlocksBoard[i][1] + marginUp < y + 59) && (SolidBlocksBoard[i][0] + marginLeft + 70 > x) && (SolidBlocksBoard[i][1] + marginUp + 70 > y + 59)) || ((SolidBlocksBoard[i][0] + marginLeft < x + 59) && (SolidBlocksBoard[i][1] + marginUp < y) && (SolidBlocksBoard[i][0] + marginLeft + 70 > x + 59) && (SolidBlocksBoard[i][1] + marginUp + 70 > y)))
                blokada++;
        }

        for (int i = 0; i < 71; i++) {// Sprawdzamy czy jakis bloczek nie bedzie na jego miejscu
            if (((crateBlocks[i][0] + marginLeft < x + 1) && (crateBlocks[i][1] + marginUp < y + 1) && (crateBlocks[i][0] + marginLeft + 70 > x + 1) && (crateBlocks[i][1] + marginUp + 70 > y + 1)) || ((crateBlocks[i][0] + marginLeft < x + 59) && (crateBlocks[i][1] + marginUp < y + 59) && (crateBlocks[i][0] + marginLeft + 70 > x + 59) && (crateBlocks[i][1] + marginUp + 70 > y + 59)) || ((crateBlocks[i][0] + marginLeft < x) && (crateBlocks[i][1] + marginUp < y + 59) && (crateBlocks[i][0] + marginLeft + 70 > x) && (crateBlocks[i][1] + marginUp + 70 > y + 59)) || ((crateBlocks[i][0] + marginLeft < x + 59) && (crateBlocks[i][1] + marginUp < y) && (crateBlocks[i][0] + marginLeft + 70 > x + 59) && (crateBlocks[i][1] + marginUp + 70 > y))) {
                blokada += 10;
            }
        }

        //Dla bomb( Hit box)

        if (kierunek == 1) {//Jezeli gracz idzie w gore
            for (int i = 0; i < 20; i++) {// Sprawdzamy czy jakas bomba nie bedzie na jego miejscu
                if (((bombs[i].getX() < x + 1) && (bombs[i].getY() + 40 < y + 1) && (bombs[i].getX() + 70 > x + 1) && (bombs[i].getY() + 70 > y + 1)) || ((bombs[i].getX() < x + 59) && (bombs[i].getY() + 40 < y + 1) && (bombs[i].getX() + 70 > x + 59) && (bombs[i].getY() + 70 > y + 1))) {
                    blokada++;
                }
            }
        }//itd dla nastepnych kierunkow

        if (kierunek == 2) {
            for (int i = 0; i < 20; i++) {
                if (((bombs[i].getX() < x + 1) && (bombs[i].getY() < y + 59) && (bombs[i].getX() + 70 > x + 1) && (bombs[i].getY() - 40 + 70 > y + 59)) || ((bombs[i].getX() < x + 59) && (bombs[i].getY() < y + 59) && (bombs[i].getX() + 70 > x + 59) && (bombs[i].getY() - 40 + 70 > y + 59))) {
                    blokada++;
                }
            }
        }

        if (kierunek == 3) {
            for (int i = 0; i < 20; i++) {
                if (((bombs[i].getX() + 40 < x + 1) && (bombs[i].getY() < y + 1) && (bombs[i].getX() + 70 > x + 1) && (bombs[i].getY() + 70 > y + 1)) || ((bombs[i].getX() + 40 < x + 1) && (bombs[i].getY() < y + 59) && (bombs[i].getX() + 70 > x + 1) && (bombs[i].getY() + 70 > y + 59))) {
                    blokada++;
                }
            }
        }

        if (kierunek == 4) {
            for (int i = 0; i < 20; i++) {
                if (((bombs[i].getX() < x + 59) && (bombs[i].getY() < y + 1) && (bombs[i].getX() - 40 + 70 > x + 59) && (bombs[i].getY() + 70 > y + 1)) || ((bombs[i].getX() < x + 59) && (bombs[i].getY() < y + 59) && (bombs[i].getX() - 40 > x + 59) && (bombs[i].getY() + 70 > y + 59))) {
                    blokada++;
                }
            }
        }
        return blokada;
    }

    /**
     * Metoda do sprawdzania przez AI czy w tym miejscu nie bedzie sciany, skrzyni lub bomby
     * Rozni sie rozmiarem hitboxu bomby od blocked
     *
     * @param x
     * @param y
     * @param kierunek
     * @param SolidBlocksBoard
     * @param bombs
     * @param crateBlocks
     * @return Zwraca wartość blokady, jeżeli równe 0 to postać moze się poruszać
     */
    public int blocked2(int x, int y, int kierunek, int SolidBlocksBoard[][], Bomb bombs[], int crateBlocks[][]) {
        blokada = 0;//Zmienna od informacji o tym czy mozna sie poryuszyc. 0 to oznacza ze mozna

        for (int i = 0; i < 82; i++) {// Sprawdzamy czy jakis solid blok nie bedzie na miejscu
            if (((SolidBlocksBoard[i][0] + marginLeft < x + 1) && (SolidBlocksBoard[i][1] + marginUp < y + 1) && (SolidBlocksBoard[i][0] + marginLeft + 70 > x + 1) && (SolidBlocksBoard[i][1] + marginUp + 70 > y + 1)) || ((SolidBlocksBoard[i][0] + marginLeft < x + 59) && (SolidBlocksBoard[i][1] + marginUp < y + 59) && (SolidBlocksBoard[i][0] + marginLeft + 70 > x + 59) && (SolidBlocksBoard[i][1] + marginUp + 70 > y + 59)) || ((SolidBlocksBoard[i][0] + marginLeft < x) && (SolidBlocksBoard[i][1] + marginUp < y + 59) && (SolidBlocksBoard[i][0] + marginLeft + 70 > x) && (SolidBlocksBoard[i][1] + marginUp + 70 > y + 59)) || ((SolidBlocksBoard[i][0] + marginLeft < x + 59) && (SolidBlocksBoard[i][1] + marginUp < y) && (SolidBlocksBoard[i][0] + marginLeft + 70 > x + 59) && (SolidBlocksBoard[i][1] + marginUp + 70 > y)))
                blokada++;
        }

        for (int i = 0; i < 71; i++) {// Sprawdzamy czy jakis bloczek nie bedzie na jego miejscu
            if (((crateBlocks[i][0] + marginLeft < x + 1) && (crateBlocks[i][1] + marginUp < y + 1) && (crateBlocks[i][0] + marginLeft + 70 > x + 1) && (crateBlocks[i][1] + marginUp + 70 > y + 1)) || ((crateBlocks[i][0] + marginLeft < x + 59) && (crateBlocks[i][1] + marginUp < y + 59) && (crateBlocks[i][0] + marginLeft + 70 > x + 59) && (crateBlocks[i][1] + marginUp + 70 > y + 59)) || ((crateBlocks[i][0] + marginLeft < x) && (crateBlocks[i][1] + marginUp < y + 59) && (crateBlocks[i][0] + marginLeft + 70 > x) && (crateBlocks[i][1] + marginUp + 70 > y + 59)) || ((crateBlocks[i][0] + marginLeft < x + 59) && (crateBlocks[i][1] + marginUp < y) && (crateBlocks[i][0] + marginLeft + 70 > x + 59) && (crateBlocks[i][1] + marginUp + 70 > y))) {
                blokada += 10;
            }
        }

        //Dla bomb( Hit box)

        for (int i = 0; i < 20; i++) {// Sprawdzamy czy jakis solid blok nie bedzie na miejscu
            if (((bombs[i].getX() < x + 1) && (bombs[i].getY() < y + 1) && (bombs[i].getX() + 70 > x + 1) && (bombs[i].getY() + 70 > y + 1)) || ((bombs[i].getX() < x + 59) && (bombs[i].getY() < y + 59) && (bombs[i].getX() + 70 > x + 59) && (bombs[i].getY() + 70 > y + 59)) || ((bombs[i].getX() < x) && (SolidBlocksBoard[i][1] < y + 59) && (bombs[i].getX() + 70 > x) && (SolidBlocksBoard[i][1] + 70 > y + 59)) || ((bombs[i].getX() < x + 59) && (SolidBlocksBoard[i][1] < y) && (bombs[i].getX() + 70 > x + 59) && (SolidBlocksBoard[i][1] + 70 > y)))
                blokada++;
        }
        /*
        for (int i = 0; i < 20; i++) {
            if((bombs[i].getX()== x || bombs[i].getX() +5 == x || bombs[i].getX() + 10== x)&& (bombs[i].getY()== y || bombs[i].getY() + 5 == y || bombs[i].getY() + 10== y))
            {
                blokada++;
            }
        }*/

        return blokada;
    }

    /**
     * Metoda do podłożenia bomby, przypisujmy jej gracza oraz czas postawienia
     *
     * @param bombs
     * @param czas
     * @param imgBomb
     */
    public void plantBomb(Bomb[] bombs, int czas, JLabel[] imgBomb) {

        int j = 0;
        int x, y;
        int exit = 0;
        if (getBombs() > manyBombs) {//Czy gracz 1 moze plantowac
            for (int i = 0; i < 20; i++)
                if (bombs[i].exisst == 0 && j == 0) {//Szukamy bomby ktorej nie ma na mapie

                    if ((this.x - 255) % 70 < 35)// Jezeli jest na wiekszosci ma jednym z tych blokow- bomba zostaje podlozona na srodku kwadratu
                    {
                        x = this.x - ((this.x - 255) % 70);
                    } else
                        x = this.x - ((this.x - 255) % 70) + 70;

                    if ((this.y - 45) % 70 < 35) {
                        y = this.y - ((this.y - 45) % 70);
                    } else
                        y = this.y - ((this.y - 45) % 70) + 70;


                    for (int k = 0; k < 20; k++) {
                        if (bombs[k].getX() == x && bombs[k].getY() == y && bombs[k].exisst == 1)//Nie mozemy podlozyc bomby gdy juz jedna jest na jej miejscu
                        {
                            exit = 1;
                        }

                    }
                    if (exit == 0) { //jezli mozemy podlozyc tu bomba
                        bombs[i].plantBomb(x, y, getPower(), czas, i, this);//Wpisujemy ja do tablicy
                        // bomba=bombs[i];
                        bombs[i].exisst = 1;// i zmieniamy jej flage na to ze istnieje

                        imgBomb[i].setIcon(createToolIcon("/images/Bomb.gif"));
                        imgBomb[i].setBounds(x, y, 70, 70);//rysujemy
                        j++;
                        manyBombs++;//Zmniejszamy ilosc mozliwych bomb gracza
                    }

                }

        }

    }

    /**
     * Metoda do poruszania się postaci, zmienia obrazek postaci oraz
     * wywoluje blockes aby sprawdzic czy moze poruszac sie w danym kierunku
     *
     * @param imgage
     * @param czas
     * @param SolidBlocksBoard
     * @param bombs
     * @param crateBlocks
     * @param powerupy
     * @param imgPowerup
     */
    public void movePlayer(JLabel imgage, int czas, int SolidBlocksBoard[][], Bomb bombs[], int crateBlocks[][], PowerUps powerupy[], JLabel imgPowerup[]) {
        if (this.getExist()) {
            if (czas % (4 - this.getSpeed()) == 0) {//W zaleznosci od wartosci predkosci gracza 1 co tyle czasu trigeruje
                if (this.getPup()) {//Dla chodzenia w gore
                    if (this.blocked(this.x, this.y - 5, 1, SolidBlocksBoard, bombs, crateBlocks) == 0) {//Sprawdzamy czy nie natrafimy na bloczki czy bombe
                        this.addXY(0, -5);//Jesli tak to zmieniamy jego pozycje
                        imgage.setIcon(createToolIcon("/images/pu1.gif"));
                    }
                    imgage.setBounds(this.x, this.y, 60, 60);
                    this.CollectPowerUp(this.x, this.y - 5, powerupy, imgPowerup);//Sprawdzamy czy gracz zebral powerup
                }//itd

                if (this.getPdown()) {
                    if (this.blocked(this.x, this.y + 5, 2, SolidBlocksBoard, bombs, crateBlocks) == 0) {
                        this.addXY(0, 5);
                        imgage.setIcon(createToolIcon("/images/pd1.gif"));
                    }
                    imgage.setBounds(this.x, this.y, 60, 60);
                    this.CollectPowerUp(this.x, this.y + 5, powerupy, imgPowerup);
                }

                if (this.getPleft()) {
                    if (this.blocked(this.x - 5, this.y, 3, SolidBlocksBoard, bombs, crateBlocks) == 0) {
                        this.addXY(-5, 0);
                        imgage.setIcon(createToolIcon("/images/pl1.gif"));
                    }
                    imgage.setBounds(this.x, this.y, 60, 60);
                    this.CollectPowerUp(this.x - 5, this.y, powerupy, imgPowerup);
                }

                if (this.getPright()) {
                    if (this.blocked(this.x + 5, this.y, 4, SolidBlocksBoard, bombs, crateBlocks) == 0) {
                        this.addXY(5, 0);
                        imgage.setIcon(createToolIcon("/images/lpp1.gif"));
                    }
                    imgage.setBounds(this.x, this.y, 60, 60);
                    this.CollectPowerUp(this.x + 5, this.y, powerupy, imgPowerup);
                }
            }
        }

    }

    /**
     * Metoda do usuwania gracza który zginął.
     *
     * @param imgPlayer
     * @param imgBadge
     * @param player
     */
    public void deletePlayer(JLabel imgPlayer, JLabel imgBadge, int player) {
        imgPlayer.setVisible(false);

        setExist(false);
        if (player == 1) {
            imgBadge.setIcon(createToolIcon("/images/p1pld.gif"));
        }
        if (player == 2) {
            imgBadge.setIcon(createToolIcon("/images/p2pld.gif"));
        }
        if (player == 3) {
            imgBadge.setIcon(createToolIcon("/images/p3pld.gif"));
        }
        if (player == 4) {
            imgBadge.setIcon(createToolIcon("/images/p4pld.gif"));
        }

    }

    /**
     * Metoda do zbieraia powerupow, dla danego miejsca i gracza
     *
     * @param x
     * @param y
     * @param powerupy
     * @param imgPowerup
     */
    private void CollectPowerUp(int x, int y, PowerUps[] powerupy, JLabel[] imgPowerup) {

        for (int i = 0; i < 24; i++) {// sprawdza tablice powerupow
            if ((powerupy[i].getX() == x || powerupy[i].getX() + 5 == x || powerupy[i].getX() + 10 == x) && (powerupy[i].getY() == y || powerupy[i].getY() + 5 == y || powerupy[i].getY() + 10 == y))//Jezeli gracz jest na tym kwadracie
            {
                //W zaleznosci od numeru powerupa 0,1,2:   Dodaje graczowi powerup oraz przemieszcza powerup na 0,0
                if (powerupy[i].getPower() == 0) {
                    addBombs();
                    powerupy[i].setXY(0, 0);
                    powerupy[i].setPower(0);
                    imgPowerup[i].setBounds(marginLeft, marginUp, 70, 70);
                }

                if (powerupy[i].getPower() == 1) {
                    addPower();
                    powerupy[i].setXY(0, 0);
                    powerupy[i].setPower(0);
                    imgPowerup[i].setBounds(marginLeft, marginUp, 70, 70);
                }

                if (powerupy[i].getPower() == 2) {
                    //Max 3 speed
                    if (getSpeed() < 3) {
                        addSpeed();
                    }
                    powerupy[i].setXY(0, 0);
                    powerupy[i].setPower(0);
                    imgPowerup[i].setBounds(marginLeft, marginUp, 70, 70);
                }
            }
        }

    }
}
