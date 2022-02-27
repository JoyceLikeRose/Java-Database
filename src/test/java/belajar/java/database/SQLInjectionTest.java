package belajar.java.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLInjectionTest {
    @Test
    void testSqlInjection() throws SQLException {
        /**
         * Query menggunakan statement sangat tifak dianjurkan
         * karena rawan terjadi sql injection.
         * Keadaan di mana jika where clause yang kita kehendaki (berjumlah lebih dari satu)
         * berhenti di where clause pertama karena kondisi string yang dimasukkan
         * user menghendaki where clause berhenti di where clause pertama
         * misal seperti ini| "admin'; #" | karena tanda petik di tengah dan smicolon
         * yang menandakan akhir perintah sql
         */
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();
        String username = "admin'; #";
        String password = "admin";
        String sql ="SELECT * FROM admin WHERE username= '"+username+"' AND password= '"+password+"'";
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()){
            //Berhasil
            System.out.println("Berhail: " + resultSet.getString("username"));
        } else {
            //Gagal
            System.out.println("Gagal");
        }
        resultSet.close();
        statement.close();
        connection.close();
    }
}
