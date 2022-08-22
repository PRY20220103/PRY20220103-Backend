package com.pry20220103.backend.service;

import com.pry20220103.backend.domain.model.entity.Role;
import com.pry20220103.backend.domain.model.enumeration.Roles;
import com.pry20220103.backend.domain.persistence.RoleRepository;
import com.pry20220103.backend.domain.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    private static String[] DEFAULT_ROLE = {"ROLE_STUDENT", "ROLE_PROFESSOR", "ROLE_ADMIN"};

    @Override
    public void seed() {
        Arrays.stream(DEFAULT_ROLE).forEach(name -> {
            Roles roleName = Roles.valueOf(name);
            if(!roleRepository.existsByName(roleName)) {
                roleRepository.save(new Role().withName(roleName));
            }
        });
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }
}
