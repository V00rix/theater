package theater.domain.http;


import theater.domain.entities.Performance;

import java.util.List;

public class PerformanceResponse {
    public List<Performance> performances;

    public int maximum_seats = 5;

    public PerformanceResponse(List<Performance> performances) {
        this.performances = performances;
    }


}
