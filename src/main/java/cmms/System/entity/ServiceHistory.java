package cmms.System.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

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

    // Many service logs can be tied to a single Vehicle (linked via vehicle's primary key or custom join column)
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
    private BigDecimal cost; // Service layer must validate cost >= 0

    @Column(columnDefinition = "text")
    private String remarks;


    public enum ServiceType {
        ROUTINE, WARRANTY, REPAIR, RECALL
    }
}
