package cmms.system.entity;


import cmms.system.common.entity.Users;
import cmms.system.userContext.UserContext;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE audit_logs SET is_deleted = true, deleted_at = NOW() WHERE id = ?")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performed_by_id", nullable = true)
    private Users performedBy;

    @Column(name = "ip_address")
    private String ipAddress;


    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(name = "created_by", nullable = false)
    private Long createdBy;

    @LastModifiedDate
    @Column(name = "last_modified_at", nullable = false)
    private LocalDateTime lastModifiedAt;

    @LastModifiedBy
    @Column(name = "last_modified_by", nullable = false)
    private Long lastModifiedBy;

    @Builder.Default
    @Column(name = "is_deleted", nullable = false, columnDefinition = "boolean DEFAULT false")
    private boolean deleted = false;

    @PrePersist
    protected void onCreate() {
        this.createdAt =LocalDateTime.now(java.time.ZoneOffset.UTC);
        this.lastModifiedAt = LocalDateTime.now(java.time.ZoneOffset.UTC);

        this.createdBy = UserContext.getUserId();
        this.lastModifiedBy = UserContext.getUserId();
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastModifiedAt = LocalDateTime.now(java.time.ZoneOffset.UTC);
        this.lastModifiedBy = UserContext.getUserId();
    }



    public enum AuditAction {
        CREATE, UPDATE, DELETE, LOGIN_SUCCESS, LOGIN_FAILURE
    }

}
