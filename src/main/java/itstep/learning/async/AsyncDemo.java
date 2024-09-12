package itstep.learning.async;

import com.sun.org.apache.bcel.internal.Const;

import java.util.Objects;
import java.util.concurrent.*;

public class AsyncDemo {
   private final ExecutorService pool = Executors.newFixedThreadPool(3);

    public void run(){
        //threadDemo();
        System.out.println("Starting async thread");
        taskDemo();
        //percentDemo();
    }
    private void threadDemo(){
        // Thread - основний класс для роботи з потоками
        // У Java вводиться поняття функціонального інтерфейсу
        // типу даних з одним методом. Об'єкти функціональних
        // інтерфейсів грають роль функцій (не методів) і передаються
        // в інші об'єкти, які традиційно вимагають саме функції.
        Runnable runnable = new Runnable() {
            public void run() {
                System.out.println("Hello from Runnable");
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

        System.out.println("Hello from Main");

        try{ thread.join();}
        catch(InterruptedException e){}

        System.out.println("Hello from Main");

    }

    private double sum;
    private final Object sumLocker = new Object();
    private int threadsCountdown;
    private int countThreads;

    private void percentDemo(){
        sum = 100.0;
        threadsCountdown = 12;
        countThreads = 12;
        Thread[] threads = new Thread[threadsCountdown];
        for(int i = 1; i <= countThreads;i++) {
            threads[i-1] = new Thread(new PercentRunnable(i));
            threads[i-1].start();
        }
        for(int i = 1; i <= threads.length; i++) {
            try {
                threads[i-1].join();
            }catch (InterruptedException ignore){}
        }
        System.out.printf("===================\nTotal: %.3f\n", sum);

    }
    // Nested class - клас, описаний в іншому класі
    private class PercentRunnable implements Runnable{
        int month;

        public PercentRunnable(int month){
            this.month = month;
        }
        @Override
        public void run(){
            double factor = (1.0 + getPercent(month) / 100);
            double localSum;
            int localCountdown;
            synchronized (sumLocker) {
                sum = sum * factor;
                localSum = sum;
                localCountdown = --threadsCountdown;
            }
            if(localCountdown == 0) {
                System.out.printf("-------------\nTotal: %.3f\n", localSum);

            }
        }

    }

    private double getPercent(int month){
        try {
            TimeUnit.MICROSECONDS.sleep(5000);
        }
        catch (InterruptedException e) {}
        return  10.0;
    }

    private void taskDemo(){
        System.out.printf("Work task demo\n");
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                try {
                     TimeUnit.MILLISECONDS.sleep(1000);
                }catch (InterruptedException ignore){}
                return "Callable data";
            }
        };

        Callable<String> callable2 = () ->{
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            }catch (InterruptedException ignore){}
            return "Callable data";
        };

        Future<String> task1 = pool.submit(callable);
        Future<String> task2 = pool.submit(callable2);
        Future<Double> task3 = pool.submit(()->getPercent(1));

        try {
            String res1 = task1.get();
            System.out.println(res1);
            String res2 = task2.get();
            System.out.println(res2);
            double res3 = task3.get();
            System.out.println(res3);

            sum = 100.0;
            Future<Double>[] tasks = new Future[12];

            for(int i = 1; i <= 12;i++) {
                tasks[i-1] = pool.submit(new PercentCallable(i));
            }

            for(int i = 1; i <= 12;i++) {
                double percent = tasks[i-1].get();
                double factor = (1.0 + percent / 100);
                sum = sum * factor;
                System.out.printf("month: %d, sum: %.2f\n", i, sum);
            }
        }catch (InterruptedException | ExecutionException e){
            System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
        }



        pool.shutdown();
        try {pool.awaitTermination(15000, TimeUnit.MILLISECONDS);}
        catch (InterruptedException ignore){}
        pool.shutdownNow();

    }

    class PercentCallable implements Callable<Double>{
        private final int month;
        public PercentCallable(int month){
            this.month = month;
        }
        @Override
        public Double call() throws Exception {
            double percent = getPercent(month);
            return percent;
        }
    }

}

/*
* Асинхронне програмування
* Синхроне виконання коду - послідовне, один за одним.
*
*
* Реалізація асинхроності:
* - багатозадачність (об'єкти мови програмування / платформи)
* - багатопоточність (об'єкти операційної системи)
* - багатопроцесність (---//--- за наявності відміностей між потоком та процесом)
* - мережні технології (grid, network), у тому числі хмарні технології
*
*
* "Інфляція" - за відомими показниками місячних коефіцієнтів інфвляції
* порахувати річне значення.
* Мат.обгрунтування: чи однаковий результат операцій
* (100 + 10%) + 20% =?= (100 + 20%) + 10%
* 100 * 1.1 * 1.2 == 100 * 1.2 * 1.1
* можливість "переставлення" - ідеально для асинхроності - немає різниці
* у якій послідовності будуть враховані 12 місячних коефіцієнтів
*
* Pandigital - число, яке складається з усіх цифр (0-9), кожна один раз
* Задача: написати генератор Pandigital чисел на базі багатопоточності
*
* */
