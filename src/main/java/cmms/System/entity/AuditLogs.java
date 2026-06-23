package cmms.System.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditLogs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "table_name", nullable = false)
    private String tableName;

    @Column(name = "record_id", nullable = false)
    private Long recordId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuditAction action;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "changed_data", columnDefinition = "jsonb")
    private String changedData;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "performed_by_id", nullable = true)
//    private Users performedBy;

    @Column(name = "ip_address")
    private String ipAddress;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public enum AuditAction {
        CREATE, UPDATE, DELETE, LOGIN_SUCCESS, LOGIN_FAILURE
    }

}
