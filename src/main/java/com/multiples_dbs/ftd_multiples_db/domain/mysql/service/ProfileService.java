package com.multiples_dbs.ftd_multiples_db.domain.mysql.service;

import com.multiples_dbs.ftd_multiples_db.domain.mysql.entity.Profile;
import com.multiples_dbs.ftd_multiples_db.domain.mysql.repository.ProfileRepository;
import java.sql.SQLException;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
  private final ProfileRepository profileRepository;

  public ProfileService(ProfileRepository profileRepository) {
    this.profileRepository = profileRepository;
  }

  public Profile getProfileById(Integer id) throws SQLException {
    return profileRepository.findById(id).get();
  }

  public Profile getProfileByName(String name) throws SQLException {
    return profileRepository.findByName(name).get();
  }
}
