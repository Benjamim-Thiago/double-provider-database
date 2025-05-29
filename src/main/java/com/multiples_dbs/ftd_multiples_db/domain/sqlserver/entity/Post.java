package com.multiples_dbs.ftd_multiples_db.domain.sqlserver.entity;

import com.multiples_dbs.ftd_multiples_db.domain.mysql.entity.User;
import java.io.Serial;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
  @Serial private static final long serialVersionUID = 1L;

  private Integer id;
  private String name;
  private User user;
}
