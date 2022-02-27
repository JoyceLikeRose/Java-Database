package belajar.java.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionTest {
    /**
     * JDBC secara default mengatur set commit menjadi true,
     * sehingga perintah sql yang dilakukan akam dijalankan
     * dan hasilnya disimpan secara permanen di dalan database.
     * Kita bisa mengatur set auto commit di JDBC menjadi false,
     * tapi agar perintah sql kita dijalankan dan hasilnya di-
     * simpan permanen di database, kita hasus meng-commit connection,
     * sebelum connection kita close.
     * Kita juga bisa membatalkan transaksi dengan perintah rollBack()
     */
    @Test
    void testCommit() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO comments(email, comment) VALUES(?, ?)";

        for (int i = 0; i < 100; i++) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "wildan@test.com");
            preparedStatement.setString(2, "hi");
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }

        //connection.rollback();
        connection.commit();
        connection.close();
    }
}
