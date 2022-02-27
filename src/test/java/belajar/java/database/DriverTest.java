package belajar.java.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Driver;
import java.sql.SQLException;

public class DriverTest {
    @Test
    void testDriver() {
        try {
            Driver testDriver = new com.mysql.cj.jdbc.Driver();
        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }
}
