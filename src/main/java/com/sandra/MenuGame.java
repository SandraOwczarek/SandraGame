package com.sandra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


class MenuGame extends JFrame implements ActionListener {
    JMenu menu, submenu;
    JMenuItem btnLoad, btnSave, i3, i4, i6;
    GamePanel panel;


    MenuGame() {

        JMenuBar mb = new JMenuBar();
        menu = new JMenu("Menu");
        submenu = new JMenu("Level ");

        btnLoad = new JMenuItem("Load Game");
        LoadOption load = new LoadOption();
        btnLoad.addActionListener(load);

        btnSave = new JMenuItem("Save game");
        SaveOption save = new SaveOption();
        btnSave.addActionListener(save);

        i3 = new JMenuItem("Easy");
        EasyOption Easy = new EasyOption();
        i3.addActionListener(Easy);

        i4 = new JMenuItem("Hard");
        HardOption Hard = new HardOption();
        i4.addActionListener(Hard);


        i6 = new JMenuItem("Exit");
        ExitOption exit = new ExitOption();
        i6.addActionListener(exit);

        menu.add(btnLoad);
        menu.add(btnSave);
        menu.add(i6);
        submenu.add(i3);
        submenu.add(i4);

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

    @Override
    public void actionPerformed(ActionEvent e) {


    }

    static class ExitOption implements ActionListener {
        public void actionPerformed(ActionEvent Exit) {
            System.exit(0);
        }
    }

    static class SaveOption implements ActionListener  {
        public void actionPerformed(ActionEvent Save) {
            PrintWriter Scores= null;
            try {
                Scores = new PrintWriter("Score.txt");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Scores.println("Score of last saved game was" );
            Scores.close();
        }
    }

    static class LoadOption implements ActionListener {
        public void actionPerformed(ActionEvent Load) {
            File file = new File ("Score.txt");
            Scanner in = null;
            try {
                in = new Scanner(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String  score= in.nextLine();

        }
    }
    static class HardOption implements ActionListener {
        public void actionPerformed(ActionEvent Save) {

        }
    }
    static class EasyOption implements ActionListener {
        public void actionPerformed(ActionEvent Save) {



        }
    }
}