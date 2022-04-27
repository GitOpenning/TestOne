package com.projects.tankgame;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/*
  @author CWT
 * @version 1.0
 */
public class AePlayWave extends Thread {
    private final String filename;

    public AePlayWave(String wavFile) {
        filename = wavFile;
    }

    public void run() {

        File soundFile = new File(filename);

        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
        } catch (Exception e1) {
            e1.printStackTrace();
            return;
        }

        AudioFormat format = audioInputStream.getFormat();
        SourceDataLine auLine = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

        try {
            auLine = (SourceDataLine) AudioSystem.getLine(info);
            auLine.open(format);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        auLine.start();
        int nBytesRead = 0;
        //这是缓冲
        byte[] abData = new byte[512];

        try {
            while (nBytesRead != -1) {
                nBytesRead = audioInputStream.read(abData, 0, abData.length);
                if (nBytesRead >= 0)
                    auLine.write(abData, 0, nBytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            auLine.drain();
            auLine.close();
        }

    }
}
