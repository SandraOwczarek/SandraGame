package com.sandra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class GameFrame extends JFrame implements ActionListener, KeyListener {
    JMenuBar menuBar;
    JMenu useIt, seeItRun, exit, viewTheCode;
    JMenuItem sirOpen, sirSave ;
    JMenuItem vtcOpen, vtcSave;

    GamePanel panel; // canvas gdzie bedziemy malowac gre

    GameFrame() { // konstruktor
        panel = new GamePanel(); // dodajemy rzeczy do naszej planszy
        this.add(panel);
        this.addKeyListener(this);
        this.setJMenuBar (menuBar);
        this.setTitle("Sandra Game");
        this.setResizable(false); // nie mozna zmienic rozmiaru gry
        this.setBackground(Color.lightGray);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack(); //metoda z JFrame , gra sie zmiesci w naszym panelu
        this.setVisible(true); // by widziec gre
        this.setLocationRelativeTo(null); // gra pojawi sie po samym srodku naszego ekranu

        menuBar = new JMenuBar();
        useIt = new JMenu ("Sandra Menu Game");
        menuBar.add(useIt);

        viewTheCode = new JMenu("Viewing the code");
        useIt.add(viewTheCode);


        exit = new JMenu ("Exit");
        exit.setMnemonic(KeyEvent.VK_X);

        menuBar.add(exit);

        seeItRun = new JMenu("Seeing it run");
        useIt.add(seeItRun);

        viewTheCode = new JMenu ("Viewing the code");
        useIt.add(viewTheCode);

        sirOpen= new JMenuItem ("Open a file",new ImageIcon ("images/open_document_16.gif"));
        sirOpen.addActionListener(this);
        seeItRun.add(sirOpen);

        sirSave = new JMenuItem ("Save a file", new ImageIcon("images/save_16.gif"));
        sirSave.addActionListener(this);
        seeItRun.add(sirSave);

        vtcOpen = new JMenuItem ("Open a file", new ImageIcon("images/open_document_16.gif"));
        vtcOpen.addActionListener(this);
        viewTheCode.add(vtcOpen);

        vtcSave = new JMenuItem ("Save a file ", new ImageIcon("images/save_16.git"));
        vtcSave.addActionListener(this);
        viewTheCode.add(vtcSave);

        this.setJMenuBar(menuBar);

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'x') {
            System.exit(0);
        }
    }
}
