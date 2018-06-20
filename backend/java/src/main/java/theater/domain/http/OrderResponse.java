package theater.domain.http;

import theater.domain.Seat;
import theater.domain.entities.Client;

import java.sql.Timestamp;
import java.util.List;

import static theater.domain.entities.Order.Checkout;

public class OrderResponse {

        public Long id;

        public String code;

        public Checkout checkout;

        public Client client;

        public String performance;

        public Timestamp session;

        public List<Seat> seats;

    public OrderResponse() {
    }

    public OrderResponse(Long id, String code, Checkout checkout, Client client, String performance, Timestamp session, List<Seat> seats) {
        this.id = id;
        this.code = code;
        this.checkout = checkout;
        this.client = client;
        this.performance = performance;
        this.session = session;
        this.seats = seats;
    }
}
