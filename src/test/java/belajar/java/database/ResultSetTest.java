package belajar.java.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetTest {
    @Test
    void testResultTest() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();
        String sql = """
                SELECT * FROM customers
                """;
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()){
            String id = resultSet.getString("id");
            //Jika penamaan field ambigu karena digunakan sebagai key untuk join
            //maka kita bisa menambhkan prefix tabelnya sebelum nama field / column
            String name = resultSet.getString("customers.name");
            String email = resultSet.getString("email");

            System.out.println(
                    String.join(", ", id, name, email)
            );
        }
        resultSet.close();
        statement.close();
        connection.close();
    }
}
