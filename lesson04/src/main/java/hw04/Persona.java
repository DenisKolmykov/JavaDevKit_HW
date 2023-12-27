package hw04;
/*
каждый сотрудник должен иметь следующие атрибуты:
Табельный номер
Номер телефона
Имя
Стаж
 */


public class Persona implements Comparable<Persona>{
    private Integer personalNumber;
    private String phoneNumber;
    private String name;
    private Integer experience;

    public Persona (Integer personalNumber, String phoneNumber, String name, Integer experience){
        this.personalNumber = personalNumber;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.experience = experience;
    }

    public Integer getPersonalNumber() {
        return personalNumber;
    }

    public Integer getExperience() {
        return experience;
    }

    public String getName() {
        return name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // переопределям compareTo для сравнения по табельному номеру (при добавлении в справочник)
    @Override
    public int compareTo(Persona o) {
        return Integer.compare(this.personalNumber, o.getPersonalNumber());
    }


    @Override
    public String toString(){
        return String.format("таб №: %d, Имя: %s, телефон: %s, стаж: %s",personalNumber, name, phoneNumber, experience);
    }

}
