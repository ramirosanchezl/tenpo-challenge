package com.tenpo.challenge.service;

import com.tenpo.challenge.exception.PercentageClientException;
import com.tenpo.challenge.exception.PercentageNotFoundException;
import com.tenpo.challenge.model.dto.HistoryDTO;
import com.tenpo.challenge.model.entity.HistoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculateService {

    @Autowired
    private PercentageService percentageService;
    @Autowired
    private HistoryService historyService;

    public String calculate(BigDecimal valueA, BigDecimal valueB) throws PercentageNotFoundException {

        BigDecimal percentage = getPercentage();

        BigDecimal sum = valueA.add(valueB);
        BigDecimal result = sum.multiply(new BigDecimal(1).add(percentage.divide(new BigDecimal(100))));

        String request = String.format("{ (%s + %s)}", valueA, valueB);
        String response = String.format("{ (%s + %s) + %s%% = %s }", valueA, valueB, percentage, result);
        historyService.saveOperation(request,response, percentage.toString());
        return response;
    }

    private BigDecimal getPercentage() throws PercentageNotFoundException{
        try {
            return percentageService.getPercentage();
        }
        catch (PercentageClientException e){
            HistoryDTO lastPercentageSaved = historyService.getLastHistory();
           if(lastPercentageSaved != null){
               return new BigDecimal(lastPercentageSaved.getPercentage());
           }
           throw new PercentageNotFoundException();
       }
    }
}
