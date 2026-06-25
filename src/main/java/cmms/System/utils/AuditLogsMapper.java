package cmms.System.utils;

import cmms.System.Dto.AuditLogResponseDto;
import cmms.System.Dto.AuditLogsRequestDto;
import cmms.System.entity.AuditLogs;
import cmms.System.entity.AuditLogs.AuditAction;
import org.springframework.stereotype.Component;

@Component
public class AuditLogsMapper {

    public  AuditLogs AuditlogsRequestDtoToAuditLogs (AuditLogsRequestDto dto) {

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
                .performedById(entity.getPerformedBy() != null ? entity.getPerformedBy().getId() : null)
                .performedByUsername(entity.getPerformedBy() != null ? entity.getPerformedBy().getUsername() : null)
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .lastModifiedAt(entity.getLastModifiedAt())
                .lastModifiedBy(entity.getLastModifiedBy())
                .build();
    }


}
