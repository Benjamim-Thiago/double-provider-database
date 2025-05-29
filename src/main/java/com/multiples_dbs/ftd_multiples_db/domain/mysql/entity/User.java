package com.multiples_dbs.ftd_multiples_db.domain.mysql.entity;

import com.multiples_dbs.ftd_multiples_db.domain.sqlserver.entity.Post;
import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
  @Serial private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false, length = 100, unique = true)
  private String userCode;

  @ManyToOne
  @JoinColumn(name = "profile_id", referencedColumnName = "id")
  private Profile profile;

  @Transient private List<Post> posts;
}
