package br.com.edulcs;

import java.util.*;

public class DinerThread extends Thread {
    public static final int numeroDeFilosofos = 6;
    public static Object[] listOfLocks = new Object[numeroDeFilosofos];
    public static char[] mesa = new char[4* numeroDeFilosofos];
    public static char[] lockedDiner = new char[4* numeroDeFilosofos];
    public static Random randomGenerator = new Random();
    public static int tempo = 150;
    private int threadIndex;
    public static void main(String[] a) {
        for (int i = 0; i< numeroDeFilosofos; i++) listOfLocks[i] =
                new Object();
        for (int i = 0; i< numeroDeFilosofos; i++) {
            mesa[4*i] = '|';
            mesa[4*i+1] = ' ';
            mesa[4*i+2] = '-';
            mesa[4*i+3] = ' ';
            lockedDiner[4*i] = ' ';
            lockedDiner[4*i+1] = '|';
            lockedDiner[4*i+2] = '=';
            lockedDiner[4*i+3] = ' ';
        }
        for (int i = 0; i< numeroDeFilosofos; i++) {
            Thread t = new DinerThread(i);
            t.setDaemon(true);
            t.start();
        }
        String lockedString = new String(lockedDiner);
        System.out.println("Mesa de jantar: ");
        long step = 0;
        while (true) {
            step++;
            System.out.println((new String(mesa))+"   "+step);
            if (lockedString.equals(new String(mesa)))
                break;
            try {
                Thread.sleep(tempo);
            } catch (InterruptedException e) {
                System.out.println("Interrupted.");
            }
        }
        System.out.println("The diner is locked.");
    }
    public DinerThread(int i) {
        threadIndex = i;
    }
    public void run() {
        while (!isInterrupted()) {
            try {
                sleep(tempo *randomGenerator.nextInt(5));
            } catch (InterruptedException e) {
                break;
            }
            // teantando pegar o garfo da esquerda
            Object leftLock = listOfLocks[threadIndex];
            synchronized (leftLock) {
                int i = 4*threadIndex;
                mesa[i] = ' ';
                mesa[i+1] = '|';
                mesa[i+2] = '=';
                try {
                    sleep(tempo *1);
                } catch (InterruptedException e) {
                    break;
                }
                // teantando pegar o garfo da direita
                Object rightLock =
                        listOfLocks[(threadIndex+1)% numeroDeFilosofos];
                synchronized (rightLock) {
                    mesa[i+2] = 'o';
                    mesa[i+3] = '|';
                    mesa[(i+4)%(4* numeroDeFilosofos)] = ' ';
                    try {
                        sleep(tempo *1);
                    } catch (InterruptedException e) {
                        break;
                    }
                    mesa[i] = '|';
                    mesa[i+1] = ' ';
                    mesa[i+2] = '-';
                    mesa[i+3] = ' ';
                    mesa[(i+4)%(4* numeroDeFilosofos)] = '|';
                }
            }
        }
    }
}