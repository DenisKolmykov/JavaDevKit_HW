package org.example;

import java.util.*;

/*
В качестве задачи предлагаю вам реализовать код для демонстрации парадокса Монти Холла (Парадокс Монти Холла — Википедия )
и наглядно убедиться в верности парадокса (запустить игру в цикле на 1000 и вывести итоговый счет).
Необходимо:
Создать свой Java Maven или Gradle проект;
Самостоятельно реализовать прикладную задачу;
Сохранить результат в HashMap<шаг теста, результат>
Вывести на экран статистику по победам и поражениям

Наиболее популярной является задача с дополнительным условием —
участнику игры заранее известны следующие правила :

- автомобиль равновероятно размещён за любой из трёх дверей;
- ведущий знает, где находится автомобиль;
- ведущий в любом случае обязан открыть дверь с козой (но не ту, которую выбрал игрок) и предложить игроку изменить выбор;

Для стратегии выигрыша важно следующее:
если вы меняете выбор двери после действий ведущего, то вы выигрываете,
если изначально выбрали проигрышную дверь.
Это произойдёт с вероятностью 2⁄3, так как изначально выбрать проигрышную дверь можно 2 способами из 3.
Иными словами, если вероятность того, что выигрыш за изначально выбранной дверью равна 1⁄3,
то вероятность обратного — 2⁄3, а ведущий устраняет неопределённость, не меняя при этом вероятность.
 */

public class Main {
    public static final Random random = new Random();

    public static void main(String[] args) {
        getStatistic (false); // после предложения ведущего игрок НЕ меняет выбор (false)
        System.out.println();
        getStatistic (true); // после предложения ведущего игрок меняет выбор (true)
    }

    private static void getStatistic (boolean changeChoice){
        Map<Integer, String> results = new HashMap<>();
        int testQuantity = 1000;
        for (int i = 1; i <= testQuantity; i++){
            results.put(i,game(changeChoice));
        }
//        System.out.println("Результаты тестов:");
//        printResultsMap(results);

        int countWin = Collections.frequency(results.values(), "win");
        int countFalse = Collections.frequency(results.values(), "false");

        System.out.printf("статистика когда игрок меняет/не меняет (%s) выбор:\n" +
                "всего шагов: %d, WIN: %d(%d%%), FALSE: %d(%d%%)\n",
                changeChoice, testQuantity, countWin,countWin*100/testQuantity, countFalse,countFalse*100/testQuantity);
    }

    private static String game(boolean changeChoice){
        int auto = random.nextInt(1,4);
        int playerChoice = random.nextInt(1,4) ;

        if (auto == playerChoice){
            if (!changeChoice){
                return "win";
            }
        } else {
            if (changeChoice){
                return "win";
            }
        }
        return "false";
    }


    private static void printResultsMap(Map<Integer, String> map){
        for(int k : map.keySet()){
            System.out.printf("test #%d: %s\n", k, map.get(k));
        }
    }
}