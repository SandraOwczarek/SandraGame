package com.sandra;
import java.awt.*;
import java.awt.event.*;

public class Racket extends Rectangle{

    int id; // rakietka 1 i rakietka dwa
    int yVelocity; // predkosci rakietki
    int speed = 10;

    Racket(int x, int y, int RACKET_WIDTH, int RACKET_HEIGHT, int id){
        super(x,y,RACKET_WIDTH,RACKET_HEIGHT); // super bo to subclass od rectangle class
        this.id=id; // numer rakietki gracza
    }

    public void keyPressed(KeyEvent e) {
        switch(id) { //dla paletki 1wszej
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_Q) { // naciskamy klawisz Q
                    setYDirection(-speed); // w gore
                }
                if(e.getKeyCode()==KeyEvent.VK_A) {
                    setYDirection(speed); // w dol  klawisz a
                }
                break;
            case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP) {
                    setYDirection(-speed);
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN) {
                    setYDirection(speed);
                }
                break;
        }
    }
    public void keyReleased(KeyEvent e) {
        switch(id) {
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_Q) {
                    setYDirection(0); // zero zeby nie ruszala sie w nieskonczonosc
                }
                if(e.getKeyCode()==KeyEvent.VK_A) {
                    setYDirection(0);
                }
                break;
            case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP) {
                    setYDirection(0);
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN) {
                    setYDirection(0);
                }
                break;
        }
    }
    public void setYDirection(int yDirection) {
        yVelocity = yDirection;
    }
    public void move() {
        y= y + yVelocity;
    }
    public void draw(Graphics g) {
        if(id==1)
            g.setColor(Color.orange); // rakietka pierwszego gracza  -kolor
        else
            g.setColor(Color.red); // paletka drugiego gracza - kolor
        g.fillRect(x, y, width, height); // parametry rakietki
    }
}