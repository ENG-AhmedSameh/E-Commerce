package com.example.ecommerce.model;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Properties;

public class HikariCPDataSource {

    private static HikariCPDataSource instance;
    private final HikariDataSource dataSource;

    private static final int MIN_POOL_SIZE = 20;
    private static final int MAX_POOL_SIZE = 100;
    private static final int MAX_IDLE_TIME = 3000;

    private HikariCPDataSource() {
        dataSource = setUpDataSource();
    }

    private HikariDataSource setUpDataSource(){
        Properties credentials = new Properties();

        try (InputStream is = getClass().getClassLoader().getResourceAsStream("db.properties")) {
            credentials.load(is);
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(credentials.getProperty("db.url"));
            config.setUsername(credentials.getProperty("db.user"));
            config.setPassword(credentials.getProperty("db.password"));

            config.setMinimumIdle(MIN_POOL_SIZE);
            config.setMaximumPoolSize(MAX_POOL_SIZE);
            config.setIdleTimeout(MAX_IDLE_TIME);

            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
            return new HikariDataSource(config);
        } catch (IOException e) {
            System.err.println("Datasource Exception: " + e.getMessage()+"\n"+ Arrays.toString(e.getStackTrace()));
            return null;
        }
    }

    public static synchronized HikariCPDataSource getInstance() {
        if (instance == null) {
            synchronized (HikariCPDataSource.class) {
                if (instance == null) {
                    instance = new HikariCPDataSource();
                }
            }
        }
        return instance;
    }

    public HikariDataSource getDataSource() {
        return dataSource;
    }
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}

