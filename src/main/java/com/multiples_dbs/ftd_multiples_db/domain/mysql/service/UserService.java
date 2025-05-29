package com.multiples_dbs.ftd_multiples_db.domain.mysql.service;

import com.multiples_dbs.ftd_multiples_db.domain.mysql.entity.User;
import com.multiples_dbs.ftd_multiples_db.domain.mysql.repository.UserRepository;
import java.sql.SQLException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User getUser(int id) throws SQLException {
    return userRepository.findById(id).get();
  }
}
