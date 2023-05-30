package com.example;

import com.example.repository.JdbcRepository;

public class Main {
    public static void main(String[] args) {

        JdbcRepository repository = new JdbcRepository();
        repository.getNumberOfPeople();

    }
}
