package com.multiples_dbs.ftd_multiples_db.domain.sqlserver.service;

import com.multiples_dbs.ftd_multiples_db.domain.sqlserver.entity.Post;
import com.multiples_dbs.ftd_multiples_db.domain.sqlserver.repository.PostRepository;
import java.sql.SQLException;
import java.util.List;

public class PostService {

  private final PostRepository postRepository;

  public PostService(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public List<Post> getPostsByUserId(int userId) throws SQLException {
    return postRepository.findByUserId(userId);
  }
}
