package theater.domain.http;

import theater.domain.entities.Session;
import theater.domain.enumeration.Availability;

import java.util.List;


public class SessionResponse {
    public Session session;

    public SessionResponse(Session session, List<List<Availability>> seats) {
        this.session = session;
        this.seats = seats;
    }

    public List<List<Availability>> seats;

    public SessionResponse(Session session) {
        this.session = session;
    }

    public SessionResponse() {

    }
}
