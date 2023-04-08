package dao;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;
@Testcontainers
class TodoDaoTest {
    @Container
    public static MySQLContainer container = new MySQLContainer("mysql:8.0")
            .withDatabaseName("tests-db")
            .withPassword("root")
            .withUsername("root");
    String SQL_CREATE_TABLES = "CREATE TABLE user (id INT PRIMARY KEY AUTO_INCREMENT, first_name VARCHAR(20) NOT NULL, last_name VARCHAR(20), username VARCHAR(250) NOT NULL, password VARCHAR(20) NOT NULL)";// +
            //" CREATE TABLE todo (id INT PRIMARY KEY AUTO_INCREMENT, description VARCHAR(255) NOT NULL, is_done BIT, target_date DATETIME(6), username VARCHAR(255) NOT NULL, title VARCHAR(255) NOT NULL, FOREIGN KEY(user_id) REFERENCES user(id);";
    @BeforeAll
    public static void startDb() {
        container.start();
    }

//    @Test
//    void testInsertData() throws Exception {
//        // Connect to the database
//        Connection conn = DriverManager.getConnection(
//                container.getJdbcUrl(),
//                container.getUsername(),
//                container.getPassword());
//        // Create a table with a single column called "name"
//        Statement statement = conn.createStatement();
//        statement.execute("CREATE TABLE IF NOT EXISTS test_table (id bigint, name VARCHAR(100))");
//
//        // Insert data
//        Statement stmt = conn.createStatement();
//        stmt.executeUpdate("INSERT INTO test_table VALUES (1, 'hello')");
//
//        // Verify data was inserted
//        ResultSet resultSet = stmt.executeQuery("SELECT count(*) FROM test_table");
//        resultSet.next();
//        assertEquals(1, resultSet.getInt(1));
//
//        // Close connection
//        conn.close();
//    }
    @Test
    void testInsertUser() throws Exception {
        // Connect to the database
        Connection conn = DriverManager.getConnection(
                container.getJdbcUrl(),
                container.getUsername(),
                container.getPassword());
        // Create a table user
        Statement statement = conn.createStatement();
        statement.execute(SQL_CREATE_TABLES);

        // Insert data
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("INSERT INTO user VALUES (1, 'karl', 'lagerfeld', 'karlag', '111111')");

        // Verify data was inserted
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM user WHERE username='karlag'");
        resultSet.next();
        String username = resultSet.getNString(4);
        String password = resultSet.getNString(5);
        assertEquals(("karlag"), username);
        assertEquals("111111", password);

        // Close connection
        conn.close();
    }


    @AfterAll
    public static void stopDb() {
        container.stop();
    }
}