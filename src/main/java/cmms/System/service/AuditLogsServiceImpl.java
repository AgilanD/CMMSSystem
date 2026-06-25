package cmms.System.service;

import cmms.System.Dto.AuditLogResponseDto;
import cmms.System.Dto.AuditLogsRequestDto;
import cmms.System.entity.AuditLogs;
import cmms.System.repository.AuditLogsRepository;
import cmms.System.utils.AuditLogsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditLogsServiceImpl implements AuditLogsService{

    private final AuditLogsRepository auditLogsRepository;
    private final AuditLogsMapper auditLogsMapper;

    public List<AuditLogs> GetAllAuditLogs(){
        return auditLogsRepository.findAll();
    }

    public AuditLogs GetAuditLogsById(Long id){
        return auditLogsRepository.findById(id).orElse(null);
    }

    public AuditLogResponseDto CreateAuditLogs (AuditLogsRequestDto auditLogsRequestDto){

        AuditLogs auditLogs = auditLogsMapper.AuditlogsRequestDtoToAuditLogs(auditLogsRequestDto);
        AuditLogs auditLogsSaved = auditLogsRepository.save(auditLogs);
        return auditLogsMapper.toResponseDto(auditLogsSaved);

    }


}
