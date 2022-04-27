package com.projects.tankgame;

/*
  @author CWT
 * @version 1.0
 坦克
 */

import java.io.Serializable;
import java.util.Vector;

public class Tank implements Serializable {

    private int x; //坦克左上角横坐标
    private int y; //坦克左上角纵坐标
    private int direct; //记录坦克当前方向 0->向上  1->向下  2->向左  3->向右
    private int speed; //坦克移动的速度
    Vector<Cannonball> cnb; //坦克子弹集合
    boolean isLive = true; //坦克是否存活
    Vector<EnemyTank> enemy = new Vector<>();//存放敌方坦克

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setEnemy(Vector<EnemyTank> enemy) {
        this.enemy = enemy;
    }


    public int getMX() {
        if (direct == 0 || direct == 1)
            return x + 40;
        else
            return x + 60;
    }

    public int getMY() {
        if (direct == 0 || direct == 1)
            return y + 60;
        else
            return y + 40;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    //坦克向上移动
    public void moveUp() {
        setY(getY() - getSpeed());
    }

    //坦克向下移动
    public void moveDown() {
        setY(getY() + getSpeed());
    }

    //坦克向左移动
    public void moveLeft() {
        setX(getX() - getSpeed());
    }

    //坦克向右移动
    public void moveRight() {
        setX(getX() + getSpeed());
    }
}
