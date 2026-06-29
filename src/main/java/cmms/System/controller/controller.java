package cmms.System.controller;


import cmms.System.Dto.*;
import cmms.System.entity.AuditLogs;
import cmms.System.service.AuditLogsService;
import cmms.System.service.ProductionOrderService;
import cmms.System.service.ServiceHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/System")
public class controller {

    private final AuditLogsService  auditLogsService;

    private final ProductionOrderService orderService;

    private final ServiceHistoryService historyService;

    @GetMapping("/checkings")
    public String Checkings(){
        return "Hello all I am validations Message in System Service ";
    }

    @GetMapping("/GetAllAuditLogs")
    public List<AuditLogs> GetAllAuditLogs(){
        return auditLogsService.GetAllAuditLogs();
    }

    @PostMapping("/AddAuditLogs")
    public AuditLogResponseDto createAuditLog( @RequestBody AuditLogsRequestDto requestDto) {
        AuditLogResponseDto createdLog = auditLogsService.CreateAuditLogs(requestDto);
        return createdLog;
    }



    @PostMapping("/CreateOrder")
    public ProductionOrderResponseDto createOrder(@RequestBody ProductionOrderRequestDto requestDto) {
        ProductionOrderResponseDto createdOrder = orderService.createOrder(requestDto);
        return createdOrder;
    }

    @GetMapping("/GetAllOrder")
    public List<ProductionOrderResponseDto> getAllOrders() {
        List<ProductionOrderResponseDto> orders = orderService.getAllOrders();
        return orders;
    }

    @GetMapping("/GetOrderByid/{id}")
    public ProductionOrderResponseDto getOrderById(@PathVariable Long id) {
        ProductionOrderResponseDto order = orderService.getOrderById(id);
        return order;
    }

    @PutMapping("/UpdateOrder/{id}")
    public ProductionOrderResponseDto updateOrder(
            @PathVariable Long id,
             @RequestBody ProductionOrderRequestDto requestDto) {
        ProductionOrderResponseDto updatedOrder = orderService.updateOrder(id, requestDto);
        return updatedOrder;
    }

    @DeleteMapping("/DeleteOrder/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }



    @PostMapping("/CreateHistory")
    public ServiceHistoryResponseDto createHistory( @RequestBody ServiceHistoryRequestDto requestDto) {
        ServiceHistoryResponseDto createdHistory = historyService.createHistory(requestDto);
        return createdHistory;
    }

    @GetMapping("/GetAllHistory")
    public List<ServiceHistoryResponseDto> getAllHistories() {
        List<ServiceHistoryResponseDto> histories = historyService.getAllHistory();
        return histories;
    }

    @GetMapping("/GetHistoryById/{id}")
    public ServiceHistoryResponseDto getHistoryById(@PathVariable Long id) {
        ServiceHistoryResponseDto history = historyService.getHistoryById(id);
        return history;
    }

    @PutMapping("UpdateHistoryById/{id}")
    public ServiceHistoryResponseDto updateHistory(
            @PathVariable Long id,
            @RequestBody ServiceHistoryRequestDto requestDto) {
        ServiceHistoryResponseDto updatedHistory = historyService.updateHistory(id, requestDto);
        return updatedHistory;
    }

    @DeleteMapping("/deleteHistory/{id}")
    public void deleteHistory(@PathVariable Long id) {
        historyService.deleteHistory(id);
    }

}
