package com.projects.tankgame;

/*
  @author CWT
 * @version 1.0
 记录信息
 */

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Recorder {
    private static int score = 0;
    private static ObjectOutputStream oos = null;
    private static ObjectInputStream ois = null;
    private static final String filePath = "d:\\recorder.dat";
    private static MyPanel mp;

    public static void save() {
        try {
            oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(filePath)));
            oos.writeInt(score);
            oos.writeObject(mp);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void reBack() {
        try {
            ois = new ObjectInputStream(Files.newInputStream(Paths.get(filePath)));
            score = ois.readInt();
            Object o = ois.readObject();
            if (o instanceof MyPanel)
                mp = (MyPanel) o;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setMp(MyPanel mp) {
        Recorder.mp = mp;
    }

    public static MyPanel getMp() {
        return mp;
    }

    public static int getScore() {
        return score;
    }

    public static void addScore() {
        score++;
    }
}
