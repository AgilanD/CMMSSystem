package cmms.system.service;

import cmms.system.dto.AuditLogResponseDto;
import cmms.system.dto.AuditLogsRequestDto;
import cmms.system.entity.AuditLogs;

import java.util.List;

public interface AuditLogsService {

    public List<AuditLogs> getAllAuditLogs();

//    public AuditLogsRequestDto getAuditLogsById(Long id);

    public AuditLogResponseDto createAuditLogs (AuditLogsRequestDto auditLogsRequestDto);

}
