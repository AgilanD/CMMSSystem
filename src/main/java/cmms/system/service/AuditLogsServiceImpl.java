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

    public List<AuditLogResponseDto> getAllAuditLogs() {
        return auditLogsRepository.findAll()
                .stream()
                .map(auditLogsMapper::toResponseDto)
                .toList();
    }

    public AuditLogResponseDto getAuditLogsById(Long id){
        AuditLogs auditLogs = auditLogsRepository.findById(id).orElse(null);
        return auditLogsMapper.toResponseDto(auditLogs);
    }

    @Override
    public AuditLogResponseDto createAuditLogs(AuditLogsRequestDto auditLogsRequestDto) {

        AuditLogs auditLogEntity = auditLogsMapper.auditlogsRequestDtoToAuditLogs(auditLogsRequestDto);
        if (auditLogsRequestDto.getPerformedById() != null) {
            auditLogEntity.setCreatedBy(auditLogsRequestDto.getPerformedById());
            auditLogEntity.setLastModifiedBy(auditLogsRequestDto.getPerformedById());
        }

        AuditLogs savedEntity = auditLogsRepository.save(auditLogEntity);
        return auditLogsMapper.toResponseDto(savedEntity);
    }

}
