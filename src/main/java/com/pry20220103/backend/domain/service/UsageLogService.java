package com.pry20220103.backend.domain.service;

import com.pry20220103.backend.domain.model.entity.UsageLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UsageLogService {
    UsageLog createUsageLog(Long modelId, UsageLog usageLog);
    UsageLog updateUsageLog(Long modelId, Long usageLogId, UsageLog usageLog);
    UsageLog getUsageLogByIdAndModelId(Long usageLogId, Long modelId);
    Page<UsageLog> getAllByModelId(Long modelId, Pageable  pageable);
    Page<UsageLog> getAll(Pageable pageable);
}