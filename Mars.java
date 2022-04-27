package com.projects.tankgame;

/*
  @author CWT
 * @version 1.0
 自己的坦克
 */

import java.util.Vector;
@SuppressWarnings("all")
public class Mars extends Tank {

    public Mars(int x, int y, int direct) {
        super(x, y);
        cnb = new Vector<>();
        this.setDirect(direct);
        this.setSpeed(3);
    }

    //开炮
    public void fire() {
        //限制炮弹数量
        if (cnb.size() < 5) {
            Cannonball cannon = new Cannonball(getX(), getY(), getDirect());
            new Thread(cannon).start();
            cnb.add(cannon);
        }
    }

    public boolean go() {
        switch (getDirect()) {
            case 0:
                for (EnemyTank enemyTank : enemy) {
                    if (enemyTank.isLive && isLive) {
                        //当前坦克左上角(getX(),getY())
                        if (getX() > enemyTank.getX()
                                && getX() < enemyTank.getMX()
                                && getY() > enemyTank.getY()
                                && getY() < enemyTank.getMY()
                        )
                            return true;
                        //当前坦克右上角(getX()+40,getY())
                        if (getX() + 40 > enemyTank.getX()
                                && getX() + 40 < enemyTank.getMX()
                                && getY() > enemyTank.getY()
                                && getY() < enemyTank.getMY()
                        )
                            return true;
                    }
                }
                break;
            case 1:
                for (EnemyTank enemyTank : enemy) {
                    if (enemyTank.isLive && isLive) {
                        //当前坦克左下角(getX(),getY()+60)
                        if (getX() > enemyTank.getX()
                                && getX() < enemyTank.getMX()
                                && getY() + 60 > enemyTank.getY()
                                && getY() + 60 < enemyTank.getMY()
                        )
                            return true;
                        //当前坦克右下角(getX()+40,getY()+60)
                        if (getX() + 40 > enemyTank.getX()
                                && getX() + 40 < enemyTank.getMX()
                                && getY() + 60 > enemyTank.getY()
                                && getY() + 60 < enemyTank.getMY()
                        )
                            return true;
                    }
                }
                break;
            case 2:
                for (EnemyTank enemyTank : enemy) {
                    if (enemyTank.isLive && isLive) {
                        //当前坦克左上角(getX(),getY())
                        if (getX() > enemyTank.getX()
                                && getX() < enemyTank.getMX()
                                && getY() > enemyTank.getY()
                                && getY() < enemyTank.getMY()
                        )
                            return true;
                        //当前坦克左下角(getX(),getY()+40)
                        if (getX() > enemyTank.getX()
                                && getX() < enemyTank.getMX()
                                && getY() + 40 > enemyTank.getY()
                                && getY() + 40 < enemyTank.getMY()
                        )
                            return true;
                    }
                }
                break;
            case 3:
                for (EnemyTank enemyTank : enemy) {
                    if (enemyTank.isLive && isLive) {
                        //当前坦克右上角(getX()+60,getY())
                        if (getX() + 60 > enemyTank.getX()
                                && getX() + 60 < enemyTank.getMX()
                                && getY() > enemyTank.getY()
                                && getY() < enemyTank.getMY()
                        )
                            return true;
                        //当前坦克右下角(getX()+60,getY()+40)
                        if (getX() + 60 > enemyTank.getX()
                                && getX() + 60 < enemyTank.getMX()
                                && getY() + 40 > enemyTank.getY()
                                && getY() + 40 < enemyTank.getMY()
                        )
                            return true;
                    }
                }
                break;
        }
        return false;
    }

    //当炮弹消亡时移除
    public void remove() {
        cnb.removeIf(cannonball -> !cannonball.isLive());
    }
}
