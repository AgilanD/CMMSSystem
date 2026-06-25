package cmms.System.service;

import cmms.System.Dto.AuditLogResponseDto;
import cmms.System.Dto.AuditLogsRequestDto;
import cmms.System.entity.AuditLogs;

import java.util.List;

public interface AuditLogsService {

    public List<AuditLogs> GetAllAuditLogs();

    public AuditLogs GetAuditLogsById(Long id);

    public AuditLogResponseDto CreateAuditLogs (AuditLogsRequestDto auditLogsRequestDto);

}
