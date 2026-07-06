package cmms.system.entity;

import cmms.system.common.entity.VehicleInventory;

import cmms.system.userContext.UserContext;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "service_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id", nullable = false)
    private VehicleInventory vehicle;

    @Temporal(TemporalType.DATE)
    @Column(name = "service_date", nullable = false)
    private LocalDate serviceDate;

    @Column(name = "service_center", nullable = false)
    private String serviceCenter;

    @Enumerated(EnumType.STRING)
    @Column(name = "service_type", nullable = false)
    private ServiceType serviceType;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal cost;

    @Column(columnDefinition = "text")
    private String remarks;

    @CreatedDate
    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT '2026-06-23 19:54:30'")
    private LocalDateTime createdAt = LocalDateTime.parse("2026-06-23T19:54:30");

    @CreatedBy
    @Column(name = "created_by", nullable = false, columnDefinition = "BIGINT DEFAULT 1")
    private Long createdBy = 1L;

    @LastModifiedDate
    @Column(name = "last_modified_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT '2026-06-23 19:54:30'")
    private LocalDateTime lastModifiedAt = LocalDateTime.parse("2026-06-23T19:54:30");

    @LastModifiedBy
    @Column(name = "last_modified_by", nullable = false, columnDefinition = "BIGINT DEFAULT 1")
    private Long lastModifiedBy = 1L;


    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now(java.time.ZoneOffset.UTC);
        this.lastModifiedAt = LocalDateTime.now(java.time.ZoneOffset.UTC);

        this.createdBy = UserContext.getUserId();
        this.lastModifiedBy = UserContext.getUserId();
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastModifiedAt = LocalDateTime.now(java.time.ZoneOffset.UTC);
        this.lastModifiedBy = UserContext.getUserId();
    }



    public enum ServiceType {
        ROUTINE, WARRANTY, REPAIR, RECALL
    }


}
