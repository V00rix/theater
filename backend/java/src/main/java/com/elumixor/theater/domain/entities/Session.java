package com.elumixor.theater.domain.entities;

import com.elumixor.theater.domain.enumeration.Availability;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "t_session")
public class Session implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "date", nullable = false)
    private Timestamp date;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hall", nullable = false)
    @JsonIgnore
    public Hall hall;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Performance performance;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "session")
    @JsonIgnore
    public Set<Seat> seatsRef = new HashSet<>();

    public String getDate() {
        return date.toString();
    }

    public String getHall() {
        return hall.toString();
    }

    public List<List<Availability>> getSeats() {
        var rows = new ArrayList<List<Availability>>();

        this.hall.rows.forEach(r -> {
            var row = new ArrayList<Availability>();

            for (int i = 0; i < r.seat_number; i++) {
                row.add(Availability.FREE);
            }

            rows.add(row);
        });

        this.seatsRef.forEach(seat -> {
            rows.get(seat.rowRef.number - 1).set(seat.number - 1, seat.availability);
        });

        return rows;
    }
}
