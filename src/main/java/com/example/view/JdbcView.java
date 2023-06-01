package com.example.view;

import com.example.service.JdbcService;

public class JdbcView {
    public static void main(String[] args) {

        JdbcService service = new JdbcService();

        System.out.println("1. Load All Data");
        System.out.println("========================");
        System.out.println("2. Load Data By Id");
        System.out.println("========================");
        System.out.println("3. Print Number of People");
        System.out.println("========================");
        System.out.println("4. Delete By Id");
        System.out.println("========================");
        System.out.println("5. Save Data");
        System.out.println("========================");
        System.out.println("6. Save a List of Data");
        System.out.println("========================");
        System.out.println("7. Load Data with Pagination");
        System.out.println("========================");
        System.out.println();
        System.out.print("Please select an option : ");

        service.selectOptions();

    }
}
