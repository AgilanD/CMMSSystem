package cmms.System.Dto;

import cmms.System.entity.ServiceHistory.ServiceType;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceHistoryResponseDto {

    private Long id;
    private Long vehicleId;
    private LocalDate serviceDate;
    private String serviceCenter;
    private ServiceType serviceType;
    private BigDecimal cost;
    private String remarks;


    private LocalDateTime createdAt;
    private Long createdBy;
    private LocalDateTime lastModifiedAt;
    private Long lastModifiedBy;

}
