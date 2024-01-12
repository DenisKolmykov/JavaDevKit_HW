package hw05;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Table extends Thread{

    private List<Philosopher> philosophers;
    private Fork [] forks;
    private CountDownLatch cdl;

    public Table (int seats){
        cdl = new CountDownLatch(seats);
        forks = new Fork[seats];
        philosophers = new ArrayList<>();
        for (int i = 0; i < seats; i++) {
            philosophers.add(new Philosopher(String.format("Philosopher %d", i + 1), cdl));

            System.out.printf("%s уселся за стол\n", philosophers.get(i).getPhilosopherName());
            forks[i] = new Fork(i + 1);
        }
        for (int i = 0; i < seats; i++){
            int r = i - 1; // i-левая вилка, r-правая вилка
            if (r < 0) r = seats - 1;
            philosophers.get(i).setPhilosopherForks(forks[i], forks[r]);
        }
        System.out.println();
    }


    @Override
    public void run(){
        for (Philosopher p : philosophers){
            p.start();
        }
        try {
            cdl.await(); // ждем пока все философы полностью наедятся
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\nВСЕ ФИЛОСОФЫ ЗАКОНЧИЛИ ТРАПЕЗУ");
    }

// для проверки
//    public void print (){
//        for (Philosopher p : philosophers){
//            System.out.println(p.getPhilosopherName() + " " + p.getForkLeft().getForkNum() + " " + p.getForkRight().getForkNum());
//        }
//    }
}
