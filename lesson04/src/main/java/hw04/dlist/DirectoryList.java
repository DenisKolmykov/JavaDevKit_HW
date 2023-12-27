package hw04.dlist;
/*
Добавить метод, который ищет сотрудника по стажу (может быть список)
Добавить метод, который возвращает номер телефона сотрудника по имени (может быть список)
Добавить метод, который ищет сотрудника по табельному номеру
Добавить метод добавление нового сотрудника в справочник
 */


import hw04.Persona;

import java.util.ArrayList;
import java.util.List;

public class DirectoryList {
    private List<Persona> personaList;

    public DirectoryList(List<Persona> personaList){
        this.personaList = personaList;
    }

    /**
     * метод, который возвращает номер телефона сотрудника по имени (может быть список)
     * @param targetName -  искомое имя
     * @return возвращает список телефонов ArrayList<String>
     */
    public ArrayList<String> findPhoneNumFromPersonName(String targetName){
        ArrayList<String> findPersonPhoneNumList = new ArrayList<>();
        for (Persona p : personaList) {
            if (p.getName().equals(targetName)){
                findPersonPhoneNumList.add(p.getPhoneNumber());
            }
        }
        return findPersonPhoneNumList;
    }

    /**
     * метод, который ищет сотрудника по стажу (может быть список)
     * @param targetExperience -  стаж по которому ищем
     * @return возращает ArrayList<Persona>
     */
    public ArrayList<Persona> findPersonFromExperience(Integer targetExperience){
        ArrayList<Persona> findPersonList = new ArrayList<>();
        for (Persona p : personaList) {
            if (p.getExperience() >= targetExperience){
                findPersonList.add(p);
            }
        }
        return findPersonList;
    }


    /**
     * метод поиска сотрудника по табельному номеру
     * @param targetPersonalNumber - искомый таб. номер
     * @return если найден - возвращаем Persona, если нет - null
     */
    public Persona findPerson(Integer targetPersonalNumber){
        for (Persona p : personaList){
            if (p.getPersonalNumber() == targetPersonalNumber){
                return p;
            }
        }
        return null;
    }

    /**
     * метод добавления нового сотрудника
     * @param newPersonPersonalNumber - табельный номер нового сотрудника
     * @param newPersonPhoneNumber  - телефон нового сотрудника
     * @param newPersonName - имя нового сотрудника
     * @param newPersonExperience - стаж нового сотрудника
     * @return true - если создан новый сотрудник и добавлен в стправочник
     */
    public boolean addNewPersona (Integer newPersonPersonalNumber, String newPersonPhoneNumber, String newPersonName, Integer newPersonExperience){
        if (findPerson(newPersonPersonalNumber) == null) { //если сотрудника с таким табельным номером нет - создаем нового и добавляем в справочник
            personaList.add(new Persona(newPersonPersonalNumber, newPersonPhoneNumber, newPersonName, newPersonExperience));
            return true;
        }
        return false;
    }

    public void printList(){
        for (Persona p : personaList){
            System.out.println(p);
        }
        System.out.println("-------------");
    }


}
