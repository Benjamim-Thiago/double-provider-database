package com.multiples_dbs.ftd_multiples_db.domain.mysql.repository;

import com.multiples_dbs.ftd_multiples_db.domain.mysql.entity.Profile;
import com.multiples_dbs.ftd_multiples_db.domain.mysql.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<User> findByUserCode(String userCode);

  List<User> findByProfile(Profile profile);
}
