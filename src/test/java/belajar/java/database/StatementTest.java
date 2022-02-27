package belajar.java.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementTest {
    @Test
    void testStatement() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();
        statement.close();
        connection.close();
    }

    @Test
    void testStatementExecuteUpdate() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();
        String sql = """
                INSERT INTO customers(id, name, email) VALUES
                ('nanu', 'Nanu', 'nanu@test.com')
                """;
        int update = statement.executeUpdate(sql);
        System.out.println(update);
        statement.close();
        connection.close();
    }

    @Test
    void testStatementExecuteQuery() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();
        String sql = """
                SELECT * FROM customers
                """;
        ResultSet resultSet = statement.executeQuery(sql);

        resultSet.close();
        statement.close();
        connection.close();
    }
}
