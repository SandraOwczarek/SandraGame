package com.sandra;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{ // implementacja Runnable interfejsu
     // plansza i pilka
    static final int GAME_WIDTH = 1000; // szybszy , wszyscy beda dzielic ten sam rozmiar
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555)); //stadardowy rozmiar 'boiska'
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);//wymiar
    static final int BALL_DIAMETER = 25;// rozmiar pilki
    static final int PADDLE_WIDTH = 25; // rozmiar rakietki
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
        this.setFocusable(true); // zawsze bedzie czuly na nacisk klawiszy
        this.addKeyListener(new ActionListener());// odpowie na nacisk klawisza (action listener)
        this.setPreferredSize(SCREEN_SIZE); // przekazac dimension
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void newBall() {
        random = new Random();
        ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER); // pilka zawsze bedzie zaczynac po srodku gry
    }
    public void newRackets() { // ustawiamy miejsca paletek
        racket1 = new Racket(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1); //ustawiamy na lewa strone id dla rakietki 1wszej
        racket2 = new Racket(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2); // prawa strona dla drugiej rakietki
    }
    public void paint(Graphics g) { // malowanie na ekranie
        image = createImage(getWidth(),getHeight()); // pobieramy rozmiar naszego panelu
        graphics = image.getGraphics(); // pobieramy grafike
        draw(graphics); // przekazujemy grafiki
        g.drawImage(image,0,0,this); // przekazujemy dalej
    }
    public void draw(Graphics g) {
        racket1.draw(g);
        racket2.draw(g); // "g" dla " pass the graphics"
        ball.draw(g);
        score.draw(g);
        Toolkit.getDefaultToolkit().sync(); // ulepsza animacje gry
    }
    public void move() { // musza ruszczac sie gladko
        racket1.move();
        racket2.move();
        ball.move();
    }
    public void checkCollision() {
        //piłka musi odbijac się od górnej i dolnej krawędzi okna
        if(ball.y <=0) {  // zmieni kierunek
            ball.setYDirection(-ball.yVelocity);
        }
        if(ball.y >= GAME_HEIGHT-BALL_DIAMETER) {
            ball.setYDirection(-ball.yVelocity);
        }
        //pilka odbija sie od rakietek
        if(ball.intersects(racket1)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++; //opcjonalnie
            if(ball.yVelocity>0)
                ball.yVelocity++; //opcjonalnie
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
        // gracz zdobywa 1 punkt i zaczynamy runde od nowa z nowa pilka i rakietkami
        if(ball.x <=0) { // zawodnik zdobywa punkt
            score.player2++; // dodajemy 1 punkt
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
        long lastTime = System.nanoTime(); // kopia game loop z minecraft'a ;D
        double amountOfTicks =60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true) { // true albo running
            long now = System.nanoTime();
            delta += (now -lastTime)/ns;
            lastTime = now;
            if(delta >=1) {
                move(); // ruszmay wszystkie rakietki i pilke
                checkCollision();
                repaint();
                delta--;
            }
        }
    }
    public class ActionListener extends KeyAdapter{  //'action listener'
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