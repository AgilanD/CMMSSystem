package cmms.system.service;

import cmms.system.dto.AuditLogResponseDto;
import cmms.system.dto.AuditLogsRequestDto;
import cmms.system.entity.AuditLogs;
import cmms.system.repository.AuditLogsRepository;
import cmms.system.utils.AuditLogsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditLogsServiceImpl implements AuditLogsService{

    private final AuditLogsRepository auditLogsRepository;
    private final AuditLogsMapper auditLogsMapper;

    public List<AuditLogs> getAllAuditLogs(){
        return auditLogsRepository.findAll();
    }
//
//    public AuditLogs getAuditLogsById(Long id){
//        return auditLogsRepository.findById(id).orElse(null);
//    }

    public AuditLogResponseDto createAuditLogs (AuditLogsRequestDto auditLogsRequestDto){
        return auditLogsMapper.toResponseDto(auditLogsRepository.save(auditLogsMapper.AuditlogsRequestDtoToAuditLogs(auditLogsRequestDto)));
    }

}
