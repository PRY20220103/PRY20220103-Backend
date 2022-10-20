package com.pry20220103.backend.service;

import com.pry20220103.backend.domain.model.entity.Model;
import com.pry20220103.backend.domain.model.entity.UsageLog;
import com.pry20220103.backend.domain.persistence.ModelRepository;
import com.pry20220103.backend.domain.persistence.UsageLogRepository;
import com.pry20220103.backend.domain.service.UsageLogService;
import com.pry20220103.backend.shared.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsageLogServiceImpl implements UsageLogService {
    @Autowired
    private UsageLogRepository usageLogRepository;

    @Autowired
    private ModelRepository modelRepository;

    @Override
    public UsageLog createUsageLog(Long modelId, UsageLog usageLog) {
        return modelRepository.findById(modelId).map(model -> {
            usageLog.setModel(model);
            return usageLogRepository.save(usageLog);
        }).orElseThrow(() -> new ResourceNotFoundException("Model", modelId));
    }

    @Override
    public UsageLog updateUsageLog(Long modelId, Long usageLogId, UsageLog usageLogUpdate) {
        return usageLogRepository.findByIdAndModelId(usageLogId, modelId).map(log -> {
            Model model = usageLogUpdate.getModel();
            String grade = model.getGrade();
            log.setModelGrade(grade);
            log.setRoleName(usageLogUpdate.getRoleName());
            log.setViewedAt(usageLogUpdate.getViewedAt());
            return usageLogRepository.save(log);
        }).orElseThrow(() -> new ResourceNotFoundException("Usage Log", usageLogId));
    }

    @Override
    public UsageLog getUsageLogByIdAndModelId(Long usageLogId, Long modelId) {
        return usageLogRepository.findByIdAndModelId(usageLogId, modelId)
                .orElseThrow(() -> new ResourceNotFoundException("Usage Log not found with id: " +
                        usageLogId + " and model id: " + modelId));
    }

    @Override
    public Page<UsageLog> getAllByModelId(Long modelId, Pageable pageable) {
        return usageLogRepository.findAllByModelId(modelId, pageable);
    }

    @Override
    public Page<UsageLog> getAll(Pageable pageable) {
        return usageLogRepository.findAll(pageable);
    } //TODO: Ver paginación
}
