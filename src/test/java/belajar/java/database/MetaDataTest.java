package belajar.java.database;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class MetaDataTest {
    @Test
    void testGetMetaData() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        DatabaseMetaData databaseMetaData =  connection.getMetaData();
        System.out.println(databaseMetaData.getDatabaseProductName());
        System.out.println(databaseMetaData.getDatabaseProductVersion());

        ResultSet tables = databaseMetaData.getTables("belajar_java_databese", null, null, null);
        while (tables.next()){
            System.out.println(tables.getString("TABLE_NAME"));
        }
        tables.close();
        connection.close();
    }

    @Test
    void testGetParameterMetaData() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String sql = "INSERT INTO comments(email, comment) VALUES(?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();
        System.out.println(parameterMetaData.getParameterCount());
        // ERROR: tidak bisa mendapatkan type parameter untuk
        // Database MySql
//        System.out.println(parameterMetaData.getParameterType(1));
//        System.out.println(parameterMetaData.getParameterType(2));
        preparedStatement.close();
        connection.close();
    }

    @Test
    void testResultSetMetaData() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM sample_times";
        ResultSet resultSet = statement.executeQuery(sql);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        for (int i = 1; i < resultSetMetaData.getColumnCount(); i++) {
            System.out.println("Name: " + resultSetMetaData.getColumnName(i));
            System.out.println("Type: " + resultSetMetaData.getColumnType(i));
            System.out.println("Type Name: " + resultSetMetaData.getColumnTypeName(i));

        }

        resultSet.close();
        connection.close();
    }
}
