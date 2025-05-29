package com.multiples_dbs.ftd_multiples_db.config.db;

import com.multiples_dbs.ftd_multiples_db.config.db.mode_config.MysqlDataSourceProperties;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.multiples_dbs.ftd_multiples_db.domain.mysql.repository",
    entityManagerFactoryRef = "mysqlEntityManagerFactory",
    transactionManagerRef = "mysqlTransactionManager")
@EntityScan(basePackages = "com.multiples_dbs.ftd_multiples_db.domain.mysql.entity")
public class MysqlJpaConfig {
  private final MysqlDataSourceProperties properties;

  public MysqlJpaConfig(MysqlDataSourceProperties properties) {
    this.properties = properties;
  }

  @Bean(name = "mysqlDataSource")
  public DataSource mysqlDataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(this.properties.getDriverClassName());
    dataSource.setUrl(this.properties.getUrl());
    dataSource.setUsername(this.properties.getUsername());
    dataSource.setPassword(this.properties.getPassword());

    return dataSource;
  }

  @Bean(name = "mysqlEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(
      EntityManagerFactoryBuilder builder, @Qualifier("mysqlDataSource") DataSource dataSource) {
    return builder
        .dataSource(dataSource)
        .packages("com.multiples_dbs.ftd_multiples_db.domain.mysql.entity") // entidades MySQL aqui
        .persistenceUnit("mysqlPU")
        .build();
  }

  @Bean(name = "mysqlTransactionManager")
  public PlatformTransactionManager mysqlTransactionManager(
      @Qualifier("mysqlEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }
}
