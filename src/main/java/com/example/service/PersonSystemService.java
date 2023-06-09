package com.example.service;

import com.example.entity.Person;
import com.example.repository.JdbcRepository;

import java.util.regex.Pattern;

public class PersonSystemService {

    private JdbcRepository repository = new JdbcRepository();

    public void register(String firstName, String lastName) {

        if (Pattern.matches("[A-Z][a-z]*", firstName) &&
                Pattern.matches("[A-Z][a-z]*", lastName)) {
            Person person = new Person();
            person.setFirstName(firstName);
            person.setLastName(lastName);
            repository.save(person);
        } else {
            System.out.println("Invalid first name and last name.");
            System.exit(0);
        }

    }

}
