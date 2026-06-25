package cmms.System.service;

import cmms.System.Dto.ProductionOrderRequestDto;
import cmms.System.Dto.ProductionOrderResponseDto;

import java.util.List;

public interface ProductionOrderService {

    ProductionOrderResponseDto createOrder(ProductionOrderRequestDto requestDto);
    List<ProductionOrderResponseDto> getAllOrders();
    ProductionOrderResponseDto getOrderById(Long id);
    ProductionOrderResponseDto updateOrder(Long id, ProductionOrderRequestDto requestDto);
    void deleteOrder(Long id);

}
