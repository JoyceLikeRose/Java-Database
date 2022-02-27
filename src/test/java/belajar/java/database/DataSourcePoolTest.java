package belajar.java.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSourcePoolTest {
    @Test
    void testHikariCp() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/belajar_java_databese");
        config.setUsername("root");
        config.setPassword("wildan123");

        //Konfigurasi pool
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setIdleTimeout(60_000);
        config.setMaxLifetime(10 * 60_000);

        try{
            HikariDataSource dataSource = new HikariDataSource(config);
            Connection connection = dataSource.getConnection();
            System.out.println("berhasil connect ke database");
            connection.close();
            System.out.println("berhasil mengembalikan koneksi");
            dataSource.close();
            System.out.println("Sukses menutup pool");
        } catch (SQLException exception){
            Assertions.fail(exception);
        }
    }

    @Test
    void testConnectionUtil() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
    }
}
