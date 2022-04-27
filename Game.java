package com.projects.tankgame;

/*
  @author CWT
 * @version 1.0
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Game extends JFrame {
    MyPanel mp;

    public Game() {
        JLabel jl;
        JButton b1, b2;
        jl = new JLabel("坦克大战");
        jl.setFont(new Font("华文彩云", Font.BOLD, 50));
        b1 = new JButton("新游戏");
        b1.setFont(new Font("宋体", Font.BOLD, 16));
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Game("1");
                dispose();
            }
        });
        b2 = new JButton("继续上局");
        b2.setFont(new Font("宋体", Font.BOLD, 16));
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Game("2");
                dispose();
            }
        });
        jl.setBounds(95, 35, 250, 50);
        b1.setBounds(100, 165, 90, 30);
        b2.setBounds(200, 165, 110, 30);
        this.setSize(400, 300);
        this.setTitle("欢迎光临坦克大战小游戏");
        this.setLayout(null);
        this.add(jl);
        this.add(b1);
        this.add(b2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public Game(String str) {
        if ("1".equals(str)) {
            mp = new MyPanel();
            Recorder.setMp(mp);
        } else if ("2".equals(str)) {
            Recorder.reBack();
            mp = Recorder.getMp();
            for (int i = 0; i < mp.enemy.size(); i++) {
                EnemyTank en = mp.enemy.get(i);
                if (en.isLive) new Thread(en).start();
                for (Cannonball cannonball : en.cnb) {
                    new Thread(cannonball).start();
                }
            }
            for (Cannonball cnb : mp.mars.cnb) {
                new Thread(cnb).start();
            }
        } else {
            System.out.println("--------");
            return;
        }
        mp.init();
        new Thread(mp).start();
        this.add(mp);
        this.setSize(1300, 750);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.save();
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        new Game();
    }
}
