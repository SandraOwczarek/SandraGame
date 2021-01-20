package com.sandra;
import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame{
    GamePanel panel; // canvas gdzie bedziemy malowac gre
    GameFrame(){ // konstruktor
        panel = new GamePanel(); // dodajemy rzeczy do naszej planszy
        this.add(panel);
        this.setTitle("Sandra Game");
        this.setResizable(false); // nie mozna zmienic rozmiaru gry
        this.setBackground(Color.lightGray);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack(); //metoda z JFrame , gra sie zmiesci w naszym panelu
        this.setVisible(true); // by widziec gre
        this.setLocationRelativeTo(null); // gra pojawi sie po samym srodku naszego ekranu
    }
}
