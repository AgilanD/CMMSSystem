package cmms.system.controller;


import cmms.system.dto.*;
import cmms.system.entity.AuditLogs;
import cmms.system.service.AuditLogsService;
import cmms.system.service.ServiceHistoryService;
import cmms.system.userContext.RequireRole;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/System")
public class controller {

    private final AuditLogsService  auditLogsService;

    private final ServiceHistoryService historyService;

    @GetMapping("/GetAllAuditLogs")
    @RequireRole({"ADMIN", "PLANT_MANAGER"})
    public List<AuditLogs> GetAllAuditLogs(){
        return auditLogsService.getAllAuditLogs();
    }

    @PostMapping("/AddAuditLogs")
    public AuditLogResponseDto createAuditLog( @RequestBody AuditLogsRequestDto requestDto) {
       return auditLogsService.createAuditLogs(requestDto);
    }

//    @GetMapping("/getAuditLogByIds")
//    public AuditLogResponseDto getAuditLogUsingIds(@PathVariable Long id){
//        return auditLogsService.getAuditLogsById(getAuditLogUsingIds())
//    }

    @PostMapping("/CreateHistory")
    @RequireRole({"ADMIN"})
    public ServiceHistoryResponseDto createHistory( @RequestBody ServiceHistoryRequestDto requestDto) {
        ServiceHistoryResponseDto createdHistory = historyService.createHistory(requestDto);
        return createdHistory;
    }

    @GetMapping("/GetAllHistory")
    @RequireRole({"ADMIN"})
    public List<ServiceHistoryResponseDto> getAllHistories() {
        List<ServiceHistoryResponseDto> histories = historyService.getAllHistory();
        return histories;
    }

    @GetMapping("/GetHistoryById/{id}")
    public ServiceHistoryResponseDto getHistoryById(@PathVariable Long id) {
        ServiceHistoryResponseDto history = historyService.getHistoryById(id);
        return history;
    }

    @PutMapping("UpdateHistoryById/{id}")
    public ServiceHistoryResponseDto updateHistory(
            @PathVariable Long id,
            @RequestBody ServiceHistoryRequestDto requestDto) {
        ServiceHistoryResponseDto updatedHistory = historyService.updateHistory(id, requestDto);
        return updatedHistory;
    }

    @DeleteMapping("/deleteHistory/{id}")
    public void deleteHistory(@PathVariable Long id) {
        historyService.deleteHistory(id);
    }

}
