package com.sandra;
import java.awt.*;
import java.awt.event.*;

public class Racket extends Rectangle{

    int id;
    int yVelocity;
    int speed = 10;

    Racket(int x, int y, int RACKET_WIDTH, int RACKET_HEIGHT, int id){
        super(x,y,RACKET_WIDTH,RACKET_HEIGHT);
        this.id=id;
    }

    public void keyPressed(KeyEvent e) {
        switch(id) {
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_Q) {
                    setYDirection(-speed);
                }
                if(e.getKeyCode()==KeyEvent.VK_A) {
                    setYDirection(speed);
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
                    setYDirection(0);
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
            g.setColor(Color.orange);
        else
            g.setColor(Color.red);
        g.fillRect(x, y, width, height);
    }
}