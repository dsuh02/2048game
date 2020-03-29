import java.awt.Image;

public class Coord{
    private int x,y,v; // 0-covered 1-opened 2-flagged 3-exploded
    public Coord(int x, int y, int v){
        this.x=x;
        this.y=y;
        this.v=v;
    }
    public int getDown(){ return x;}    
    public int getRight(){ return y;}
    public int getValue(){ return v;}
    public void setValue(int v){ this.v=v;}
}