package com.mxnxv.todoapp;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringBootTest
@SpringJUnitConfig
@Configuration
@TestPropertySource("classpath:application.properties")
public class DbConTests {
    

    Logger log = LoggerFactory.getLogger(DbConTests.class);

    @Value("${spring.datasource.url}")
    String dbUrl;

    @Test
    void dbConTest()
    {
        try {
            Connection con= DriverManager.getConnection(dbUrl);
            assertTrue(con.isValid(5));
            con.isValid(5);
            log.info("Connection checked✅ .... Closing Connection...");
            con.close();
        } catch (SQLException e) {
            log.error( e.getMessage());
           
        }
    }
}
