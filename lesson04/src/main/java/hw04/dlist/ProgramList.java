package hw04.dlist;
/*
Урок 4. Коллекции
Создать справочник сотрудников
Необходимо:
Создать класс справочник сотрудников, который содержит внутри
коллекцию сотрудников - каждый сотрудник должен иметь следующие атрибуты:
Табельный номер
Номер телефона
Имя
Стаж
Добавить метод, который ищет сотрудника по стажу (может быть список)
Добавить метод, который возвращает номер телефона сотрудника по имени (может быть список)
Добавить метод, который ищет сотрудника по табельному номеру
Добавить метод добавление нового сотрудника в справочник
 */


import hw04.Persona;

import java.util.ArrayList;
import java.util.List;

public class ProgramList {
    public static void main(String[] args) {
        Persona p1 = new Persona(1, "6733213","Ivan", 23);
        Persona p2 = new Persona(2, "124574213","Petr", 3);
        Persona p3 = new Persona(3, "9823213","Igor", 2);
        Persona p4 = new Persona(4, "233213","Fedor", 15);
        Persona p5 = new Persona(5, "34533213","Stepan", 4);
        Persona p6 = new Persona(6, "89533254","Igor", 9);

        List<Persona> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);
        list.add(p6);

        //создаем справочник сотрудников
        DirectoryList employeeDirectory = new DirectoryList(list);
        employeeDirectory.printList();


        // поиск сотрудников по стажу (все больше или равно целевого)
        Integer targetExperience = 4;
        System.out.printf("Сотрудники, у которых стаж больше или равен %d:\n", targetExperience);
        if (!employeeDirectory.findPersonFromExperience(targetExperience).isEmpty()) {
            for (Persona p : employeeDirectory.findPersonFromExperience(targetExperience)) {
                System.out.println(p);
            }
        } else {
            System.out.println("!не найдено");
        }
        System.out.println("------------");

        // возвращает номер телефона сотрудника по имени (может быть список)
        String targetName = "Igor";
        System.out.printf("Номера телефонов сотрудников с именем %s:\n", targetName);
        if (!employeeDirectory.findPhoneNumFromPersonName(targetName).isEmpty()) {
            for (String phoneNums : employeeDirectory.findPhoneNumFromPersonName(targetName)) {
                System.out.println(phoneNums);
            }
        }else{
            System.out.println("!не найдено");
        }
        System.out.println("------------");


        // поиск по табельному номеру
        int targetPersonalNumber = 4;
        if (employeeDirectory.findPerson(targetPersonalNumber) != null){
            System.out.printf("найден сотрудник: %s\n",employeeDirectory.findPerson(targetPersonalNumber));
        } else {
            System.out.printf("Сотрудник с таб. №: %d не найден\n", targetPersonalNumber);
        }
        System.out.println("------------");

        // добавляем нового сотрудника
        Integer newPersonPersonalNumber = 8;
        String newPersonPhoneNumber = "910392834646";
        String newPersonName = "newEmployee";
        Integer newPersonExperience = 1;
        if (employeeDirectory.addNewPersona(newPersonPersonalNumber, newPersonPhoneNumber,newPersonName, newPersonExperience)){
            System.out.printf("Добавлен сотрудник: %s\n", employeeDirectory.findPerson(newPersonPersonalNumber));
            employeeDirectory.printList();
        } else {
            System.out.printf("сотрудник с таб.№ %d уже есть: %s\n", newPersonPersonalNumber, employeeDirectory.findPerson(newPersonPersonalNumber));
        }
    }
}