package cmms.System.service;

import cmms.System.Dto.ServiceHistoryRequestDto;
import cmms.System.Dto.ServiceHistoryResponseDto;
import java.util.List;

public interface ServiceHistoryService {
    ServiceHistoryResponseDto createHistory(ServiceHistoryRequestDto requestDto);
    List<ServiceHistoryResponseDto> getAllHistory();
    ServiceHistoryResponseDto getHistoryById(Long id);
    ServiceHistoryResponseDto updateHistory(Long id, ServiceHistoryRequestDto requestDto);
    void deleteHistory(Long id);
}
