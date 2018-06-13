package theater.domain.http;


import theater.domain.entities.Session;

import java.util.List;

public class SessionResponse {
    public List<Session> sessions;

    public SessionResponse(List<Session> sessions) {
        this.sessions = sessions;
    }

    public SessionResponse() {

    }
}
