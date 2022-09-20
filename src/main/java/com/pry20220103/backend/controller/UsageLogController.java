package com.pry20220103.backend.controller;

import com.pry20220103.backend.domain.model.entity.UsageLog;
import com.pry20220103.backend.domain.service.UsageLogService;
import com.pry20220103.backend.resource.SaveUsageLogResource;
import com.pry20220103.backend.resource.UsageLogResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/models/{modelId}/usage-logs")
public class UsageLogController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UsageLogService usageLogService;

    @PostMapping
    public UsageLogResource createUsageLog(@PathVariable(name = "modelId") Long modelId,
                          @Valid @RequestBody SaveUsageLogResource resource){
        UsageLog usageLog = convertToEntity(resource);
        return convertToResource(usageLogService.createUsageLog(modelId, usageLog));
    }

    @PutMapping("/{usageLogId}")
    public UsageLogResource updateUsageLog(@PathVariable(name = "modelId") Long modelId,
                                           @PathVariable(name = "usageLogId") Long usageLogId,
                                           @Valid @RequestBody SaveUsageLogResource resource){
        UsageLog usageLog = convertToEntity(resource);
        return convertToResource(usageLogService.updateUsageLog(modelId, usageLog));
    }

    private UsageLog convertToEntity(SaveUsageLogResource resource){
        return mapper.map(resource, UsageLog.class);
    }

    private UsageLogResource convertToResource(UsageLog entity){
        return mapper.map(entity, UsageLogResource.class);
    }

}
