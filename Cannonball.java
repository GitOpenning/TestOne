package com.projects.tankgame;

/*
  @author CWT
 * @version 1.0
 炮弹
 */

import java.io.Serializable;

public class Cannonball implements Runnable, Serializable {
    private int x0; //炮弹的横坐标
    private int y0; //炮弹的纵坐标
    int direct; //根据坦克的方向确定坐标
    final int speed = 2; //炮弹移动的速度
    private boolean alive = true; //炮弹是否存活


    public int getX0() {
        return x0;
    }

    public int getY0() {
        return y0;
    }

    public boolean isLive() {
        return alive;
    }

    public void setLive(boolean live) {
        alive = live;
    }

    //根据坦克的信息确定炮弹的信息
    public Cannonball(int x, int y, int direct) {
        this.direct = direct;
        switch (direct) {
            case 0:
                x0 = x + 19;
                y0 = y - 3;
                break;
            case 1:
                x0 = x + 19;
                y0 = y + 57;
                break;
            case 2:
                x0 = x - 3;
                y0 = y + 20;
                break;
            case 3:
                x0 = x + 57;
                y0 = y + 20;
        }
    }

    @Override
    public void run() {
        //限制炮弹的移动范围
        while ((x0 >= -5 && x0 <= 1000) && ((y0 >= -5 && y0 <= 750))) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            shoot();
            //System.out.println("x: " + x0 + ",y: " + y0);
        }
        setLive(false);
//        System.out.print("x: " + x0 + ",y: " + y0);
//        System.out.println("导弹已爆炸~");
    }

    //炮弹发射即开始移动
    public void shoot() {
        switch (direct) {
            case 0:
                y0 -= speed;
                break;
            case 1:
                y0 += speed;
                break;
            case 2:
                x0 -= speed;
                break;
            case 3:
                x0 += speed;
        }
    }
}
