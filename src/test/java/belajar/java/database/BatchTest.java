package belajar.java.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchTest {
    /**
     * Pada saat kita memberikan perintah addBatch, sebenarnya
     * perintah sql kita disinpan dalam memory applikasi java kita dalam bentuk array atau list
     * baru setelah dipanngil perintah executeBatch
     * perintah kita dijalankan sekaligus.
     * Disarankan bijak dalam menngunakan Batch ini.
     * Misal data kita ada sejuta yang mau dieksekusi lansung,
     * maka sebaiknya kita masukkan data secara bertahap agar tidak terjadi
     * memory of bound (memori applikasi java kita penuh)
     */
    @Test
    void testStatement() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();
        String sql = "INSERT INTO comments(email, comment) VALUES('wildan@test.com', 'hi')";

        for (int i = 0; i < 1000; i++) {
            statement.addBatch(sql);
        }

        statement.executeBatch();

        statement.close();
        connection.close();
    }

    @Test
    void testPreparedStatement() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String sql = "INSERT INTO comments(email, comment) VALUES(?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        for (int i = 0; i < 1000; i++) {
            preparedStatement.clearParameters();
            preparedStatement.setString(1, "wildan@test.com");
            preparedStatement.setString(2, "hi");
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();

        preparedStatement.close();
        connection.close();
    }


}
