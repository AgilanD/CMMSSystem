package cmms.system.controller;


import cmms.system.dto.*;
import cmms.system.service.AuditLogsService;
import cmms.system.service.ServiceHistoryService;
import cmms.system.usercontext.RequireRole;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/System")
public class SystemController {

    private final AuditLogsService  auditLogsService;

    private final ServiceHistoryService historyService;

    @GetMapping("/GetAllAuditLogs")
    @RequireRole({"ADMIN"})
    public List<AuditLogResponseDto> getAllAuditLogs(){
        return auditLogsService.getAllAuditLogs();
    }

    @PostMapping("/AddAuditLogs")
    @RequireRole({"ADMIN"})
    public AuditLogResponseDto createAuditLog( @RequestBody AuditLogsRequestDto requestDto) {
       return auditLogsService.createAuditLogs(requestDto);
    }

    @GetMapping("/getAuditLogByIds/{id}")
    @RequireRole({"ADMIN"})
    public AuditLogResponseDto getAuditLogUsingIds(@PathVariable Long id){
        return auditLogsService.getAuditLogsById(id);
    }

    @PostMapping("/CreateHistory")
    @RequireRole({"ADMIN"})
    public ServiceHistoryResponseDto createHistory( @RequestBody ServiceHistoryRequestDto requestDto) {
        return historyService.createHistory(requestDto);
    }

    @GetMapping("/GetAllHistory")
    @RequireRole({"ADMIN"})
    public List<ServiceHistoryResponseDto> getAllHistories() {
        return historyService.getAllHistory();

    }

    @GetMapping("/GetHistoryById/{id}")
    public ServiceHistoryResponseDto getHistoryById(@PathVariable Long id) {
       return  historyService.getHistoryById(id);
    }

    @PutMapping("UpdateHistoryById/{id}")
    public ServiceHistoryResponseDto updateHistory(
            @PathVariable Long id,
            @RequestBody ServiceHistoryRequestDto requestDto) {
       return  historyService.updateHistory(id, requestDto);
    }

    @DeleteMapping("/deleteHistory/{id}")
    public void deleteHistory(@PathVariable Long id) {
        historyService.deleteHistory(id);
    }

}
