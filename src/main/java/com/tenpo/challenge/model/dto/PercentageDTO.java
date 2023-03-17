package com.tenpo.challenge.model.dto;

public class PercentageDTO {

    private Double number1;
    private Double number2;
    private Integer percentage;
    private Double result;

    public PercentageDTO(Double number1, Double number2, Integer percentage, Double result) {
        this.number1 = number1;
        this.number2 = number2;
        this.percentage = percentage;
        this.result = result;
    }

    public Double getNumber1() {
        return number1;
    }

    public void setNumber1(Double number1) {
        this.number1 = number1;
    }

    public Double getNumber2() {
        return number2;
    }

    public void setNumber2(Double number2) {
        this.number2 = number2;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

}
