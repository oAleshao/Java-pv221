package itstep.learning.async;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class AsyncDemo {
    public void run(){
        //threadDemo();
        //taskDemo();
        percentDemo();
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
            TimeUnit.MICROSECONDS.sleep(300);

        }
        catch (InterruptedException e) {}
        return  10.0;
    }

    private void taskDemo(){
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
