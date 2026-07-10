package cmms.system.utils;

import cmms.system.dto.AuditLogResponseDto;
import cmms.system.dto.AuditLogsRequestDto;
import cmms.system.entity.AuditLogs;
import org.springframework.stereotype.Component;

@Component
public class AuditLogsMapper {

    public  AuditLogs auditlogsRequestDtoToAuditLogs (AuditLogsRequestDto dto) {

        if (dto == null) {
            return null;
        }

        return AuditLogs.builder()
                .tableName(dto.getTableName())
                .recordId(dto.getRecordId())
                .action(dto.getAction())
                .changedData(dto.getChangedData())
                .ipAddress(dto.getIpAddress())
                .build();
    }


    public AuditLogResponseDto toResponseDto(AuditLogs entity) {

        if (entity == null) {
            return null;
        }

        return AuditLogResponseDto.builder()
                .id(entity.getId())
                .tableName(entity.getTableName())
                .recordId(entity.getRecordId())
                .action(entity.getAction())
                .changedData(entity.getChangedData())
                .ipAddress(entity.getIpAddress())
                .performedById(entity.getPerformedBy() != null ? entity.getPerformedBy() : null)
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .lastModifiedAt(entity.getLastModifiedAt())
                .lastModifiedBy(entity.getLastModifiedBy())
                .build();
    }

    public void updateEntityFromDto(AuditLogsRequestDto dto, AuditLogs entity) {
        if (dto == null || entity == null) {
            return;
        }
        entity.setTableName(dto.getTableName());
        entity.setRecordId(dto.getRecordId());
        entity.setAction(dto.getAction());
        entity.setChangedData(dto.getChangedData());
        entity.setIpAddress(dto.getIpAddress());
    }


}
