package com.elumixor.theater.domain.http;

import com.elumixor.theater.domain.SelectedSeat;
import com.elumixor.theater.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class Status {
    public int selected_performance;
    public int selected_session;
    public List<SelectedSeat> selected_seats;
    public String selected_checkout;
    public User user;

    // FIXME: 27-May-18 Store status in a session or something

    @JsonIgnore
    public static Status status = new Status();

    Status() {
        this.selected_seats = new ArrayList<>();
    }

    @Override
    public String toString() {
        selected_seats.forEach(System.out::println);
        return String.format("Selected performance: %d; Selected session: %d; Selected checkout: %s;\n", selected_performance, selected_session, selected_checkout);
    }
}
