import java.awt.*; 
import javax.swing.*; 
public class Board extends JPanel 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//board
    public Coord[][] coords = new Coord[4][4];
    public Coord[][] temp = new Coord[4][4];
    //background
    ImageIcon bI = new ImageIcon("background.png");
    private Image background = bI.getImage();
    //numbers
    int score=0;
    int hscore=0;
    
    int lost=0;
    //
    ImageIcon loseI= new ImageIcon("lose.jpg");
    private Image lose = loseI.getImage();
    //
    ImageIcon i2 = new ImageIcon("2.png");
    ImageIcon i4 = new ImageIcon("4.png");
    ImageIcon i8 = new ImageIcon("8.png");
    ImageIcon i16 = new ImageIcon("16.png");
    ImageIcon i32 = new ImageIcon("32.png");
    ImageIcon i64 = new ImageIcon("64.png");
    ImageIcon i128 = new ImageIcon("128.png");
    ImageIcon i256 = new ImageIcon("256.png");
    ImageIcon i512 = new ImageIcon("512.png");
    ImageIcon i1024 = new ImageIcon("1024.png");
    ImageIcon i2048 = new ImageIcon("2048.png");
    //
    private Image im2 = i2.getImage();
    private Image im4 = i4.getImage();
    private Image im8 = i8.getImage();
    private Image im16 = i16.getImage();
    private Image im32 = i32.getImage();
    private Image im64 = i64.getImage();
    private Image im128 = i128.getImage();
    private Image im256 = i256.getImage();
    private Image im512 = i512.getImage();
    private Image im1024 = i1024.getImage();
    private Image im2048 = i2048.getImage();
    
    public Board()
    {
        load();
        setVisible(true);
    }
    private void load(){
        int count =0;
        while(count<2){
            for(int r=0; r<4; r++){
                for(int c=0; c<4; c++){
                    double x = Math.random();
                    if(coords[r][c]==null){
                        if(x<.125 && count<2){
                            coords[r][c] = new Coord(r,c,2);
                            count++;
                        }else
                            coords[r][c] = new Coord(r,c,0);
                    }
                    else{
                        if(x<.125 && count<2 && coords[r][c].getValue()==0){
                            coords[r][c] = new Coord(r,c,2);
                            count++;
                        }
                    }
                }
            }
        }
    }
    public void reset(){
        coords=new Coord[4][4];
        score=0;
        lost=0;
        load();
    }
    public void undo(){
        coords=temp;
        System.out.println("undo");
    }
    @Override
    public void paintComponent(Graphics g){
        g.drawImage(background,0,0,null);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.setColor(Color.white);
        g.drawString(Integer.toString(score), 250, 86);
        g.drawString(Integer.toString(hscore), 370, 86);
        for(int r=0; r<4; r++){
            for(int c=0; c<4; c++){
                int x =coords[r][c].getValue();
                if(x!=0){
                    if(x==2)
                        g.drawImage(im2,(c*100+30)+(c*14),(r*100+215)+(r*14),null);
                    else if(x==4)
                        g.drawImage(im4,(c*100+30)+(c*14),(r*100+215)+(r*14),null);
                    else if(x==8)
                        g.drawImage(im8,(c*100+30)+(c*14),(r*100+215)+(r*14),null);
                    else if(x==16)
                        g.drawImage(im16,(c*100+30)+(c*14),(r*100+215)+(r*14),null);    
                    else if(x==32)
                        g.drawImage(im32,(c*100+30)+(c*14),(r*100+215)+(r*14),null);
                    else if(x==64)
                        g.drawImage(im64,(c*100+30)+(c*14),(r*100+215)+(r*14),null);
                    else if(x==128)
                        g.drawImage(im128,(c*100+30)+(c*14),(r*100+215)+(r*14),null);
                    else if(x==256)
                        g.drawImage(im256,(c*100+30)+(c*14),(r*100+215)+(r*14),null);
                    else if(x==512)
                        g.drawImage(im512,(c*100+30)+(c*14),(r*100+215)+(r*14),null);
                    else if(x==1024)
                        g.drawImage(im1024,(c*100+30)+(c*14),(r*100+215)+(r*14),null);
                    else if(x==2048)
                        g.drawImage(im2048,(c*100+30)+(c*14),(r*100+215)+(r*14),null);
                    else System.out.println("this coord has an invalid value of " + x);
                }
            }
        }
        if(lost==1){
            g.fillRect(0,0,530,740);
            g.drawImage(lose,50,80,null);
            g.setFont(new Font("Serif", Font.PLAIN, 30));
            g.setColor(Color.black);
            g.drawString("No more moves!!!",150,530); 
            g.drawString("Your score was",165,560); 
            g.drawString(""+score,180,610); 
            g.drawString("Press \"R\" to restart",135,660);
        }
    }
    public void spawn(){
        int count=0;
        int timeout =0;
        while(count==0){
            double x=Math.random();
            double y=Math.random();
            int r,c;
            if(x<.25) c=0;
            else if(x<.5) c=1;
            else if(x<.75) c=2;
            else c=3;
            if(y<.25) r=0;
            else if(y<.5) r=1;
            else if(y<.75) r=2;
            else r=3;
            if(coords[r][c].getValue()==0){
                double chance = Math.random();
                if (chance<.9) coords[r][c].setValue(2);
                else coords[r][c].setValue(4);
                count++;
            }
            else {
                timeout++;
                System.out.println(timeout);
            }
            if (timeout>1000)
                {lost=1;
                 count++;
                }
        }
    }
    public void down(){
        System.out.println("down");
        for(int c=3; c>=0; c--){
            for(int r=3; r>=0; r--){
                int x =coords[r][c].getValue();
                int y=0;
                int last =0;
                for(int i=0; i<r; i++){
                    if(coords[i][c].getValue()!=0){
                        y=coords[i][c].getValue();
                        last = i;
                    }
                }
                //if(last==c) System.out.println("crashed into");
                if(x==0 && y!=0){
                    coords[r][c].setValue(y);
                    coords[last][c].setValue(0);
                    r++;
                }
                else if(y==0) break;
                else if(y==x && last!= r){
                    coords[r][c].setValue(x*2);
                    score+=x*2;
                    coords[last][c].setValue(0);
                }
            }
        }
    }
    public void up(){
        System.out.println("up");
        for(int c=0; c<4; c++){
            for(int r=0; r<4; r++){
                int x =coords[r][c].getValue();
                int y=0;
                int last =3;
                for(int i=3; i>r; i--){
                    if(coords[i][c].getValue()!=0){
                        y=coords[i][c].getValue();
                        last = i;
                    }
                }
                //if(last==c) System.out.println("crashed into");
                if(x==0 && y!=0){
                    coords[r][c].setValue(y);
                    coords[last][c].setValue(0);
                    r--;
                }
                else if(y==0) break;
                else if(y==x && last!= r){
                    coords[r][c].setValue(x*2);
                    score+=x*2;
                    coords[last][c].setValue(0);
                }
            }
        }
    }
    public void right(){
        System.out.println("right");
        for(int r=3; r>=0; r--){
            for(int c=3; c>=0; c--){
                int x =coords[r][c].getValue();
                int y=0;
                int last =0;
                for(int i=0; i<c; i++){
                    if(coords[r][i].getValue()!=0){
                        y=coords[r][i].getValue();
                        last = i;
                    }
                }
                //if(last==c) System.out.println("crashed into");
                if(x==0 && y!=0){
                    coords[r][c].setValue(y);
                    coords[r][last].setValue(0);
                    c++;
                }
                if(y==0) break;
                if(y==x && last!= c){
                    coords[r][c].setValue(x*2);
                    score+=x*2;
                    coords[r][last].setValue(0);
                }
            }
        }
    }
    public void left(){
        System.out.println("left");
        for(int r=0; r<4; r++){
            for(int c=0; c<4; c++){
                int x =coords[r][c].getValue();
                int y=0;
                int last =0;
                for(int i=3; i>c; i--){
                    if(coords[r][i].getValue()!=0){
                        y=coords[r][i].getValue();
                        last = i;
                    }
                }
                //if(last==c) System.out.println("crashed into");
                if(x==0 && y!=0){
                    coords[r][c].setValue(y);
                    coords[r][last].setValue(0);
                    c--;
                }
                if(y==0) break;
                if(y==x && last!= c){
                    coords[r][c].setValue(x*2);
                    score+=x*2;
                    coords[r][last].setValue(0);
                }
            }
        }
    }
}