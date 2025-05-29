package com.multiples_dbs.ftd_multiples_db.domain.sqlserver.repository;

import com.multiples_dbs.ftd_multiples_db.domain.sqlserver.entity.Post;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class PostRepository {

  private final DataSource dataSource;

  public PostRepository(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public List<Post> findByUserId(int userId) throws SQLException {
    String sql = "SELECT id, description, user_id FROM posts WHERE user_id = ?";
    List<Post> posts = new ArrayList<>();

    try (Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {

      ps.setInt(1, userId);
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        posts.add(new Post(rs.getInt("id"), rs.getString("description"), null));
      }
    }
    return posts;
  }
}
