package hw03.task01_calculator;

public class ProgramTask01 {
    public static void main(String[] args){

        Double res1 = Calculator.sum(5, 3f);
        System.out.println(Calculator.printResult(res1));

        Double res2 = Calculator.multiply(res1, 2f);
        System.out.println(Calculator.printResult(res2));

        try {
            Double res3 = Calculator.divide(res2, 0);
            System.out.println(Calculator.printResult(res3));

        } catch (RuntimeException e){
            System.out.println(e);
        }

        Double res4 = Calculator.divide(res2, 4);
        System.out.println(Calculator.printResult(res4));

        Double res5 = Calculator.subtract(res4, 5.01);
        System.out.println(Calculator.printResult(res5));

    }
}
