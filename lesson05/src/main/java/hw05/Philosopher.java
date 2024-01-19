package hw05;
/*
Во первых синхронизацию вы точно использовали неверно) нет смысла накладывать синхронизацию на методы философа.
Так как методы каждого философа вызываются только в одном потоке)
то есть философ только сам вызывает свои методы и синхронизация ничего не дает.
Ну и во вторых я думаю, что может быть ситуация, когда два философа проверяют вилки почти одновременно,
убеждаются, что они свободны и потом сначала один поток ставит их в состояние фолс,
а потом второй ставит их в состояние фолс и никаких проблем с точки зрения кода нет.
Главное, чтобы оба прошли проверку до того, как один из них поменяет состояние вилок

корректировка - убираем синхронизацию здесь в методе run, ставим синхронизацию в классе Fork на метод set
 */
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Philosopher extends Thread{
    private String name;
    private Fork forkLeft;
    private Fork forkRight;
    private long thinkingTime;
    private long eatingTime;

    private CountDownLatch cdl;
    private Random random;
    private int countEating; // сколько раз надо поесть чтоб наесться

    public Philosopher (String name, CountDownLatch cdl){
        this.name = name;
        this.countEating = 3; // при необходимости можно прописать и в классе Table и пердавать это значание (при создании экземпляра класса Philosopher)
        this.cdl = cdl;
        random = new Random();
        eatingTime = (long) random.nextDouble(1, 5) * 1000;
        thinkingTime = (long) random.nextDouble(2, 7) * 1000;

    }

    public String getPhilosopherName() {
        return name;
    }

    public void setPhilosopherForks(Fork forkLeft, Fork forkRight){
        this.forkLeft = forkLeft;
        this.forkRight = forkRight;
    }

    @Override
    public void run(){
        try {
            thinking();
            int count = countEating;
            while(countEating > 0){
                eating(count);
            }
            cdl.countDown();
            System.out.printf("%s ПОЛНОСТЬЮ НАЕЛСЯ И ЗАКОНЧИЛ ТРАПЕЗУ\n", name);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void thinking() throws InterruptedException {
        System.out.printf("%s погрузился в размышления на %d сек\n", name, thinkingTime/1000);
        sleep(thinkingTime);

        System.out.printf("\n%s закончил размышлять\n", name);
    }

    public void eating(int count) throws InterruptedException {
        System.out.printf("%s собирается поесть %d/%d раз\n",name, count - countEating + 1, count);
//        System.out.printf("вилки №%d-%s,№%d-%s\n",forkLeft.getForkNum(),forkLeft.getForkStatus(), forkRight.getForkNum(),forkRight.getForkStatus()); //для проверки
        if (forkLeft.getForkStatus().get() && forkRight.getForkStatus().get()) {
            forkLeft.setForkStatus(false);
            forkRight.setForkStatus(false);
            System.out.printf("%s взял вилки №%d и №%d и начал кушать %d/%d раз\n\n", name, forkLeft.getForkNum(), forkRight.getForkNum(), count - countEating + 1, count); // должен взять ОДНОВРЕМЕННО две вилки

            sleep(eatingTime);

            forkLeft.setForkStatus(true);
            forkRight.setForkStatus(true);
            System.out.printf("\n%s поел %d/%d раз за %d сек и положил вилки №%d и №%d на стол\n", name, count - countEating + 1, count, eatingTime/1000, forkLeft.getForkNum(), forkRight.getForkNum());
//            System.out.printf("вилки №%d-%s,№%d-%s\n", forkLeft.getForkNum(), forkLeft.getForkStatus(), forkRight.getForkNum(), forkRight.getForkStatus()); // //для проверки
            this.countEating--;
        } else {
            System.out.printf("для %s нет 2x свободных вилок: №%d-%s и №%d-%s\n", name, forkLeft.getForkNum(), forkLeft.getForkStatus(), forkRight.getForkNum(), forkRight.getForkStatus());
        }
        if (countEating > 0) {
            thinking();
        }
    }
}

