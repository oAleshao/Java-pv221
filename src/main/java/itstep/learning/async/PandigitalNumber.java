package itstep.learning.async;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PandigitalNumber {
    private final ExecutorService pool = Executors.newFixedThreadPool(3);

    private String pandigitalNumber;
    private int counters;
    private final Object digitLocker = new Object();

    public void run(){
        counters = 10;
        Thread[] threads = new Thread[counters];
        for(int i = 0; i < counters; i++){
            threads[i] = new Thread(new PandigitalNumberRunnable(i));
            threads[i].start();
        }
        for(int i = 1; i <= threads.length; i++) {
            try {
                threads[i-1].join();
            }catch (InterruptedException ignore){}
        }
        System.out.printf("===================\nYour pandigital number: %s\n", pandigitalNumber);

    }


    private void SetNumber(int digit){

    }



    private class PandigitalNumberRunnable implements Runnable{
        int digit;

        public PandigitalNumberRunnable(int digit){
            this.digit = digit;
        }

        @Override
        public void run() {
            synchronized (digitLocker){
                if(pandigitalNumber == null){
                    pandigitalNumber = digit + "";
                }
                else {
                    pandigitalNumber = pandigitalNumber + digit;
                }
            }
        }
    }


}


