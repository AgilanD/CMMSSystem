package cmms.System.Dto;


import cmms.System.entity.AuditLogs;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditLogsRequestDto {

    private String tableName;

    private Long recordId;

    private AuditLogs.AuditAction action;

    private String changedData;

    private Long performedById;

    private String ipAddress;



}
