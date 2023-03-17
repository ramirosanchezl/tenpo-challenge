package com.tenpo.challenge.service;

import com.tenpo.challenge.model.HistoryResponse;
import com.tenpo.challenge.model.dto.HistoryDTO;
import com.tenpo.challenge.model.entity.HistoryEntity;
import com.tenpo.challenge.repository.HistoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoryService {
    private static final Logger log = LoggerFactory.getLogger(HistoryService.class);

    @Autowired
    private HistoryRepository historyRepository;

    @Async
    public void saveOperation(String request, String response, String percentage){
        try
        {
            HistoryEntity operation = new HistoryEntity(request, response, percentage);

            historyRepository.save(operation);
        }
        catch (Exception e){
            log.error(String.format("Error saving operation: %s", e.getMessage()));
        }
    }

    public HistoryResponse getHistory(Integer page){

        PageRequest pageRequest = PageRequest.of(page == null ? 0 : page,5 );

        Page<HistoryEntity> historySaved = historyRepository.findAll(pageRequest);

        return getResponse(historySaved, pageRequest.getPageNumber());
    }

    private HistoryResponse getResponse(Page<HistoryEntity> response, Integer page){
        log.info("Response:", response);
        if(response.getTotalElements() > 0) {
            List<HistoryDTO> history = response.stream().map(r -> new HistoryDTO(r.getId(),r.getRequest(),r.getResponse(), r.getPercentage()))
                    .collect(Collectors.toList());

            return new HistoryResponse(history,page,response.getTotalPages());
        }

        return new HistoryResponse(new ArrayList<>(),page,0);
    }

    public HistoryDTO getLastHistory(){
        HistoryEntity lastHistorySaved = historyRepository.findTopByOrderByIdDesc();
        return new HistoryDTO(null, "", "",lastHistorySaved.getPercentage());
    }
}
