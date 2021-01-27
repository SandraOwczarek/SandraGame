package com.sandra;

import javax.swing.*;
import java.awt.*;


class MenuGame extends JFrame  {
    JMenu menu, submenu;
    JMenuItem i1, i2, i3, i4, i5;
    GamePanel panel; // canvas gdzie bedziemy malowac gre
    MenuGame() { // konstruktor

        JMenuBar mb=new JMenuBar();
        menu = new JMenu ("Menu");
        submenu = new JMenu("Level ");
        i1= new JMenuItem ("Open New Game");
        i2= new JMenuItem ("Save game");
        i3= new JMenuItem ("Easy");
        i4= new JMenuItem ("Medium");
        i5= new JMenuItem ("Hard");
        menu.add(i1); menu.add(i2);
        submenu.add(i3);submenu.add(i4); submenu.add(i5);
        menu.add(submenu);
        panel = new GamePanel(); // dodajemy rzeczy do naszej planszy
        this.add(panel);
        this.setTitle("Sandra Game");
        this.setResizable(false); // nie mozna zmienic rozmiaru gry
        this.setBackground(Color.lightGray);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack(); //metoda z JFrame , gra sie zmiesci w naszym panelu
        this.setVisible(true); // by widziec gre
        this.setLocationRelativeTo(null); // gra pojawi sie po samym srodku naszego ekranu
        this.setJMenuBar(mb);
        mb.add(menu);
    }

}