import javax.swing.JFrame;
import java.awt.event.*; 
@SuppressWarnings("serial")
public class App extends JFrame
{
    /**
	 * 
	 */
	public App()
    {
        initUI();
    }
    private void initUI(){
        setSize(515,730);
        Board board = new Board();
        add(board);
        
        addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e){
                int x = e.getKeyCode();
                //System.out.println(x);
                
                if(x<41 && x>36){
                    board.temp=board.coords;
                    if(x==37) board.left();
                    else if(x==38) board.up();
                    else if(x==39) board.right();
                    else if(x==40) board.down();
                    board.spawn();
                }
                if(x==10) board.lost=1;
                if(x==82) board.reset();
                else if(x==8) board.undo();
                if(board.score>board.hscore) board.hscore=board.score;
                board.repaint();
            }
        });
        setVisible(true);
        setResizable(false);
        setTitle("2048");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    public static void main(String[] args){
        App app = new App();
        app.setVisible(true);
    }
}
