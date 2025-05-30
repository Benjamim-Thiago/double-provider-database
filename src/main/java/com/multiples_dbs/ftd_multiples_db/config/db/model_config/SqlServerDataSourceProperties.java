package com.multiples_dbs.ftd_multiples_db.config.db.model_config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "datasource.sqlserver")
@Getter
@Setter
public class SqlServerDataSourceProperties {
  private String url;
  private String username;
  private String password;
  private String driverClassName;
}
