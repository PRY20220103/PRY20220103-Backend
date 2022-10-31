package com.pry20220103.backend.domain.persistence;

import com.pry20220103.backend.domain.model.entity.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
    Optional<Model> findByModelName(String name);
    Optional<Model> findByIdAndCategoryId(Long modelId, Long categoryId);
    Page<Model> findAllByCategoryId(Long categoryId, Pageable pageable);

}
