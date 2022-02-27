package belajar.java.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {
    @BeforeAll
    static void beforeAll() {
        try{
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.deregisterDriver(driver);
        }catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    @Test
    void testConnection() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/belajar_java_databese";
        String username = "root";
        String password = "wildan123";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("berhasil connect ke database");
        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }

    @Test
    void testConnectionClose() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/belajar_java_databese";
        String username = "root";
        String password = "wildan123";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("berhasil connect ke database");
            connection.close();
            System.out.println("berhasil menutup connect ke database");
        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }

    @Test
    void testConnectionCloseWithTryResource() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/belajar_java_databese";
        String username = "root";
        String password = "wildan123";
        try(Connection connection = DriverManager.getConnection(jdbcUrl, username, password);) {
            System.out.println("berhasil connect ke database");
        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }
}
