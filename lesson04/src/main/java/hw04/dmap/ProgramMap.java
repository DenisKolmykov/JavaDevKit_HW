package hw04.dmap;
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

public class ProgramMap {
    //создаем справочник сотрудников
    public static final DirectoryMap employeeDirectory = new DirectoryMap();

    public static void main(String[] args) {
        Persona p1 = new Persona(1, "6733213","Ivan", 23);
        Persona p2 = new Persona(2, "124574213","Petr", 3);
        Persona p3 = new Persona(3, "9823213","Igor", 2);
        Persona p4 = new Persona(4, "233213","Fedor", 15);
        Persona p5 = new Persona(5, "34533213","Stepan", 9);
        Persona p6 = new Persona(6, "89533254","Igor", 9);



        employeeDirectory.addNewPersona(p1);
        employeeDirectory.addNewPersona(p2);
        employeeDirectory.addNewPersona(p3);
        employeeDirectory.addNewPersona(p4);
        employeeDirectory.addNewPersona(p5);
        employeeDirectory.addNewPersona(p6);

        employeeDirectory.printMap();

        // поиск сотрудников по стажу (все больше или равно целевого)
        int targetExperience = 25;
        findEmployeeFromExperience(targetExperience);

        int targetExperience2 = 4;
        findEmployeeFromExperience(targetExperience2);

        // возвращает номер телефона сотрудника по имени (может быть список)
        String targetName = "Den";
        findPhoneNumsFromEmployeeName (targetName);

        String targetName2 = "Igor";
        findPhoneNumsFromEmployeeName (targetName2);

        // поиск по табельному номеру
        int targetPersonalNumber = 8;
        findEmployeePersonalNumber(targetPersonalNumber);

        int targetPersonalNumber2 = 5;
        findEmployeePersonalNumber(targetPersonalNumber2);

        // добавляем нового сотрудника (плохо что если такой есть в справочнике - то будет ждать пока этот экземпляр удалит GC)
        Persona newPerson = new Persona(3,"910392834646", "newEmployee", 1);
        addNewEmployee(newPerson);
        Persona newPerson2 = new Persona(7,"910392834646", "newEmployee", 1);
        addNewEmployee(newPerson2);
    }


    // метод поиск сотрудников по стажу (все больше или равно целевого)
    public static void findEmployeeFromExperience(int targetExperience){
        System.out.printf("Сотрудники, у которых стаж больше или равен %d:\n", targetExperience);
        if (!employeeDirectory.findPersonFromExperience(targetExperience).isEmpty()) {
            for (Persona p : employeeDirectory.findPersonFromExperience(targetExperience)) {
                System.out.println(p);
            }
        } else {
            System.out.println("!не найдено");
        }
        System.out.println("------------");
    }


    // метод возвращает номер телефона сотрудника по имени (может быть список)
    public static void findPhoneNumsFromEmployeeName(String targetName){
        System.out.printf("Номера телефонов сотрудников с именем %s:\n", targetName);
        if (!employeeDirectory.findPhoneNumFromPersonName(targetName).isEmpty()) {
            for (String phoneNums : employeeDirectory.findPhoneNumFromPersonName(targetName)) {
                System.out.println(phoneNums);
            }
        }else{
            System.out.println("!не найдено");
        }
        System.out.println("------------");
    }

    // метод поиск по табельному номеру
    public static void findEmployeePersonalNumber(int targetPersonalNumber){
        System.out.printf("Поиск сотрудника с таб.№ %d:\n", targetPersonalNumber);
        if (employeeDirectory.findPersonFromPersonalNumber(targetPersonalNumber) != null){
            System.out.printf("найден сотрудник: %s\n",employeeDirectory.findPersonFromPersonalNumber(targetPersonalNumber));
        } else {
            System.out.printf("Сотрудник с таб. №: %d не найден\n", targetPersonalNumber);
        }
        System.out.println("------------");
    }

    // метод добавляем нового сотрудника
    public static void addNewEmployee(Persona newPerson){
        if (employeeDirectory.addNewPersona(newPerson)){
            System.out.printf("Добавлен сотрудник: %s\n", newPerson);
            employeeDirectory.printMap();
        } else {
            System.out.printf("!попытка добавить сотрудника: %s\n",newPerson);
            System.out.printf("сотрудник с таб.№ %d уже есть: %s\n", newPerson.getPersonalNumber(), employeeDirectory.findPersonFromPersonalNumber(newPerson.getPersonalNumber()));
            System.out.println("------------");
        }
    }
}
