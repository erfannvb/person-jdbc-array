package com.example.repository;

public class Queries {

    public static final String INSERT_INTO = "insert into person(firstName, lastName) values(?,?)";
    public static final String UPDATE_RECORD = "update person set firstName=?, lastName=? where id=?";
    public static final String DELETE_BY_ID = "delete from person where id=?";
    public static final String SELECT_BY_ID = "select * from person where id=?";
    public static final String SELECT_ALL = "select * from person";

}
