import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;


public class SnakeGame extends JPanel implements ActionListener, KeyListener {
    private class Tile {
        int x;
        int y;
        Tile(int x, int y) 
        {

            this.x = x;
            this.y = y;
        }
    }
// adding and updating the code
    int boardwidth;
    int boardHeight;
     //snake 
       Tile snakeHead;
       ArrayList<Tile> snakeBody = new ArrayList<>();
       //food for the snake  
       Tile food;
       //random 
       Random random;
       // game logic
       Timer gameLoop; 
       int velocityX;
       int velocityY;
       boolean gameOver = false;



    SnakeGame(int boardwidth, int boardHeight) {
        this.boardwidth = boardwidth;
        this.boardHeight = boardHeight;
        addKeyListener(this);
        setFocusable(true);
        setPreferredSize(new Dimension(this.boardwidth, this.boardHeight));
        setBackground(Color.black);
        snakeHead = new Tile(5, 5);
        snakeBody = new ArrayList<>();
        food = new Tile(10, 10);
        random = new Random();
        placeFood();

        velocityX = 0;
        velocityY = 0;

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
        // for(int i = 0 ; i < boardwidth/tileSize; i++)
        //     {
        //          g.drawLine(i*tileSize, 0, i* tileSize, boardHeight);
        //          g.drawLine(0 , i*tileSize, boardwidth,i*tileSize);
        //         }
    //food

    g.setColor(Color.red);
    // g.fillRect(food.x*tileSize, food.y*tileSize, tileSize, tileSize);
    g.fill3DRect(food.x*tileSize, food.y*tileSize, tileSize, tileSize,true);
        //SNAKE
        g.setColor(Color.green);
      //  g.fillRect(snakeHead.x * tileSize, snakeHead.y *  tileSize , tileSize, tileSize);
        g.fill3DRect(snakeHead.x * tileSize, snakeHead.y *  tileSize , tileSize, tileSize,true);

        for(int i = 0 ; i <snakeBody.size();i++){
            Tile snakepart =  snakeBody.get(i);
            g.fill3DRect(snakepart.x*tileSize, snakepart.y*tileSize, tileSize, tileSize,true);
        }
        //score
        g.setFont(new Font("Arial", Font.PLAIN , 18));
        if(gameOver){
            g.setColor(Color.red);

            g.drawString("Game Over : "+ String.valueOf(snakeBody.size()),tileSize -16 , tileSize);
        }
        else{
            g.drawString("Score: "+ String.valueOf(snakeBody.size()), tileSize -16, tileSize);
        }
    }
     public void placeFood()
     {
        int tileSize = 25;
      food.x = random.nextInt(boardwidth/tileSize);
      food.y = random.nextInt(boardHeight/tileSize);

        }
        public boolean collision (Tile tile1, Tile tile2){
            return tile1.x == tile2.x && tile1.y == tile2.y;
        }
        // public void move(){
        //     //eat food
        //     if(collision(snakeHead, food)){
        //         snakeBody.add(new Tile(food.x , food.y));
        //         placeFood();
        //     }
        // }
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if(gameOver){
            gameLoop.stop();
        }
    }
    public void move() {
         //eat food
         if(collision(snakeHead, food)){
            snakeBody.add(new Tile(food.x , food.y));
            placeFood();
         }
            //move the body
            for(int i= snakeBody.size()-1 ; i >= 0 ; i--){
                Tile snakepart = snakeBody.get(i);
                if(i ==0 ){
                    snakepart.x = snakeHead.x;
                    snakepart.y = snakeHead.y;
                }
                else
                {
                    Tile prevsnakepart = snakeBody.get(i-1);
                    snakepart.x = prevsnakepart.x;
                    snakepart.y = prevsnakepart.y;

                }
            }
        //snake head
        snakeHead.x += velocityX;
        snakeHead.y += velocityY;
        //wall collision
        for(int i =0 ; i < snakeBody.size(); i++){
            Tile snakepart = snakeBody.get(i);
            
            if(collision(snakeHead, snakepart))
            {
                gameOver = true;
            }
        }
        int tileSize = 25;
        if(snakeHead.x* tileSize < 0 || snakeHead.x* tileSize > boardwidth ||
         snakeHead.y * tileSize < 0 || snakeHead.y * tileSize > boardHeight)
         {
          gameOver =true;
         }
         }

    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP && velocityY != 1){
                   velocityX =0 ;
                   velocityY =-1;
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN  && velocityY != -1 ){
            velocityX = 0;
            velocityY = 1;      
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT && velocityX != 1 ){
            velocityX = -1;
            velocityY = 0;
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT && velocityX != -1){
            velocityX = 1;
            velocityY = 0;
        }
        
    }
    @Override
    public void keyReleased(KeyEvent e) {
     
    }
    
}
