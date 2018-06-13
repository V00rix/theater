package theater.domain.http;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Status {
    public int selected_performance;
    public int selected_session;
    public String selected_checkout;

    // FIXME: 27-May-18 Store status in a session or something

    @JsonIgnore
    public static Status status = new Status();

    public Status() {
    }

    @Override
    public String toString() {
        return String.format("Selected performance: %d; Selected session: %d; Selected checkout: %s;\n", selected_performance, selected_session, selected_checkout);
    }
}
