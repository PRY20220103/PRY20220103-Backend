package com.pry20220103.backend.domain.persistence;

import com.pry20220103.backend.domain.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> { }
