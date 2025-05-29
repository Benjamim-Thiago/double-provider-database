package com.multiples_dbs.ftd_multiples_db.config.db;

import javax.sql.DataSource;

import com.multiples_dbs.ftd_multiples_db.config.db.mode_config.SqlServerDataSourceProperties;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class SqlServerDataSourceFactory {
  private final SqlServerDataSourceProperties properties;

  public SqlServerDataSourceFactory(SqlServerDataSourceProperties properties) {
    this.properties = properties;
  }

  public static DataSource createDataSource(SqlServerDataSourceProperties properties) {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(properties.getDriverClassName());
    dataSource.setUrl(properties.getUrl());
    dataSource.setUsername(properties.getUsername());
    dataSource.setPassword(properties.getPassword());

    return dataSource;
  }
}
