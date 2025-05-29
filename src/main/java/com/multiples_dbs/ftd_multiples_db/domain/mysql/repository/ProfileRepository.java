package com.multiples_dbs.ftd_multiples_db.domain.mysql.repository;

import com.multiples_dbs.ftd_multiples_db.domain.mysql.entity.Profile;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {
  Optional<Profile> findByName(String name);
}
