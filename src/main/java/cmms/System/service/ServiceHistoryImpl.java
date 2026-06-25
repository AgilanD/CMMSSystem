package cmms.System.service;

import cmms.System.Dto.ServiceHistoryRequestDto;
import cmms.System.Dto.ServiceHistoryResponseDto;
import cmms.System.entity.ServiceHistory;
import cmms.System.utils.ServiceHistoryMapper;
import cmms.System.repository.ServiceHistoryRepository;
import cmms.System.repository.VehicleInventoryRepository; // Assuming this exists
import cmms.System.service.ServiceHistoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceHistoryImpl implements ServiceHistoryService {

    private final ServiceHistoryRepository historyRepository;
    private final VehicleInventoryRepository vehicleRepository;
    private final ServiceHistoryMapper historyMapper;

    @Override
    @Transactional
    public ServiceHistoryResponseDto createHistory(ServiceHistoryRequestDto requestDto) {
        ServiceHistory entity = historyMapper.toEntity(requestDto);

        entity.setVehicle(vehicleRepository.findById(requestDto.getVehicleId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found with ID: " + requestDto.getVehicleId())));

        ServiceHistory savedEntity = historyRepository.save(entity);
        return historyMapper.toResponseDto(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServiceHistoryResponseDto> getAllHistory() {
        return historyRepository.findAll().stream()
                .map(historyMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ServiceHistoryResponseDto getHistoryById(Long id) {
        ServiceHistory entity = historyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Service history record not found with ID: " + id));
        return historyMapper.toResponseDto(entity);
    }

    @Override
    @Transactional
    public ServiceHistoryResponseDto updateHistory(Long id, ServiceHistoryRequestDto requestDto) {
        ServiceHistory existingEntity = historyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Service history record not found with ID: " + id));

        existingEntity.setServiceDate(requestDto.getServiceDate());
        existingEntity.setServiceCenter(requestDto.getServiceCenter());
        existingEntity.setServiceType(requestDto.getServiceType());
        existingEntity.setCost(requestDto.getCost());
        existingEntity.setRemarks(requestDto.getRemarks());

        existingEntity.setVehicle(vehicleRepository.findById(requestDto.getVehicleId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found with ID: " + requestDto.getVehicleId())));

        ServiceHistory updatedEntity = historyRepository.save(existingEntity);
        return historyMapper.toResponseDto(updatedEntity);
    }

    @Override
    @Transactional
    public void deleteHistory(Long id) {
        if (!historyRepository.existsById(id)) {
            throw new EntityNotFoundException("Service history record not found with ID: " + id);
        }
        historyRepository.deleteById(id);
    }
}
