package com.tenpo.challenge.services;
import com.tenpo.challenge.exception.PercentageClientException;
import com.tenpo.challenge.exception.PercentageNotFoundException;
import com.tenpo.challenge.service.CalculateService;
import com.tenpo.challenge.service.HistoryService;
import com.tenpo.challenge.service.PercentageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CalculateServiceTest {

    @Mock
    private PercentageService percentageService;
    @Mock
    private HistoryService HistoryService;
    @InjectMocks
    private CalculateService calculateService;

    @Test
    public void calculateOk() throws PercentageNotFoundException {
        BigDecimal valueA = new BigDecimal(5);
        BigDecimal valueB = new BigDecimal(5);

        BigDecimal percentage = new BigDecimal(10);

        when(percentageService.getPercentage()).thenReturn(percentage);
        doNothing().when(HistoryService).saveOperation("{ (5 + 5)}","{ (5 + 5) + 10% = 11.0 }","10");

        String result = calculateService.calculate(valueA, valueB);

        assertTrue(result.equals("{ (5 + 5) + 10% = 11.0 }"));
    }


    @Test
    public void calculateErrorMock() throws PercentageNotFoundException {
        BigDecimal valueA = new BigDecimal(5);
        BigDecimal valueB = new BigDecimal(5);

        BigDecimal percentage = new BigDecimal(10);

        when(percentageService.getPercentage()).thenThrow(new PercentageClientException());

        assertThrows(PercentageNotFoundException.class, () -> calculateService.calculate(valueA, valueB));

    }
}
