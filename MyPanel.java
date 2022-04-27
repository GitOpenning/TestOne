package com.projects.tankgame;

/*
  @author CWT
 * @version 1.0
 展示面板
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener, Runnable, Serializable {
    //己方坦克
    Mars mars;
    //敌方坦克集合
    Vector<EnemyTank> enemy = new Vector<>();
    //定义一个Vector,用于演示爆炸效果
    Vector<Bomb> bombs = new Vector<>();
    //定义三张图片，显示爆炸效果
    transient Image image1; //大
    transient Image image2; //中
    transient Image image3; //小
    //定义敌人坦克的数量
    final int size = 3;

    public MyPanel() {
        //创建己方坦克
        mars = new Mars(100, 300, 1);
        mars.setEnemy(enemy);
        //创建敌方坦克
        for (int i = 0; i < size; i++) {
            EnemyTank en = new EnemyTank(100 * (i + 1), 0);
            en.setEnemy(enemy);
            en.setMars(mars);
            new Thread(en).start();
            enemy.add(en);
        }
    }

    public void init() {
        //初始化图片对象
        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/com/projects/tankgame/res/bomb_1.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/com/projects/tankgame/res/bomb_2.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/com/projects/tankgame/res/bomb_3.gif"));
        //播放指定的音乐
        new AePlayWave("src/com/projects/tankgame/res/111.wav").start();
    }

    //在面板上展示相关信息
    public void showInfo(Graphics g) {
        g.setColor(Color.BLACK);
        Font font = new Font("华文彩云", Font.BOLD, 35);
        g.setFont(font);
        g.drawString("坦克小游戏", 1050, 50);
        Font font1 = new Font("宋体", Font.BOLD, 25);
        g.setFont(font1);
        g.drawString("己方坦克", 1010, 150);
        drawTank(1170, 110, g, 0, 0);
        g.setColor(Color.BLACK);
        g.drawString("敌方坦克", 1010, 230);
        drawTank(1170, 190, g, 0, 1);
        g.setColor(Color.BLACK);
        g.drawString("歼敌数", 1010, 330);
        g.drawString(Recorder.getScore() + "", 1200, 330);
        drawTank(1120, 290, g, 0, 1);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //划分面板范围
        g.fillRect(0, 0, 1000, 750);
        //
        showInfo(g);
        //绘画己方坦克
        if (mars.isLive) {
            drawTank(mars.getX(), mars.getY(), g, mars.getDirect(), 0);
        }
        //将己方坦克消亡的炮弹移除
        mars.remove();
        //绘画己方坦克的炮弹
        for (Cannonball cannonball : mars.cnb) {
            g.draw3DRect(cannonball.getX0(), cannonball.getY0(), 1, 1, false);
            //判断己方炮弹是否击中敌方坦克
            if (cannonball.isLive()) {
                for (EnemyTank enemyTank : enemy) {
                    if (enemyTank.isLive) hitTank(enemyTank, cannonball);
                }
            }
        }
        //if (!mars.isLive && mars.cnb.size() == 0) mars = null;
        //绘画敌方坦克
        for (int i = 0; i < enemy.size(); i++) {
            EnemyTank eneTank = enemy.get(i);
            if (eneTank.isLive) {
                drawTank(eneTank.getX(), eneTank.getY(), g, eneTank.getDirect(), 1);
            }
            //将敌方坦克已经消亡的炮弹移除
            eneTank.remove();
            //当敌方坦克炮弹数量为0且已被击中时,将其移除
            if (eneTank.cnb.size() == 0 && !eneTank.isLive) {
                enemy.remove(eneTank);
            }
            //绘画敌方坦克炮弹
            for (int j = 0; j < eneTank.cnb.size(); j++) {
                Cannonball cannonball = eneTank.cnb.get(j);
                g.setColor(Color.yellow);
                g.draw3DRect(cannonball.getX0(), cannonball.getY0(), 1, 1, false);
                //判断敌方坦克炮弹是否击中己方坦克
                if (cannonball.isLive() && mars.isLive)
                    hitTank(mars, cannonball);
            }
        }
        //如果bombs集合不为空，则依次显示图片
        for (int i = 0; i < bombs.size(); i++) {
            //取出对象
            Bomb bomb = bombs.get(i);
            //根据对象的life值显示对应的图片
            if (bomb.life > 6) {
                g.drawImage(image3, bomb.x, bomb.y, 60, 60, this);
            } else if (bomb.life > 3) {
                g.drawImage(image2, bomb.x, bomb.y, 60, 60, this);
            } else {
                g.drawImage(image1, bomb.x, bomb.y, 60, 60, this);
            }
            //让对象生命值减少
            bomb.lifeDown();
            //当对象生命值为0时，表示显示完毕，可以将其移除
            if (!bomb.isLive) bombs.remove(bomb);
        }

    }


    //判断子弹击中坦克
    public void hitTank(Tank tank, Cannonball cnb) {
        switch (tank.getDirect()) {
            case 0:
            case 1:
                if (cnb.getX0() > tank.getX() && cnb.getX0() < tank.getX() + 40
                        && cnb.getY0() > tank.getY() && cnb.getY0() < tank.getY() + 60) {
                    //炮弹与坦克一起消亡
                    cnb.setLive(false);
                    tank.isLive = false;
                    //记录歼敌数
                    if (tank instanceof EnemyTank) {
                        Recorder.addScore();
                    }
                    //当子弹击中坦克时，创建一个Bomb对象,并添加至bombs集合中
                    bombs.add(new Bomb(tank.getX(), tank.getY()));
                }
                break;
            case 2:
            case 3:
                if (cnb.getX0() > tank.getX() && cnb.getX0() < tank.getX() + 60
                        && cnb.getY0() > tank.getY() && cnb.getY0() < tank.getY() + 40) {
                    //炮弹与坦克一起消亡
                    cnb.setLive(false);
                    tank.isLive = false;
                    //记录歼敌数
                    if (tank instanceof EnemyTank) {
                        Recorder.addScore();
                    }
                    //当子弹击中坦克时，创建一个Bomb对象,并添加至bombs集合中
                    bombs.add(new Bomb(tank.getX(), tank.getY()));
                }
                break;
        }
    }


    /**
     * @param x      坦克的左上顶点横坐标
     * @param y      坦克的坐上顶点纵坐标
     * @param g      画笔
     * @param direct 表示坦克的方向
     * @param type   表示我的坦克还是||敌方坦克
     */

    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        //用于辨别敌我坦克
        switch (type) {
            case 0:
                g.setColor(Color.cyan);
                break;
            case 1:
                g.setColor(Color.yellow);
                break;
            default:
                System.out.println("暂无此类类型的坦克");
        }
        //绘制坦克
        switch (direct) {
            //向上
            case 0:
                g.fill3DRect(x, y, 10, 60, false);//坦克的左轮
                g.fill3DRect(x + 30, y, 10, 60, false);//坦克的右轮
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//坦克的盖子
                g.fillOval(x + 10, y + 20, 20, 20);//坦克的圆盖
                g.drawLine(x + 20, y + 30, x + 20, y);//坦克的炮筒
                break;
            //向下
            case 1:
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);//坦克的右轮
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//坦克的盖子
                g.fillOval(x + 10, y + 20, 20, 20);//坦克的圆盖
                g.drawLine(x + 20, y + 30, x + 20, y + 60);//坦克的炮筒
                break;
            //向左
            case 2:
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);//坦克的右轮
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//坦克的盖子
                g.fillOval(x + 20, y + 10, 20, 20);//坦克的圆盖
                g.drawLine(x + 30, y + 20, x, y + 20);//坦克的炮筒
                break;
            //向右
            case 3:
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);//坦克的右轮
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//坦克的盖子
                g.fillOval(x + 20, y + 10, 20, 20);//坦克的圆盖
                g.drawLine(x + 30, y + 20, x + 60, y + 20);//坦克的炮筒
                break;
            default:
                System.out.println("坦克的方向只能为上下左右");
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                mars.setDirect(0);
                if (mars.getY() > 0 && !mars.go())
                    mars.moveUp();

                break;
            case KeyEvent.VK_A:
                mars.setDirect(2);
                if (mars.getX() > 0 && !mars.go())
                    mars.moveLeft();

                break;
            case KeyEvent.VK_D:
                mars.setDirect(3);
                if (mars.getX() + 80 < 1000 && !mars.go())
                    mars.moveRight();

                break;
            case KeyEvent.VK_S:
                mars.setDirect(1);
                if (mars.getY() + 100 < 750 && !mars.go())
                    mars.moveDown();

                break;
            case KeyEvent.VK_J:
                mars.fire();
                break;
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //刷新面板
            this.repaint();
        }
    }
}
