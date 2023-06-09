package com.example.view;

public class Main {
    public static void main(String[] args) {

        JdbcView service = new JdbcView();

        System.out.println("1. Load All Data");
        System.out.println("========================");
        System.out.println("2. Load All Data With Array");
        System.out.println("========================");
        System.out.println("3. Load Data By Id");
        System.out.println("========================");
        System.out.println("4. Print Number of People");
        System.out.println("========================");
        System.out.println("5. Delete By Id");
        System.out.println("========================");
        System.out.println("6. Save Data");
        System.out.println("========================");
        System.out.println("7. Save a List of Data");
        System.out.println("========================");
        System.out.println("8. Load Data with Pagination");
        System.out.println("========================");
        System.out.println("9. Exit");
        System.out.println("========================");
        System.out.println();
        System.out.print("Please select an option : ");

        service.selectOptions();

    }
}
