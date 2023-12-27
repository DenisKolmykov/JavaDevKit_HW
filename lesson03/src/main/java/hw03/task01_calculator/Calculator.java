package hw03.task01_calculator;
/*
1. Написать класс Калькулятор (необобщенный),
который содержит обобщенные статические методы: sum(), multiply(), divide(), subtract().
Параметры этих методов – два числа разного типа, над которыми должна быть произведена операция.
 */
public class Calculator {

// можно сделать через один параметр Т (в этом случае компилятор будет подписрать подходящий тип)
    // можно через два пареметра T и U - так тоже правильно и для компилятора проще
    public static <T extends Number> Double sum( T a, T b){
        return a.doubleValue() + b.doubleValue();
    }
    public static <T extends Number> Double multiply(T a, T b){
        return a.doubleValue() * b.doubleValue();
    }

    public static <T extends Number> Double divide(T a, T b){
        if (b.doubleValue() != 0){
            return a.doubleValue() / b.doubleValue();
        }
        throw new RuntimeException("Вы пытаетесь делить на ноль");
    }

    public static <T extends Number,  U extends Number> Double subtract(T a, U b){
        return a.doubleValue() - b.doubleValue();
    }

    public static String printResult(Double result){
        return String.format("результат мат.операции: %.2f", result);
    }
}
