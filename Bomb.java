package com.projects.tankgame;

/*
  @author CWT
 * @version 1.0
 爆炸效果(展示图片)
 */

public class Bomb {
    int x; //图片左上横坐标
    int y; //图片右上纵坐标
    int life = 9; //生命周期
    boolean isLive = true; //是否存活

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }
    //减少生命值(用于控制每张图片的展示时长)
    public void lifeDown() {
        if (life > 0) {
            life--;
        } else {
            isLive = false;
        }
    }
}
