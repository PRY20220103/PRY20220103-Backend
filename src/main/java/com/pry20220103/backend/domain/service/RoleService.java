package com.pry20220103.backend.domain.service;

import com.pry20220103.backend.domain.model.entity.Role;

import java.util.List;

public interface RoleService {

    void seed();

    List<Role> getAll();

}
