package com.service.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@Component
public class DataLoader implements CommandLineRunner {
    private final DataSource dataSource;

    @Autowired
    public DataLoader(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(String... args) throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            if (isDataEmpty(connection)) {
                ScriptUtils.executeSqlScript(connection, new ClassPathResource("data.sql"));
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to execute data.sql script", e);
        }
    }
    private boolean isDataEmpty(Connection connection) throws Exception {
        String query = "SELECT COUNT(*) FROM patient";
        try (Statement statement = connection.createStatement(); ResultSet resultSet =
                statement.executeQuery(query)) {
            if (resultSet.next()) {
                return resultSet.getInt(1) == 0;
            }
        }
        return false;
    }
}
