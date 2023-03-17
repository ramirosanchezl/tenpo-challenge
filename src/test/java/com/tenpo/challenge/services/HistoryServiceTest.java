package com.tenpo.challenge.services;
import com.tenpo.challenge.exception.PercentageNotFoundException;
import com.tenpo.challenge.model.entity.HistoryEntity;
import com.tenpo.challenge.repository.HistoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HistoryServiceTest {

    @Mock
    private HistoryRepository historyRepository;

    @Test
    public void SaveOk() throws PercentageNotFoundException {
        when(historyRepository.save(any())).thenReturn(any());
        HistoryEntity operation = new HistoryEntity("Some request","Some response","Some percentage");
        historyRepository.save(operation);
    }

    @Test
    public void GetLastHistoryOk() throws Exception {
        HistoryEntity entity = new HistoryEntity("Some request","Some response","Some percentage");
        when(historyRepository.findTopByOrderByIdDesc()).thenReturn(entity);
        HistoryEntity result = historyRepository.findTopByOrderByIdDesc();
        assertTrue(result.equals(entity));
    }

}
