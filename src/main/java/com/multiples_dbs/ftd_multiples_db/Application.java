package com.multiples_dbs.ftd_multiples_db;

import java.sql.SQLException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Application {
  public static void main(String[] args) throws SQLException {
    SpringApplication.run(Application.class, args);
  }
}
