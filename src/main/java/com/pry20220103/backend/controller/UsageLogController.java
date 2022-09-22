package com.pry20220103.backend.controller;

import com.pry20220103.backend.domain.model.entity.UsageLog;
import com.pry20220103.backend.domain.service.UsageLogService;
import com.pry20220103.backend.resource.SaveUsageLogResource;
import com.pry20220103.backend.resource.UsageLogResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import javax.validation.*;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin
public class UsageLogController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UsageLogService usageLogService;

    @PostMapping("/api/v1/models/{modelId}/usage-logs")
    public UsageLogResource createUsageLog(@PathVariable(name = "modelId") Long modelId,
                          @Valid @RequestBody SaveUsageLogResource resource){
        UsageLog usageLog = convertToEntity(resource);
        return convertToResource(usageLogService.createUsageLog(modelId, usageLog));
    }

    @GetMapping("/api/v1/models/{modelId}/usage-logs/{usageLogId}")
    public UsageLogResource getUsageLog(@PathVariable(name = "modelId") Long modelId,
                                        @PathVariable(name = "usageLogId") Long usageLogId){
        return convertToResource(usageLogService.getUsageLogByIdAndModelId(usageLogId,  modelId));
    }

    @PutMapping("/api/v1/models/{modelId}/usage-logs/{usageLogId}")
    public UsageLogResource updateUsageLog(@PathVariable(name = "modelId") Long modelId,
                                           @PathVariable(name = "usageLogId") Long usageLogId,
                                           @Valid @RequestBody SaveUsageLogResource resource){
        UsageLog usageLog = convertToEntity(resource);
        return convertToResource(usageLogService.updateUsageLog(modelId, usageLogId, usageLog));
    }

    @GetMapping("/api/v1/models/{modelId}/usage-logs")
    public Page<UsageLogResource> getAllUsageLogsByModelId(@PathVariable(name = "modelId") Long modelId,
                                                           Pageable pageable){
        Page<UsageLog> usageLogPage = usageLogService.getAllByModelId(modelId, pageable);
        List<UsageLogResource> resources = usageLogPage.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/api/v1/usage-logs")
    public Page<UsageLogResource> getAllUsageLogs(Pageable pageable){
        Page<UsageLog> usageLogPage = usageLogService.getAll(pageable);
        List<UsageLogResource> resources = usageLogPage.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    private UsageLog convertToEntity(SaveUsageLogResource resource){
        return mapper.map(resource, UsageLog.class);
    }

    private UsageLogResource convertToResource(UsageLog entity){
        return mapper.map(entity, UsageLogResource.class);
    }
}
