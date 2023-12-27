package hw04.dmap;

import hw04.Persona;

import java.util.ArrayList;
import java.util.HashMap;

public class DirectoryMap {
    private HashMap<Integer, Persona> personaMap = new HashMap<>();



    /**
     * метод, который возвращает номер телефона сотрудника по имени (может быть список)
     * @param targetName -  искомое имя
     * @return возвращает список телефонов ArrayList<String>
     */
    public ArrayList<String> findPhoneNumFromPersonName(String targetName){
        ArrayList<String> findPersonPhoneNumList = new ArrayList<>();
        for (Persona p : personaMap.values()) {
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
        for (Persona p : personaMap.values()) {
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
    public Persona findPersonFromPersonalNumber(Integer targetPersonalNumber){
        if (personaMap.containsKey(targetPersonalNumber)) return personaMap.get(targetPersonalNumber);
        return null;
    }

    /**
     * метод добавления нового сотрудника в справочник
     * @param newPerson - новый сотрудник (плохо что если такой есть в справочнике - то будет ждать пока этот экземпляр удалит GC
     * @return true - если успешно добавлен
     */

    public boolean addNewPersona (Persona newPerson){
            if (findPersonFromPersonalNumber(newPerson.getPersonalNumber()) == null) { //если сотрудника с таким табельным номером нет - создаем нового и добавляем в справочник
                personaMap.put(newPerson.getPersonalNumber(), newPerson);
                return true;
            }

        return false;
    }

    public void printMap(){
        for(Integer key : personaMap.keySet()){
            System.out.println(personaMap.get(key));
        }
        System.out.println("-------------");
    }


}
