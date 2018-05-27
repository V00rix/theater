package com.elumixor.theater.domain.http;

import com.elumixor.theater.domain.SelectedSeat;
import com.elumixor.theater.domain.User;

public class StatusResponse {
    public int selected_performance;
    public int selected_session;
    public SelectedSeat selected_seats;
    public String selected_checkout;
    public User user;
}
