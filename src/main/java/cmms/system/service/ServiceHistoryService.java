package cmms.system.service;

import cmms.system.dto.ServiceHistoryRequestDto;
import cmms.system.dto.ServiceHistoryResponseDto;
import java.util.List;

public interface ServiceHistoryService {
    ServiceHistoryResponseDto createHistory(ServiceHistoryRequestDto requestDto);
    List<ServiceHistoryResponseDto> getAllHistory();
    ServiceHistoryResponseDto getHistoryById(Long id);
    ServiceHistoryResponseDto updateHistory(Long id, ServiceHistoryRequestDto requestDto);
    void deleteHistory(Long id);
}
