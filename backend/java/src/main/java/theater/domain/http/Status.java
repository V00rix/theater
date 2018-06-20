package theater.domain.http;

import com.fasterxml.jackson.annotation.JsonIgnore;
import theater.domain.Seat;
import theater.domain.entities.Client;
import theater.domain.entities.Order;

import java.util.ArrayList;
import java.util.List;

public class Status {
    public Long selectedPerformance;
    public Long selectedSession;
    public Order.Checkout checkout;
    public Client client;
    public List<Seat> selectedSeats = new ArrayList<>();

    // FIXME: 27-May-18 Store status in a session or something

    @JsonIgnore
    public static Status status = new Status();

    public Status() {
    }

    @Override
    public String toString() {
        return String.format("Selected performance: %d; Selected session: %d; Selected checkout: %s;\n", selectedPerformance, selectedSession, checkout);
    }
}
