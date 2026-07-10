package cmms.system.service;

import cmms.system.dto.ServiceHistoryRequestDto;
import cmms.system.dto.ServiceHistoryResponseDto;
import cmms.system.entity.ServiceHistory;
import cmms.system.common.entity.VehicleInventory;
import cmms.system.utils.ServiceHistoryMapper;
import cmms.system.repository.ServiceHistoryRepository;
import cmms.system.common.entity.repository.VehicleInventoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceHistoryImpl implements ServiceHistoryService {

    private final ServiceHistoryRepository historyRepository;
    private final VehicleInventoryRepository vehicleRepository;
    private final ServiceHistoryMapper historyMapper;

    @Override
    @Transactional
    public ServiceHistoryResponseDto createHistory(ServiceHistoryRequestDto requestDto) {

        VehicleInventory vehicle = vehicleRepository.findById(requestDto.getVehicleId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found with ID: " + requestDto.getVehicleId()));

        if (vehicle.getStatus() == null || !"DELIVERED".equalsIgnoreCase(String.valueOf(vehicle.getStatus()))) {
            throw new IllegalStateException("Service records can only be created for vehicles with DELIVERED status. Current status: " + vehicle.getStatus());
        }

        ServiceHistory entity = historyMapper.toEntity(requestDto);
        entity.setVehicle(vehicle);

        ServiceHistory savedEntity = historyRepository.save(entity);
        return historyMapper.toResponseDto(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServiceHistoryResponseDto> getAllHistory() {
        return historyRepository.findAll().stream()
                .map(historyMapper::toResponseDto).toList();
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

        VehicleInventory vehicle = vehicleRepository.findById(requestDto.getVehicleId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found with ID: " + requestDto.getVehicleId()));


        if (vehicle.getStatus() == null || !"DELIVERED".equalsIgnoreCase(String.valueOf(vehicle.getStatus()))) {
            throw new IllegalStateException("Service records can only be linked to vehicles with DELIVERED status. Current status: " + vehicle.getStatus());
        }

        existingEntity.setVehicle(vehicle);

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