package cmms.System.Dto;

import cmms.System.entity.ServiceHistory.ServiceType;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceHistoryRequestDto {

    @NotNull(message = "Vehicle ID is required")
    private Long vehicleId;

    @NotNull(message = "Service date is required")
    @PastOrPresent(message = "Service date cannot be in the future")
    private LocalDate serviceDate;

    @NotBlank(message = "Service center name cannot be blank")
    private String serviceCenter;

    @NotNull(message = "Service type is required")
    private ServiceType serviceType;

    @NotNull(message = "Cost is required")
    @PositiveOrZero(message = "Cost cannot be negative")
    @Digits(integer = 10, fraction = 2, message = "Invalid cost format")
    private BigDecimal cost;

    private String remarks;
}
