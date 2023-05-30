package com.example.service;

import com.example.entity.Person;
import com.example.repository.JdbcRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JdbcService {

    public void selectOptions() {
        JdbcRepository repository = new JdbcRepository();

        Scanner input = new Scanner(System.in);
        int option = input.nextInt();

        switch (option) {

            case 1:
                List<Person> people = repository.loadAll();
                for (Person person : people)
                    System.out.println(person.getId() + " : " +
                            person.getFirstName() + " " + person.getLastName());
                break;

            case 2:
                System.out.print("Enter the id : ");
                Long userId = input.nextLong();
                System.out.println(repository.loadById(userId).getId() + " : " +
                        repository.loadById(userId).getFirstName() + " " +
                        repository.loadById(userId).getLastName());
                break;

            case 3:
                repository.getNumberOfPeople();
                break;

            case 4:
                System.out.print("Enter the id you want to delete : ");
                Long deleteId = input.nextLong();
                repository.deleteById(deleteId);
                System.out.println("Person got deleted successfully.");
                break;

            case 5:
                System.out.print("Enter new first name : ");
                String firstName = input.next();
                System.out.print("Enter new last name : ");
                String lastName = input.next();
                Person newPerson = new Person();
                newPerson.setFirstName(firstName);
                newPerson.setLastName(lastName);
                repository.save(newPerson);
                System.out.println("New person added successfully.");
                break;

            case 6:
                System.out.print("How many people do you want to add : ");
                int size = input.nextInt();
                List<Person> personArrayList = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    System.out.print("Enter first name : ");
                    String fName = input.next();
                    System.out.print("Enter last name : ");
                    String lName = input.next();
                    Person person = new Person();
                    person.setFirstName(fName);
                    person.setLastName(lName);
                    personArrayList.add(person);
                    repository.saveAll(personArrayList);
                }
                System.out.println("People added successfully.");
                break;
        }
    }

}
