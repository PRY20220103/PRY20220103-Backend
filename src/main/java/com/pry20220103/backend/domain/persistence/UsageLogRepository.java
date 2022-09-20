package com.pry20220103.backend.domain.persistence;

import com.pry20220103.backend.domain.model.entity.UsageLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsageLogRepository extends JpaRepository<UsageLog, Long> {
    UsageLog findByIdAndModelId(Long usageLogId, Long modelId);
    Optional<UsageLog> findByModelId(Long modelId);
    Page<UsageLog> findAllByModelId(Long modelId, Pageable pageable);
}
