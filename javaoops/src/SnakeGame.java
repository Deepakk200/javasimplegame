import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class SnakeGame extends JPanel implements ActionListener {
    private class Tile {
        int x;
        int y;
        Tile(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
 
    int boardwidth;
    int boardHeight;
     //snake 
       Tile snakeHead;
       //food for the snake  
       Tile food;
       //random 
       Random random;
       // game logic
       Timer gameLoop; 


    SnakeGame(int boardwidth, int boardHeight) {
        this.boardwidth = boardwidth;
        this.boardHeight = boardHeight;
        setPreferredSize(new Dimension(this.boardwidth, this.boardHeight));
        setBackground(Color.black);
        snakeHead = new Tile(5, 5);
        food = new Tile(10, 10);
        random = new Random();
        placeFood();
        gameLoop = new javax.swing.Timer(100, this);
        gameLoop.start();
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g)
    {
        int tileSize = 25;
        //GRID
        for(int i = 0 ; i < boardwidth/tileSize; i++)
            {
                 g.drawLine(i*tileSize, 0, i* tileSize, boardHeight);
                 g.drawLine(0 , i*tileSize, boardwidth,i*tileSize);
               
                }
    //food
    g.setColor(Color.red);
    g.fillRect(food.x*tileSize, food.y*tileSize, tileSize, tileSize);
        //SNAKE
        g.setColor(Color.green);
        g.fillRect(snakeHead.x * tileSize, snakeHead.y *  tileSize , tileSize, tileSize);
    }
     public void placeFood(){
        int tileSize = 25;
      food.x = random.nextInt(boardwidth/tileSize);
      food.y = random.nextInt(boardHeight/tileSize);

        }
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
    
}
