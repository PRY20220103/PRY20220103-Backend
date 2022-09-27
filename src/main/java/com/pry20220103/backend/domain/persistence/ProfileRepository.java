package com.pry20220103.backend.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pry20220103.backend.domain.model.entity.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long>{
    
}
