package com.example.repository;

import com.example.connection.JdbcConnection;
import com.example.entity.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.repository.JdbcQueries.*;

public class JdbcRepository {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void save(Person person) {
        try {
            connection = JdbcConnection.getConnection();
            if (connection == null) {
                System.out.println("Error getting the connection.");
            }

            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(INSERT_INTO);

            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());

            preparedStatement.execute();

            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {

                JdbcConnection.closeConnection(connection);
                JdbcConnection.closePreparedStatement(preparedStatement);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void saveAll(List<Person> personList) {
        try {
            connection = JdbcConnection.getConnection();
            if (connection == null) {
                System.out.println("Error getting the connection.");
            }

            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(INSERT_INTO);

            for (int i = 0; i < personList.size(); i++) {
                preparedStatement.setString(1, personList.get(i).getFirstName());
                preparedStatement.setString(2, personList.get(i).getLastName());
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();

            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                JdbcConnection.closeConnection(connection);
                JdbcConnection.closePreparedStatement(preparedStatement);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update(Person person) {
        try {
            connection = JdbcConnection.getConnection();
            if (connection == null) {
                System.out.println("Error getting the connection.");
            }

            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(UPDATE_RECORD);

            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setLong(3, person.getId());

            preparedStatement.execute();

            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {

                JdbcConnection.closeConnection(connection);
                JdbcConnection.closePreparedStatement(preparedStatement);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void deleteById(Long id) {
        try {
            connection = JdbcConnection.getConnection();
            if (connection == null) {
                System.out.println("Error getting the connection.");
            }

            preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                JdbcConnection.closeConnection(connection);
                JdbcConnection.closePreparedStatement(preparedStatement);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Person loadById(Long id) {
        Person person = new Person();
        try {
            connection = JdbcConnection.getConnection();
            if (connection == null) {
                System.out.println("Error getting the connection.");
            }

            preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                person.setId(resultSet.getLong("id"));
                person.setFirstName(resultSet.getString("firstName"));
                person.setLastName(resultSet.getString("lastName"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                JdbcConnection.closeConnection(connection);
                JdbcConnection.closePreparedStatement(preparedStatement);
                JdbcConnection.closeResultSet(resultSet);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return person;
    }

    public List<Person> loadAll() {
        List<Person> personList = new ArrayList<>();
        try {
            connection = JdbcConnection.getConnection();
            if (connection == null) {
                System.out.println("Error getting the connection.");
            }

            preparedStatement = connection.prepareStatement(SELECT_ALL);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getLong("id"));
                person.setFirstName(resultSet.getString("firstName"));
                person.setLastName(resultSet.getString("lastName"));
                personList.add(person);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                JdbcConnection.closeConnection(connection);
                JdbcConnection.closePreparedStatement(preparedStatement);
                JdbcConnection.closeResultSet(resultSet);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return personList;
    }

    public void getNumberOfPeople() {
        try {
            connection = JdbcConnection.getConnection();
            if (connection == null) {
                System.out.println("Error getting the connection.");
            }

            preparedStatement = connection.prepareStatement(NUMBER_OF_PEOPLE);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int numberOfPeople = resultSet.getInt("number_of_people");
                System.out.println("Number of People : " + numberOfPeople);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                JdbcConnection.closeConnection(connection);
                JdbcConnection.closePreparedStatement(preparedStatement);
                JdbcConnection.closeResultSet(resultSet);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void loadWithPagination(int limit, int offset) {
        try {

            connection = JdbcConnection.getConnection();
            if (connection == null) {
                System.out.println("Error getting the connection.");
            }

            preparedStatement = connection.prepareStatement(SELECT_ALL_WITH_PAGINATION);
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("LastName");
                System.out.println(id + " : " + firstName + " " + lastName);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {

                JdbcConnection.closeConnection(connection);
                JdbcConnection.closePreparedStatement(preparedStatement);
                JdbcConnection.closeResultSet(resultSet);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
