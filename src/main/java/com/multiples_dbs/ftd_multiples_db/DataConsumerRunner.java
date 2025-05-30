package com.multiples_dbs.ftd_multiples_db;

import com.multiples_dbs.ftd_multiples_db.config.db.SqlServerDataSourceFactory;
import com.multiples_dbs.ftd_multiples_db.config.db.model_config.SqlServerDataSourceProperties;
import com.multiples_dbs.ftd_multiples_db.domain.mysql.service.ProfileService;
import com.multiples_dbs.ftd_multiples_db.domain.mysql.service.UserService;
import com.multiples_dbs.ftd_multiples_db.domain.sqlserver.repository.PostRepository;
import com.multiples_dbs.ftd_multiples_db.domain.sqlserver.service.PostService;
import javax.sql.DataSource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataConsumerRunner implements CommandLineRunner {
  private final UserService userService;
  private final ProfileService profileService;
  private final SqlServerDataSourceProperties sqlServerProperties;

  public DataConsumerRunner(
      UserService userService,
      ProfileService profileService,
      SqlServerDataSourceProperties sqlServerProperties) {
    this.userService = userService;
    this.profileService = profileService;
    this.sqlServerProperties = sqlServerProperties;
  }

  @Override
  public void run(String... args) throws Exception {

    System.out.println(profileService.getProfileById(4));
    System.out.println(userService.getUser(1));

    DataSource sqlserverDataSource =
        SqlServerDataSourceFactory.createDataSource(this.sqlServerProperties);
    PostRepository postRepo = new PostRepository(sqlserverDataSource);
    PostService postService = new PostService(postRepo);

    System.out.println(postService.getPostsByUserId(1));
  }
}
