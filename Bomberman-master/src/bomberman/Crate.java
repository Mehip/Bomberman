package bomberman;

public class Crate {

    public int[][] xySolid = {

            {70, 280, 140, 280, 140, 350, 70, 350}, //blok 1
            {70, 350, 140, 350, 140, 420, 70, 420}, //blok 2
            {70, 560, 140, 560, 140, 630, 70, 630}, //blok 3

            {140, 210, 210, 210, 210, 280, 140, 280}, //blok 4
            {140, 490, 210, 490, 210, 560, 140, 560}, //blok 5
            {140, 630, 210, 630, 210, 700, 140, 700}, //blok 6

            {210, 140, 280, 140, 280, 210, 210, 210}, //blok 7
            {210, 210, 280, 210, 280, 280, 210, 280}, //blok 8
            {210, 350, 280, 350, 280, 420, 210, 420}, //blok 9
            {210, 420, 280, 420, 280, 490, 210, 490}, //blok 10
            {210, 490, 280, 490, 280, 560, 210, 560}, //blok 11
            {210, 560, 280, 560, 280, 630, 210, 630}, //blok 12
            {210, 630, 280, 630, 280, 700, 210, 700}, //blok 13
            {210, 700, 280, 700, 280, 770, 210, 770}, //blok 14


            {280, 70, 350, 70, 350, 140, 280, 140}, //blok 15
            {280, 350, 350, 350, 350, 420, 280, 420}, //blok 16
            {280, 490, 350, 490, 350, 560, 280, 560}, //blok 17
            {280, 630, 350, 630, 350, 700, 280, 700}, //blok 18
            {280, 770, 350, 770, 350, 840, 280, 840}, //blok 19


            {350, 210, 420, 210, 420, 280, 350, 280}, //blok 20
            {350, 280, 420, 280, 420, 350, 350, 350}, //blok 21
            {350, 420, 420, 420, 420, 490, 350, 490}, //blok 22
            {350, 490, 420, 490, 420, 560, 350, 560}, //blok 23
            {350, 560, 420, 560, 420, 630, 350, 630}, //blok 24
            {350, 700, 420, 700, 420, 770, 350, 770}, //blok 25

            {420, 70, 490, 70, 490, 140, 420, 140}, //blok 26
            {420, 210, 490, 210, 490, 280, 420, 280}, //blok 27
            {420, 350, 490, 350, 490, 420, 420, 420}, //blok 28
            {420, 630, 490, 630, 490, 700, 420, 700}, //blok 29
            {420, 770, 490, 770, 490, 840, 420, 840}, //blok 30

            {490, 70, 560, 70, 560, 140, 490, 140}, //blok 31
            {490, 140, 560, 140, 560, 210, 490, 210}, //blok 32
            {490, 210, 560, 210, 560, 280, 490, 280}, //blok 33
            {490, 280, 560, 280, 560, 350, 490, 350}, //blok 34
            {490, 420, 560, 420, 560, 490, 490, 490}, //blok 35
            {490, 560, 560, 560, 560, 630, 490, 630}, //blok 36
            {490, 700, 560, 700, 560, 770, 490, 770}, //blok 37

            {560, 70, 630, 70, 630, 140, 560, 140}, //blok 38
            {560, 210, 630, 210, 630, 280, 560, 280}, //blok 39
            {560, 490, 630, 490, 630, 560, 560, 560}, //blok 40
            {560, 630, 630, 630, 630, 700, 560, 700}, //blok 41
            {560, 770, 630, 770, 630, 840, 560, 840}, //blok 42


            {630, 70, 700, 70, 700, 140, 630, 140}, //blok 43
            {630, 140, 700, 140, 700, 210, 630, 210}, //blok 44
            {630, 210, 700, 210, 700, 280, 630, 280}, //blok 45
            {630, 280, 700, 280, 700, 350, 630, 350}, //blok 46
            {630, 350, 700, 350, 700, 420, 630, 420}, //blok 47
            {630, 420, 700, 420, 700, 490, 630, 490}, //blok 48
            {630, 490, 700, 490, 700, 560, 630, 560}, //blok 49
            {630, 560, 700, 560, 700, 630, 630, 630}, //blok 50
            {630, 630, 700, 630, 700, 700, 630, 700}, //blok 51
            {630, 700, 700, 700, 700, 770, 630, 770}, //blok 52


            {700, 70, 770, 70, 770, 140, 700, 140}, //blok 53
            {700, 210, 770, 210, 770, 280, 700, 280}, //blok 54
            {700, 350, 770, 350, 770, 420, 700, 420}, //blok 55
            {700, 490, 770, 490, 770, 560, 700, 560}, //blok 56
            {700, 630, 770, 630, 770, 700, 700, 700}, //blok 57
            {700, 770, 770, 770, 770, 840, 700, 840}, //blok 58


            {770, 280, 840, 280, 840, 350, 770, 350}, //blok 59
            {770, 420, 840, 420, 840, 490, 770, 490}, //blok 60
            {770, 490, 840, 490, 840, 560, 770, 560}, //blok 61
            {770, 700, 840, 700, 840, 770, 770, 770}, //blok 62


            {840, 210, 910, 210, 910, 280, 840, 280}, //blok 63
            {840, 350, 910, 350, 910, 420, 840, 420}, //blok 64
            {840, 490, 910, 490, 910, 560, 840, 560}, //blok 65
            {840, 630, 910, 630, 910, 700, 840, 700}, //blok 66


            {910, 280, 980, 280, 980, 350, 910, 350}, //blok 67
            {910, 350, 980, 350, 980, 420, 910, 420}, //blok 68
            {910, 420, 980, 420, 980, 490, 910, 490}, //blok 69
            {910, 490, 980, 490, 980, 560, 910, 560}, //blok 70
            {910, 560, 980, 560, 980, 630, 910, 630}, //blok 71

    };

    /**
     * Metoda zwracajaca tablice z klasy przechowujaca wspolrzedne skrzyni
     *
     * @return zwracana jest tablica przecowujaca wspolrzedne skrzyni
     */
    public int[][] getXYBoard() {
        return this.xySolid;
    }
}
