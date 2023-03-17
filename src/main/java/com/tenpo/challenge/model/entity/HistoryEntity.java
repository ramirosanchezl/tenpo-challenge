package com.tenpo.challenge.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "calculator_history")
public class HistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "request", columnDefinition="TEXT",  nullable = false)
    private String request;

    @Column(name = "response", columnDefinition="TEXT", nullable = true)
    private String response;

    @Column(name = "percentage", columnDefinition="TEXT", nullable = true)
    private String percentage;

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

    public HistoryEntity() {
    }

    public HistoryEntity(String request, String response, String percentage) {
        this.request = request;
        this.response = response;
        this.percentage = percentage;
    }
}
