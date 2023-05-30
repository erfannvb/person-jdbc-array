package com.example.connection;

import java.sql.*;

import static com.example.connection.JdbcConstant.*;

public class JdbcConnection {

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return connection;
    }

    public static void closeConnection(Connection connection) throws SQLException {
        if (connection != null)
            connection.close();
    }

    public static void closePreparedStatement(PreparedStatement preparedStatement) throws SQLException {
        if (preparedStatement != null)
            preparedStatement.close();
    }

    public static void closeResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet != null)
            resultSet.close();
    }

}
