package com.tenpo.challenge.model;

import com.tenpo.challenge.model.dto.HistoryDTO;

import java.util.List;

public class HistoryResponse {

    private List<HistoryDTO> history;

    private Integer page;

    private Integer totalPages;

    public HistoryResponse(List<HistoryDTO> history, Integer page, Integer totalPages) {
        this.history = history;
        this.page = page;
        this.totalPages = totalPages;
    }

    public HistoryResponse() {
    }

    public List<HistoryDTO> getHistory() {
        return history;
    }

    public void setHistory(List<HistoryDTO> history) {
        this.history = history;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
