package tests;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Test;

import utility.MySQLDBUtil;
import utility.PostgreSQLDBUtil;

public class DBTest {

	//If you want to test the script using MySQL
	@Test
	public void testUserExistsInDB() throws SQLException {
	    MySQLDBUtil.connectToDB("jdbc:mysql://localhost:3306/qa_db", "root", "password");

	    ResultSet rs = MySQLDBUtil.executeQuery("SELECT * FROM users WHERE username = 'testuser'");
	    Assert.assertTrue(rs.next()); // user should exist

	    MySQLDBUtil.closeConnection();
	}

	//If you want to test the script using PostgreSQL
    @Test
    public void validateUserInPostgresDB() throws SQLException {
        // Replace with actual credentials
        String dbUrl = "jdbc:postgresql://localhost:5432/your_database";
        String user = "your_username";
        String password = "your_password";

        PostgreSQLDBUtil.connectToDB(dbUrl, user, password);

        ResultSet rs = PostgreSQLDBUtil.executeQuery("SELECT * FROM users WHERE email = 'jpdalai1988@gmail.com'");
        Assert.assertTrue(rs.next(), "User should exist in the database.");

        PostgreSQLDBUtil.closeConnection();
    }

}
