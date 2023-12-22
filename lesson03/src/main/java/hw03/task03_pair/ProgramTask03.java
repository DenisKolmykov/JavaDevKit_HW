package hw03.task03_pair;

public class ProgramTask03 {

    public static void main(String[] args) {
        Pair<String, Integer> task03 = new Pair<>("Первый", 2);
        System.out.println(task03.getFirst());
        System.out.println(task03.getSecond());
        System.out.println(task03);

        Pair task031 = new Pair<>(7f, 2);
        System.out.println(task031.getFirst());
        System.out.println(task031.getSecond());
        System.out.println(task031);
    }
}
