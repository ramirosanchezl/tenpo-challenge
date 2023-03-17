package com.tenpo.challenge.model.dto;

public class HistoryDTO {
    private Long id;

    private String request;

    private String response;


    private String percentage;

    public HistoryDTO(Long id, String request, String response, String percentage) {
        this.id = id;
        this.request = request;
        this.response = response;
        this.percentage = percentage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }
}
