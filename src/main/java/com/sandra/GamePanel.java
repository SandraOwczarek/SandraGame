package com.sandra;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{
     // plansza i pilka
    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
    static final int BALL_DIAMETER = 15;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Racket racket1;
    Racket racket2;
    Ball ball;
    Score score;

    GamePanel(){
        newRackets();
        newBall();
        score = new Score(GAME_WIDTH,GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void newBall() {
        random = new Random();
        ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER);
    }
    public void newRackets() {
        racket1 = new Racket(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
        racket2 = new Racket(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);
    }
    public void paint(Graphics g) {
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }
    public void draw(Graphics g) {
        racket1.draw(g);
        racket2.draw(g);
        ball.draw(g);
        score.draw(g);
        Toolkit.getDefaultToolkit().sync(); // ulepsza animacje gry
    }
    public void move() {
        racket1.move();
        racket2.move();
        ball.move();
    }
    public void checkCollision() {
        //piłka musi odbijac się od górnej i dolnej krawędzi okna
        if(ball.y <=0) {
            ball.setYDirection(-ball.yVelocity);
        }
        if(ball.y >= GAME_HEIGHT-BALL_DIAMETER) {
            ball.setYDirection(-ball.yVelocity);
        }
        //pilka odbija sie od rakietek
        if(ball.intersects(racket1)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++; //optional for more difficulty
            if(ball.yVelocity>0)
                ball.yVelocity++; //optional for more difficulty
            else
                ball.yVelocity--;
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        if(ball.intersects(racket2)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++; //opcjonalnie wyzszy poziom
            if(ball.yVelocity>0)
                ball.yVelocity++; //opcjonalnie wyzszy poziom
            else
                ball.yVelocity--;
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        //by rakieta nie wychodzila po za gre
        if(racket1.y<=0)
            racket1.y=0;
        if(racket1.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
            racket1.y = GAME_HEIGHT-PADDLE_HEIGHT;
        if(racket2.y<=0)
            racket2.y=0;
        if(racket2.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
            racket2.y = GAME_HEIGHT-PADDLE_HEIGHT;
        // gracz zdobywa 1 punkt i zaczynamy runde od nowa
        if(ball.x <=0) {
            score.player2++;
            newRackets();
            newBall();
            System.out.println("Player 2: "+score.player2);
        }
        if(ball.x >= GAME_WIDTH-BALL_DIAMETER) {
            score.player1++;
            newRackets();
            newBall();
            System.out.println("Player 1: "+score.player1);
        }
    }
    public void run() {
        //"game loop"
        long lastTime = System.nanoTime();
        double amountOfTicks =60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true) {
            long now = System.nanoTime();
            delta += (now -lastTime)/ns;
            lastTime = now;
            if(delta >=1) {
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }
    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e) {
            racket1.keyPressed(e);
            racket2.keyPressed(e);
        }
        public void keyReleased(KeyEvent e) {
            racket1.keyReleased(e);
            racket2.keyReleased(e);
        }
    }
}