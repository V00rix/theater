package theater.domain.http;


import theater.domain.entities.Performance;

import java.util.Map;

public class PerformanceResponse {
    public Map<Integer, Performance> performances;

    public int maximumSeats = 5;

    public PerformanceResponse(Map<Integer, Performance> performances) {
        this.performances = performances;
    }

    public PerformanceResponse(Map<Integer, Performance> performances, int maximum_seats) {
        this.performances = performances;
        this.maximumSeats = maximum_seats;
    }
}
