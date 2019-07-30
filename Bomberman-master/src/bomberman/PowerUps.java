package bomberman;

public class PowerUps {
    private int x;
    private int y;
    private int power;

    int getX(){ return x;}
    int getY(){ return y;}
    int getPower(){ return power;}

    PowerUps()
    {
        x=0;
        y=0;
    }

    void setXY(int xx, int yy){ x =xx;y=yy;}

    void setPower(int p){power = p;} // 0 -- nowa bomba , 1 -- wieksza moc , 2 -- wieksza predkosc
}
