package fr.takima.cdb.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public enum TakimaConnectionFactory {
    INSTANCE;

    private Connection connection;
    private String datasource = "datasource.properties";

    TakimaConnectionFactory() {

        Properties properties = new Properties();
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream(datasource));
            HikariConfig cfg = new HikariConfig(properties);
            HikariDataSource ds = new HikariDataSource(cfg);

            connection = ds.getConnection();
        } catch (IOException | SQLException  e) {
            throw new RuntimeException(e);
        }
    }


    public String createSqlQuery() throws SQLException {

        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT 1");
        String result = "";
        while (rs.next()) {
            result = rs.getString("1");
        }
        return result;
    }

    public Connection getConnection() {
        return connection;
    }
}
