package com.projects.tankgame;

/*
  @author CWT
 * @version 1.0
 敌方坦克
 */

import java.util.Vector;
@SuppressWarnings("all")
public class EnemyTank extends Tank implements Runnable {
    Mars mars;

    public EnemyTank(int x, int y) {
        super(x, y);
        setDirect(1);
        setSpeed(1);
        cnb = new Vector<>();
        //creatFire();  !!!!!!
    }
    public void setMars(Mars mars) {
        this.mars = mars;
    }

    //创建一颗炮弹
    public void creatFire() {
        Cannonball cannon = new Cannonball(getX(), getY(), getDirect());
        cnb.add(cannon);
        new Thread(cannon).start();
    }

    //将消亡的炮弹移除
    public void remove() {
        cnb.removeIf(cannonball -> !cannonball.isLive());
    }


    //判断坦克是否重叠
    public boolean Touch() {
        switch (this.getDirect()) {
            case 0://上
                for (EnemyTank enemyTank : enemy) {
                    if (!enemyTank.isLive || !this.isLive) continue;
                    if (enemyTank != this) {
                        //敌人的坦克分为两大类
                        //当敌人坦克为上下的时候，X的范围为[enemyTank.getX(),enemyTank.getX()+40]
                        //                     Y的范围为[enemyTank.getY(),enemyTank.getY()+60]
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
                        //当敌人坦克为左右的时候，X的范围为[enemyTank.getX(),enemyTank.getX()+60]
                        //                     Y的范围为[enemyTank.getY(),enemyTank.getY()+40]
                    }
                }
                if (mars.isLive && this.isLive) {
                    //当前坦克左上角(getX(),getY())
                    if (this.getX() > mars.getX()
                            && getX() < mars.getMX()
                            && getY() > mars.getY()
                            && getY() < mars.getMY()
                    )
                        return true;
                    //当前坦克右上角(getX()+40,getY())
                    if (getX() + 40 > mars.getX()
                            && getX() + 40 < mars.getMX()
                            && getY() > mars.getY()
                            && getY() < mars.getMY()
                    )
                        return true;
                }

                break;
            case 1://下
                for (EnemyTank enemyTank : enemy) {
                    if (!enemyTank.isLive || !this.isLive) continue;
                    if (enemyTank != this) {
                        //敌人的坦克分为两大类
                        //当敌人坦克为上下的时候，X的范围为[enemyTank.getX(),enemyTank.getX()+40]
                        //                     Y的范围为[enemyTank.getY(),enemyTank.getY()+60]

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
                    //当敌人坦克为左右的时候，X的范围为[enemyTank.getX(),enemyTank.getX()+60]
                    //                     Y的范围为[enemyTank.getY(),enemyTank.getY()+40]
                }
                if (mars.isLive && this.isLive) {
                    //当前坦克左下角(getX(),getY()+60)
                    if (this.getX() > mars.getX()
                            && getX() < mars.getMX()
                            && getY() + 60 > mars.getY()
                            && getY() + 60 < mars.getMY()
                    )
                        return true;
                    //当前坦克右下角(getX()+40,getY()+60)
                    if (getX() + 40 > mars.getX()
                            && getX() + 40 < mars.getMX()
                            && getY() + 60 > mars.getY()
                            && getY() + 60 < mars.getMY()
                    )
                        return true;
                }
                break;
            case 2://左
                for (EnemyTank enemyTank : enemy) {
                    if (!enemyTank.isLive || !this.isLive) continue;
                    if (enemyTank != this) {
                        //敌人的坦克分为两大类
                        //当敌人坦克为上下的时候，X的范围为[enemyTank.getX(),enemyTank.getX()+40]
                        //                     Y的范围为[enemyTank.getY(),enemyTank.getY()+60]
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
                    //当敌人坦克为左右的时候，X的范围为[enemyTank.getX(),enemyTank.getX()+60]
                    //                     Y的范围为[enemyTank.getY(),enemyTank.getY()+40]

                }
                if (mars.isLive && this.isLive) {
                    //当前坦克左上角(getX(),getY())
                    if (this.getX() > mars.getX()
                            && getX() < mars.getMX()
                            && getY() > mars.getY()
                            && getY() < mars.getMY()
                    )
                        return true;
                    //当前坦克左下角(getX(),getY()+40)
                    if (getX() > mars.getX()
                            && getX() < mars.getMX()
                            && getY() + 40 > mars.getY()
                            && getY() + 40 < mars.getMY()
                    )
                        return true;
                }
                break;
            case 3://右
                for (EnemyTank enemyTank : enemy) {
                    if (!enemyTank.isLive || !this.isLive) continue;
                    if (enemyTank != this) {
                        //敌人的坦克分为两大类
                        //当敌人坦克为上下的时候，X的范围为[enemyTank.getX(),enemyTank.getX()+40]
                        //                     Y的范围为[enemyTank.getY(),enemyTank.getY()+60]
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
                    //当敌人坦克为左右的时候，X的范围为[enemyTank.getX(),enemyTank.getX()+60]
                    //                     Y的范围为[enemyTank.getY(),enemyTank.getY()+40]

                }
                if (mars.isLive && this.isLive) {
                    //当前坦克右上角(getX()+60,getY())
                    if (this.getX() + 60 > mars.getX()
                            && getX() + 60 < mars.getMX()
                            && getY() > mars.getY()
                            && getY() < mars.getMY()
                    )
                        return true;
                    //当前坦克右下角(getX()+60,getY()+40)
                    if (getX() + 60 > mars.getX()
                            && getX() + 60 < mars.getMX()
                            && getY() + 40 > mars.getY()
                            && getY() + 40 < mars.getMY()
                    )
                        return true;
                }
                break;
        }
        return false;
    }


    @Override
    public void run() {
        while (isLive) {
            //限制敌方坦克能发射的炮弹数量
            if (cnb.size() < 2) {
                creatFire();
            }
            switch (getDirect()) {
                case 0:
                    //增强移动效果,只移动一个单位效果不够明显
                    for (int i = 1; i <= 30; i++) {
                        if (getY() > 0 && !Touch())
                            moveUp();

                        //休眠50毫秒
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    break;
                case 1:
                    for (int i = 1; i <= 30; i++) {
                        if (getY() + 60 < 750 && !Touch())
                            moveDown();

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2:
                    for (int i = 1; i <= 30; i++) {
                        if (getX() > 0 && !Touch())
                            moveLeft();

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3:
                    for (int i = 1; i <= 30; i++) {
                        if (getX() + 60 < 1000 && !Touch())
                            moveRight();

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
            }
            changeDir();
        }
        System.out.println("该坦克已被销毁！！！");
    }

    //随机改变坦克的方向
    public void changeDir() {
        int tem = (int) (Math.random() * 4);
        setDirect(tem);
    }
}
