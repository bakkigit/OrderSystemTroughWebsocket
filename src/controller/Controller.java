package controller;

import model.Person;
import storage.Storage;

import java.util.ArrayList;

public class Controller {

    /**
     * Create a new Company.
     * Pre: name not empty, hours >= 0.
     */
    public static Person createPerson(String computerNavn,String Bestilling, double total) {
        Person person = new Person(computerNavn, Bestilling,total);
        Storage.storePersons(person);
        return person;
    }

    /**
     * Delete the company.
     * Pre: The company has no employees.
     */
    public static void deletePerson(Person person) {
        Storage.deletePerson(person);
    }


    public static ArrayList<Person> getPerson() {
        return Storage.getPersons();
    }

    // -------------------------------------------------------------------------

}
