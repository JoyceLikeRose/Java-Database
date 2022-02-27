package belajar.java.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatementTest {
    /**
     * Untuk menghindari sql injection, disarankan setiap kita
     * melakukan insert atau query data yang diinput user
     * sebaiknya menggunakan class PreparedStatement.
     * Tidak seperti statement yang hard code string yang di input ke perintah sql.
     * class PreparedStatement ini menggunakan tanya tanya sebagai ganti
     * value di perintah sql.
     * ? = index yang akan menginisiasi data yang kita input (dimulai dari 1)
     * dan menggunakan method dari prepared statement dengan prefix setXxx sesuai
     * type data yang kita kehendaki.
     */
    @Test
    void testPreparedStatementQuery() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String username = "wildan";
        String password = "salah";
        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            //Berhasil login
            System.out.println("username:" + resultSet.getString("username"));
        } else {
            // Gagal login
            System.out.println("Gagal login");
        }

        preparedStatement.close();
        connection.close();
    }

    @Test
    void testPreparedStatementInsert() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String username = "wildan";
        String password = "wildan";
        String sql = "INSERT INTO admin(username, password) VALUES(?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        int update = preparedStatement.executeUpdate();
        System.out.println(update);
        preparedStatement.close();
        connection.close();
    }
}
