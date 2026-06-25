package cmms.System.service;


import cmms.System.Dto.ProductionOrderRequestDto;
import cmms.System.Dto.ProductionOrderResponseDto;
import cmms.System.entity.ProductionOrder;
import cmms.System.repository.CarModuleRepository;
import cmms.System.repository.PlantsRepository;
import cmms.System.repository.ProductOrderRepository;
import cmms.System.utils.ProductionOrderMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductionOrderServiceImpl implements ProductionOrderService{

    private final ProductOrderRepository orderRepository;
    private final PlantsRepository plantsRepository;
    private final CarModuleRepository carModuleRepository;
    private final ProductionOrderMapper orderMapper;

    @Override
    @Transactional
    public ProductionOrderResponseDto createOrder(ProductionOrderRequestDto requestDto) {
        ProductionOrder entity = orderMapper.toEntity(requestDto);

        entity.setPlant(plantsRepository.findById(requestDto.getPlantId())
                .orElseThrow(() -> new EntityNotFoundException("Plant not found with ID: " + requestDto.getPlantId())));

        entity.setCarModel(carModuleRepository.findById(requestDto.getCarModelId())
                .orElseThrow(() -> new EntityNotFoundException("Car Model not found with ID: " + requestDto.getCarModelId())));

        log.info(entity.toString());
        ProductionOrder savedEntity = orderRepository.saveAndFlush(entity);

        return orderMapper.toResponseDto(savedEntity);
    }

    @Override
    public List<ProductionOrderResponseDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductionOrderResponseDto getOrderById(Long id) {
        ProductionOrder entity = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Production Order not found with ID: " + id));
        return orderMapper.toResponseDto(entity);
    }

    @Override
    @Transactional
    public ProductionOrderResponseDto updateOrder(Long id, ProductionOrderRequestDto requestDto) {
        ProductionOrder existingEntity = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Production Order not found with ID: " + id));

        existingEntity.setStatus(requestDto.getStatus());
        existingEntity.setTargetQuantity(requestDto.getTargetQuantity());
        if (requestDto.getCompletedQuantity() != null) {
            existingEntity.setCompletedQuantity(requestDto.getCompletedQuantity());
        }
        existingEntity.setExpectedEndDate(requestDto.getExpectedEndDate());
        existingEntity.setActualEndDate(requestDto.getActualEndDate());

        existingEntity.setPlant(plantsRepository.findById(requestDto.getPlantId())
                .orElseThrow(() -> new EntityNotFoundException("Plant not found with ID: " + requestDto.getPlantId())));

        existingEntity.setCarModel(carModuleRepository.findById(requestDto.getCarModelId())
                .orElseThrow(() -> new EntityNotFoundException("Car Model not found with ID: " + requestDto.getCarModelId())));

        ProductionOrder updatedEntity = orderRepository.save(existingEntity);
        return orderMapper.toResponseDto(updatedEntity);
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new EntityNotFoundException("Production Order not found with ID: " + id);
        }
        orderRepository.deleteById(id);
    }



}
