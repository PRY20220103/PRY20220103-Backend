package com.pry20220103.backend.domain.persistence;

import com.pry20220103.backend.domain.model.entity.Role;
import com.pry20220103.backend.domain.model.enumeration.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(Roles name);

    boolean existsByName(Roles name);

}
