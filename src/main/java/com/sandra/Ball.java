package com.sandra;
import java.awt.*;
import java.util.*;

public class Ball extends Rectangle{

    Random random;
    int xVelocity; // predkosc na osi x
    int yVelocity; // predkosc na osi y
    int initialSpeed = 4; // poczatkowa predkosc

    Ball(int x, int y, int width, int height){
        super(x,y,width,height);
        random = new Random();
        int randomXDirection = random.nextInt(2); // local variable dla pilku konstruktora
        if(randomXDirection == 0)// pojdzie w lewo
            randomXDirection--; //bedzie mniejsza niz zero
        setXDirection(randomXDirection*initialSpeed); // zwiekszamy predkosci pilki

        int randomYDirection = random.nextInt(2);
        if(randomYDirection == 0)
            randomYDirection--;
        setYDirection(randomYDirection*initialSpeed);

    }

    public void setXDirection(int randomXDirection) {
        xVelocity = randomXDirection;
    }
    public void setYDirection(int randomYDirection) {
        yVelocity = randomYDirection;
    }
    public void move() {
        x += xVelocity;
        y += yVelocity;
    }
    public void draw(Graphics g) { // malujemy pilke
        g.setColor(Color.white);
        g.fillOval(x, y, height, width); // pilka (kolo )
    }
}