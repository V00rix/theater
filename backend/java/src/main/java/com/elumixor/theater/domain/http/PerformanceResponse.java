package com.elumixor.theater.domain.http;

import com.elumixor.theater.domain.entities.Performance;

import java.util.List;

public class PerformanceResponse extends HttpResponse {
    public List<Performance> performances;

    public int maximum_seats = 5;

    public PerformanceResponse(List<Performance> performances) {
        this.performances = performances;
    }


}
