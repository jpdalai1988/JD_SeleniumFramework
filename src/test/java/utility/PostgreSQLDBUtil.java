package utility;

import java.sql.*;

public class PostgreSQLDBUtil {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    // Connect to PostgreSQL
    public static void connectToDB(String dbUrl, String user, String password) throws SQLException {
        try {
            Class.forName("org.postgresql.Driver"); // optional since JDBC 4.0
            connection = DriverManager.getConnection(dbUrl, user, password);
            statement = connection.createStatement();
            System.out.println("Connected to PostgreSQL DB.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // SELECT query
    public static ResultSet executeQuery(String query) throws SQLException {
        return statement.executeQuery(query);
    }

    // INSERT, UPDATE, DELETE
    public static int executeUpdate(String query) throws SQLException {
        return statement.executeUpdate(query);
    }

    // Close DB resources
    public static void closeConnection() {
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
            System.out.println("DB connection closed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
