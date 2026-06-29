package cmms.System.utils;

import cmms.System.Dto.ServiceHistoryRequestDto;
import cmms.System.Dto.ServiceHistoryResponseDto;
import cmms.System.entity.ServiceHistory;
import org.springframework.stereotype.Component;

@Component
public class ServiceHistoryMapper {

    public ServiceHistory toEntity(ServiceHistoryRequestDto dto) {
        if (dto == null) return null;

        return ServiceHistory.builder()
                .serviceDate(dto.getServiceDate())
                .serviceCenter(dto.getServiceCenter())
                .serviceType(dto.getServiceType())
                .cost(dto.getCost())
                .remarks(dto.getRemarks())
                .build();
    }

    public ServiceHistoryResponseDto toResponseDto(ServiceHistory entity) {
        if (entity == null) return null;

        return ServiceHistoryResponseDto.builder()
                .id(entity.getId())
                .vehicleId(entity.getVehicle() != null ? entity.getVehicle().getId() : null)
                .serviceDate(entity.getServiceDate())
                .serviceCenter(entity.getServiceCenter())
                .serviceType(entity.getServiceType())
                .cost(entity.getCost())
                .remarks(entity.getRemarks())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .lastModifiedAt(entity.getLastModifiedAt())
                .lastModifiedBy(entity.getLastModifiedBy())
                .build();
    }

}
