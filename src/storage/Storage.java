package storage;

import java.util.ArrayList;

import model.Person;

public class Storage {
    private static final ArrayList<Person> persons = new ArrayList<>();

    // -------------------------------------------------------------------------

    public static ArrayList<Person> getPersons() {
        return new ArrayList<>(persons);
    }

    public static void storePersons(Person person) {
        persons.add(person);
    }

    public static void deletePerson(Person person) {persons.remove(person);
    }

    // -------------------------------------------------------------------------

}
