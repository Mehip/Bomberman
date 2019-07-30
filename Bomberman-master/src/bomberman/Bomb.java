package bomberman;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Random;

public class Bomb {
    private int x;
    private int y;
    private int power;
    private Player player;
    int time;
    int exisst = 0;
    private int lp;

    private int marginLeft = 255;
    private int marginUp = 45;

    private int[][] xySolid = {
            {150, 280} //blok 1
    };

    Bomb() {
        x = 0;
        y = 0;
        power = 1;
        time = 0;
        exisst = 0;
    }

    void plantBomb(int xx, int yy, int playerPower, int czas, int l, Player p) {
        x = xx;
        y = yy;
        power = playerPower;
        time = czas;
        exisst = 1;
        player = p;

        lp = l;
    }

    private int getPower() {
        return this.power;
    }

    int getX() {
        return this.x;
    }

    int getY() {
        return this.y;
    }

    private void setXY(int xx, int yy) {
        x = xx;
        y = yy;
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

    //Metoda do wpisywania wybuchow do tablicy by ja pozniej narysowac
    private int WriteBlow(int x, int y, int time, int[][] tab) {
        for (int i = 0; i < 40; i++) {
            if (tab[i][0] == 0 && tab[i][1] == 0 && tab[i][2] == 0)// Jezeli wartosc w tablicy sa wyzerowane
            {
                //Dane miejsce w tablicy przyjmuje x,y oraz czas w ktorym wybuchlo
                tab[i][0] = x;
                tab[i][1] = y;
                tab[i][2] = time;

                return 0;
            }
        }
        return 1;
    }

    //Rysowanie wybuchu
    private void DrawBlow(int[][] tab, JLabel[] imgBoomC4) {
        int up, down, right, left;//Zmienne


        for (int i = 0; i < 40; i++)// Dla kazdego miejsca wybuchu
        {
            up = 0;
            down = 0;
            right = 0;
            left = 0;

            //W timerze co jedno uderzenie resetuje hit1 i hit2
            if (tab[i][0] != 0 && tab[i][1] != 0 && tab[i][2] != 0) { //Jezeli wybuch jest na mapie

                //Przeszukujemy tablice by znalesc czy istnieje wybuch ktory jest z gory, dolu, lewej  i prawej strony
                for (int j = 0; j < 40; j++) {
                    if (tab[i][0] == tab[j][0] && tab[i][1] - 70 == tab[j][1]) {
                        up = 1;
                    }
                }

                for (int j = 0; j < 40; j++) {
                    if (tab[i][0] == tab[j][0] && tab[i][1] + 70 == tab[j][1]) {
                        down = 1;
                    }
                }

                for (int j = 0; j < 40; j++) {
                    if (tab[i][0] - 70 == tab[j][0] && tab[i][1] == tab[j][1]) {
                        left = 1;
                    }
                }

                for (int j = 0; j < 40; j++) {
                    if (tab[i][0] + 70 == tab[j][0] && tab[i][1] == tab[j][1]) {
                        right = 1;
                    }
                }

                //W zaleznosci od tego rysuje wybuch
                //4 kierunki
                if (up == 1 && down == 1 && left == 1 && right == 1) {
                    imgBoomC4[i].setBounds(tab[i][0], tab[i][1], 70, 70);
                    imgBoomC4[i].setIcon(createToolIcon("/images/boomcross.gif"));
                }
                //3 kierunki
                if (up != 1 && down == 1 && left == 1 && right == 1) {
                    imgBoomC4[i].setBounds(tab[i][0], tab[i][1], 70, 70);
                    imgBoomC4[i].setIcon(createToolIcon("/images/boomcross3u.jpg"));
                }
                if (up == 1 && down != 1 && left == 1 && right == 1) {
                    imgBoomC4[i].setBounds(tab[i][0], tab[i][1], 70, 70);
                    imgBoomC4[i].setIcon(createToolIcon("/images/boomcross3d.jpg"));

                }
                if (up == 1 && down == 1 && left != 1 && right == 1) {
                    imgBoomC4[i].setBounds(tab[i][0], tab[i][1], 70, 70);
                    imgBoomC4[i].setIcon(createToolIcon("/images/boomcross3l.jpg"));

                }
                if (up == 1 && down == 1 && left == 1 && right != 1) {
                    imgBoomC4[i].setBounds(tab[i][0], tab[i][1], 70, 70);
                    imgBoomC4[i].setIcon(createToolIcon("/images/boomcross3p.jpg"));

                }
                //2 kierunki
                if (up == 1 && down == 1 && left == 0 && right == 0) {
                    imgBoomC4[i].setBounds(tab[i][0], tab[i][1], 70, 70);
                    imgBoomC4[i].setIcon(createToolIcon("/images/boompion.gif"));

                }
                if (up == 0 && down == 0 && left == 1 && right == 1) {
                    imgBoomC4[i].setBounds(tab[i][0], tab[i][1], 70, 70);
                    imgBoomC4[i].setIcon(createToolIcon("/images/boompoz.gif"));

                }
                //Skret
                if (up == 1 && down == 0 && left == 1 && right == 0) {
                    imgBoomC4[i].setBounds(tab[i][0], tab[i][1], 70, 70);
                    imgBoomC4[i].setIcon(createToolIcon("/images/boomul.jpg"));
                }
                if (up == 1 && down == 0 && left == 0 && right == 1) {
                    imgBoomC4[i].setBounds(tab[i][0], tab[i][1], 70, 70);
                    imgBoomC4[i].setIcon(createToolIcon("/images/boomupr.jpg"));

                }
                if (up == 0 && down == 1 && left == 0 && right == 1) {
                    imgBoomC4[i].setBounds(tab[i][0], tab[i][1], 70, 70);
                    imgBoomC4[i].setIcon(createToolIcon("/images/boomdp.jpg"));

                }
                if (up == 0 && down == 1 && left == 1 && right == 0) {
                    imgBoomC4[i].setBounds(tab[i][0], tab[i][1], 70, 70);
                    imgBoomC4[i].setIcon(createToolIcon("/images/boomdl.jpg"));

                }
                //Koncowka wybuchu
                if (up == 1 && down == 0 && left == 0 && right == 0) {
                    imgBoomC4[i].setBounds(tab[i][0], tab[i][1], 70, 70);
                    imgBoomC4[i].setIcon(createToolIcon("/images/boomdownend.gif"));

                }
                if (up == 0 && down == 1 && left == 0 && right == 0) {
                    imgBoomC4[i].setBounds(tab[i][0], tab[i][1], 70, 70);
                    imgBoomC4[i].setIcon(createToolIcon("/images/boomupend.gif"));

                }
                if (up == 0 && down == 0 && left == 1 && right == 0) {
                    imgBoomC4[i].setBounds(tab[i][0], tab[i][1], 70, 70);
                    imgBoomC4[i].setIcon(createToolIcon("/images/boomrightend.gif"));

                }
                if (up == 0 && down == 0 && left == 0 && right == 1) {
                    imgBoomC4[i].setBounds(tab[i][0], tab[i][1], 70, 70);
                    imgBoomC4[i].setIcon(createToolIcon("/images/boomleftend.gif"));

                }

            }
        }
    }

    /**
     * Metoda do wybuchu bomby, ktora przyjmuje dlugosc rowna mocy wybuchu gracza i
     * zatrzymuje sie na blokach i skrzyniach oraz uruchamia bomby na drodze wybuchu
     *
     * @param i
     * @param bombs
     * @param imgBomb
     * @param czas
     * @param tab
     * @param SolidBlocksBoard
     * @param crateBlocks
     * @param imgCrate
     * @param imgBoomC4
     * @param powerupy
     * @param imgPowerup
     * @param maxBuffBomb
     * @param maxBuffSpeed
     * @param maxBuffPower
     * @param buffBombs
     * @param buffPowers
     * @param buffSpeed
     */
    void blowUp(int i, Bomb[] bombs, JLabel[] imgBomb, int czas, int[][] tab, int[][] SolidBlocksBoard, int[][] crateBlocks, JLabel[] imgCrate, JLabel[] imgBoomC4, PowerUps[] powerupy, JLabel[] imgPowerup, int maxBuffBomb, int maxBuffSpeed, int maxBuffPower, int buffBombs, int buffPowers, int buffSpeed) {
        Random rand = new Random();

        bombs[i].exisst = 0;//Zmieniamy flage tablicy exist na 0, czyli nie istnieje

        int bu = 0, bd = 0, bl = 0, br = 0;// Zmienne od zakonczenia wpisywania wybuchow w kierunkach
        int buu = 0, bdd = 0, bll = 0, brr = 0;// Spotanie z Solid blokami

        //Przypisywanie x,y bomby i resetowanie jej
        int x = bombs[i].getX();
        int y = bombs[i].getY();

        bombs[i].setXY(marginLeft, marginUp);

        imgBomb[i].setIcon(createToolIcon("/images/bomb.jpg"));
        imgBomb[i].setBounds(marginLeft, marginUp, 70, 70);
        // Oraz oddanie mozliwosci plantowania paki
        bombs[i].player.manyBombs--;

        //Wybuch w miejscu bomby
        bombs[i].WriteBlow(x, y, czas, tab);

        //Dla kolejnych rozgalezien wybuchu sprawdzamy tyle pol ile player mial mocy w chwili postawienia
        for (int j = 1; j < bombs[i].getPower(); j++) {

            if (bu == 0)//Jezeli jest rowne 0 to bedzie dalej wybisywalo
            {
                for (int k = 0; k < 82; k++) {
                    if ((SolidBlocksBoard[k][0] + marginLeft == x) && (SolidBlocksBoard[k][1] + marginUp == y - j * 70) && bu == 0) {//Sprawdzamy czy bomba nie natrafila na solid block
                        bu++;
                        buu++;// Wtedy zatrzymujemy wypisywanie w tym kierunku
                    }
                }

                for (int k = 0; k < 71; k++) {
                    if ((crateBlocks[k][0] + marginLeft == x) && (crateBlocks[k][1] + marginUp == y - j * 70) && bu == 0) {//Sprawdzamy czy bomba nie natrafila na bloczek
                        crateBlocks[k][0] = 0;
                        crateBlocks[k][1] = 0;//Wtedy resetujemy bloczek
                        imgCrate[k].setVisible(false);
                        if (rand.nextInt(10) < 3) {// Oraz losujemy liczbe od 0 do 9 czy wydropi cos
                            AddPowerUp(x, y - j * 70, powerupy, imgPowerup, maxBuffBomb, maxBuffSpeed, maxBuffPower, buffBombs, buffPowers, buffSpeed);
                        }
                        bu++;// i konczymy wypisywanie w tym kierunku
                    }
                }
                for (int k = 0; k < 20; k++) {// Dla kazdej bomby
                    if ((bombs[k].getX() == x) && (bombs[k].getY() == y - j * 70) && k != i) { // sprawdzamy czy inna bomba nie spota sie na drodze
                        blowUp(k, bombs, imgBomb, czas, tab, SolidBlocksBoard, crateBlocks, imgCrate, imgBoomC4, powerupy, imgPowerup, maxBuffBomb, maxBuffSpeed, maxBuffPower, buffBombs, buffPowers, buffSpeed); // jesli tak to bum bum
                    }
                }

                if (buu == 0) {
                    bombs[i].WriteBlow(x, y - j * 70, czas, tab);
                }//Jezeli funkcja nie natrafila w tym miejscu na nic to wpisuje to miejsce do tablicy wybuchow
            }
            //itd dla lewej prawej i dolu
            if (bd == 0) {
                for (int k = 0; k < 82; k++) {
                    if ((SolidBlocksBoard[k][0] + marginLeft == x) && (SolidBlocksBoard[k][1] + marginUp == y + j * 70) && bd == 0) {
                        bd++;
                        bdd++;
                    }
                }

                for (int k = 0; k < 71; k++) {
                    if ((crateBlocks[k][0] + marginLeft == x) && (crateBlocks[k][1] + marginUp == y + j * 70) && bd == 0) {
                        imgCrate[k].setVisible(false);
                        crateBlocks[k][0] = 0;
                        crateBlocks[k][1] = 0;
                        if (rand.nextInt(10) < 3) {

                            AddPowerUp(x, y + j * 70, powerupy, imgPowerup, maxBuffBomb, maxBuffSpeed, maxBuffPower, buffBombs, buffPowers, buffSpeed);
                        }
                        bd++;
                    }
                }
                for (int k = 0; k < 20; k++) {
                    if ((bombs[k].getX() == x) && (bombs[k].getY() == y + j * 70) && k != i) {
                        blowUp(k, bombs, imgBomb, czas, tab, SolidBlocksBoard, crateBlocks, imgCrate, imgBoomC4, powerupy, imgPowerup, maxBuffBomb, maxBuffSpeed, maxBuffPower, buffBombs, buffPowers, buffSpeed);
                    }
                }

                if (bdd == 0) {
                    bombs[i].WriteBlow(x, y + j * 70, czas, tab);
                }
            }
            if (bl == 0) {
                for (int k = 0; k < 82; k++) {
                    if ((SolidBlocksBoard[k][0] + marginLeft == x - j * 70) && (SolidBlocksBoard[k][1] + marginUp == y) && bl == 0) {
                        bl++;
                        bll++;
                    }
                }

                for (int k = 0; k < 71; k++) {
                    if ((crateBlocks[k][0] + marginLeft == x - j * 70) && (crateBlocks[k][1] + marginUp == y) && bl == 0) {
                        imgCrate[k].setVisible(false);
                        crateBlocks[k][0] = 0;
                        crateBlocks[k][1] = 0;
                        if (rand.nextInt(10) < 3) {
                            AddPowerUp(x - j * 70, y, powerupy, imgPowerup, maxBuffBomb, maxBuffSpeed, maxBuffPower, buffBombs, buffPowers, buffSpeed);
                        }
                        bl++;
                    }
                }
                for (int k = 0; k < 20; k++) {
                    if ((bombs[k].getX() == x - j * 70) && (bombs[k].getY() == y) && k != i) {
                        blowUp(k, bombs, imgBomb, czas, tab, SolidBlocksBoard, crateBlocks, imgCrate, imgBoomC4, powerupy, imgPowerup, maxBuffBomb, maxBuffSpeed, maxBuffPower, buffBombs, buffPowers, buffSpeed);
                    }
                }

                if (bll == 0) {
                    bombs[i].WriteBlow(x - j * 70, y, czas, tab);
                }
            }

            if (br == 0) {
                for (int k = 0; k < 82; k++) {
                    if ((SolidBlocksBoard[k][0] + marginLeft == x + j * 70) && (SolidBlocksBoard[k][1] + marginUp == y) && br == 0) {
                        br++;
                        brr++;
                    }
                }

                for (int k = 0; k < 71; k++) {
                    if ((crateBlocks[k][0] + marginLeft == x + j * 70) && (crateBlocks[k][1] + marginUp == y) && br == 0) {
                        imgCrate[k].setVisible(false);
                        crateBlocks[k][0] = 0;
                        crateBlocks[k][1] = 0;
                        if (rand.nextInt(10) < 3) {
                            AddPowerUp(x + j * 70, y, powerupy, imgPowerup, maxBuffBomb, maxBuffSpeed, maxBuffPower, buffBombs, buffPowers, buffSpeed);
                        }
                        br++;
                    }
                }
                for (int k = 0; k < 20; k++) {
                    if ((bombs[k].getX() == x + j * 70) && (bombs[k].getY() == y) && k != i) {
                        blowUp(k, bombs, imgBomb, czas, tab, SolidBlocksBoard, crateBlocks, imgCrate, imgBoomC4, powerupy, imgPowerup, maxBuffBomb, maxBuffSpeed, maxBuffPower, buffBombs, buffPowers, buffSpeed);
                    }
                }

                if (brr == 0) {
                    bombs[i].WriteBlow(x + j * 70, y, czas, tab);
                }
            }
        }
        DrawBlow(tab, imgBoomC4);
    }

    //Dodawanie powerupow
    private void AddPowerUp(int x, int y, PowerUps[] powerupy, JLabel[] imgPowerup, int maxBuffBomb, int maxBuffSpeed, int maxBuffPower, int buffBombs, int buffPowers, int buffSpeed) {
        Random rand = new Random();
        int j = 0;//Zmienna od stopu funkcji
        int r = 0;
        for (int i = 0; i < 24; i++)//Sprawdzamy kazda wartosc tablicy powerupow
        {
            //System.out.println(powerupy[i].getX()+" "+powerupy[i].getY());
            if (powerupy[i].getX() == 0 && powerupy[i].getY() == 0 && j == 0)// Jezeli x,y oraz to jeszcze nie wpisalismy powerupa to szukamy miejsca
            {
                j++;
                //Tablica powerupow i obraz powerow przyjmuje wspolrzedne x,y
                powerupy[i].setXY(x, y);
                imgPowerup[i].setBounds(x, y, 70, 70);

                //Szukamy liczby od 0 do liczby wszystkich DOSTEPNYCH powerow-1
                r = rand.nextInt(maxBuffBomb + maxBuffSpeed + maxBuffPower - buffBombs - buffPowers - buffSpeed);
                //System.out.println(r);
                if (r < maxBuffBomb - buffBombs) {//Jezeli liczba ta nalezy od 0 do liczby dostepnych dodatkowych bomb
                    powerupy[i].setPower(0);//Wpisz 0 - co oznacza dodatkowa bombe
                    buffBombs++; //Oraz oznaczamy ile juz droplo

                    imgPowerup[i].setIcon(createToolIcon("/images/powBB.gif"));

                } else if (r < maxBuffBomb - buffBombs + maxBuffPower - buffPowers) {//Jezeli liczba ta nalezy od liczby dostepnych dodatkowych bomb do sumy liczb dostepnych bomb i power
                    powerupy[i].setPower(1);// Wpisz 1 co oznacza moc
                    buffPowers++;
                    //itd
                    imgPowerup[i].setIcon(createToolIcon("/images/powPP.gif"));
                } else {
                    powerupy[i].setPower(2);
                    buffSpeed++;

                    imgPowerup[i].setIcon(createToolIcon("/images/powSS.gif"));
                }

            }
        }
    }
}