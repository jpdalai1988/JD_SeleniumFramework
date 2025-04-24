package utility;

import java.sql.*;

public class MySQLDBUtil {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static void connectToDB(String dbUrl, String user, String password) throws SQLException {
        connection = DriverManager.getConnection(dbUrl, user, password);
        statement = connection.createStatement();
    }

    public static ResultSet executeQuery(String query) throws SQLException {
        resultSet = statement.executeQuery(query);
        return resultSet;
    }

    public static int executeUpdate(String query) throws SQLException {
        return statement.executeUpdate(query);
    }

    public static void closeConnection() throws SQLException {
        if (resultSet != null) resultSet.close();
        if (statement != null) statement.close();
        if (connection != null) connection.close();
    }
}
