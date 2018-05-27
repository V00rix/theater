package com.elumixor.theater.domain.http;

import com.elumixor.theater.domain.entities.Performance;

import java.util.List;

public class PerformanceResponse extends HttpResponse {
    public List<Performance> performances;

    public PerformanceResponse(List<Performance> performances) {
        this.performances = performances;
    }


}
