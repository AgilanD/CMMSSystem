package cmms.System.common.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import cmms.System.security.GatewayHeaderAuthFilter;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 150)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;


    @CreatedDate
    @Builder.Default
    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT '2026-06-23 19:54:30'")
    private LocalDateTime createdAt = LocalDateTime.parse("2026-06-23T19:54:30");

    @CreatedBy
    @Builder.Default
    @Column(name = "created_by", nullable = false, columnDefinition = "BIGINT DEFAULT 1")
    private Long createdBy = 1L;

    @LastModifiedDate
    @Builder.Default
    @Column(name = "last_modified_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT '2026-06-23 19:54:30'")
    private LocalDateTime lastModifiedAt = LocalDateTime.parse("2026-06-23T19:54:30");

    @LastModifiedBy
    @Builder.Default
    @Column(name = "last_modified_by", nullable = false, columnDefinition = "BIGINT DEFAULT 1")
    private Long lastModifiedBy = 1L;


    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.lastModifiedAt = LocalDateTime.now();
        this.createdBy = Long.valueOf(GatewayHeaderAuthFilter.username);
        this.lastModifiedBy =Long.valueOf(GatewayHeaderAuthFilter.username);
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastModifiedAt = LocalDateTime.now();
        this.lastModifiedBy = Long.valueOf(GatewayHeaderAuthFilter.username);
    }

    public enum UserRole {
        ADMIN,
        PLANT_MANAGER,
        SUPERVISOR
    }


}
