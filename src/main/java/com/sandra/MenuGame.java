package com.sandra;

import javax.swing.*;
import java.awt.*;


class MenuGame extends JFrame  {
    JMenu menu, submenu;
    JMenuItem i1, i2, i3, i4, i5;
    GamePanel panel;
    MenuGame() {

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
        panel = new GamePanel();
        this.add(panel);
        this.setTitle("Sandra Game");
        this.setResizable(false);
        this.setBackground(Color.lightGray);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setJMenuBar(mb);
        mb.add(menu);
    }

}