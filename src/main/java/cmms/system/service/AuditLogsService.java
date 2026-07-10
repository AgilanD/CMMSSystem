package cmms.system.service;

import cmms.system.dto.AuditLogResponseDto;
import cmms.system.dto.AuditLogsRequestDto;

import java.util.List;

public interface AuditLogsService {

    public List<AuditLogResponseDto> getAllAuditLogs();

    public AuditLogResponseDto getAuditLogsById(Long id);

    public AuditLogResponseDto createAuditLogs (AuditLogsRequestDto auditLogsRequestDto);

}
