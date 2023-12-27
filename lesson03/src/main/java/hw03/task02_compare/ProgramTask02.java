package hw03.task02_compare;
/*
2. Напишите обобщенный метод compareArrays(),
который принимает два массива и возвращает true, если они одинаковые, и false в противном случае.
Массивы могут быть любого типа данных, но должны иметь одинаковую длину и содержать элементы одного типа по парно.
*/


public class ProgramTask02 {
    public static <T, U > boolean compareArrays (T [] firstArr, U [] secondArr){
        if (firstArr.length == secondArr.length){
            for (int i = 0; i < firstArr.length; i++){
//                System.out.println(firstArr[i].getClass().getName() + ", " + secondArr[i].getClass().getName());
                if (!firstArr[i].getClass().getName().equals(secondArr[i].getClass().getName()))
                    return false;
            }
            return true;
        }
        return false;
    }


    public static void main(String[] args) {

        Object [] arr1 = {1, 2, 3, 4};
        Object [] arr2 = {"5", "6", "7", "8"};

        Object [] arr3 = {1, 2, 3, 4};
        Object [] arr4 = {5, 6, "7", 8};

        Object [] arr5 = {1, 2, 3, 4};
        Object [] arr6 = {5, 6, 7};

        Object [] arr7 = {1, 2, 3, 4};
        Object [] arr8 = {5, 6, 7, 8};

        System.out.println("первая пара: " + compareArrays(arr1, arr2));
        System.out.println("вторая пара: " + compareArrays(arr3, arr4));
        System.out.println("третья пара: " + compareArrays(arr5, arr6));
        System.out.println("четвертая пара: " + compareArrays(arr7, arr8));
    }
}
