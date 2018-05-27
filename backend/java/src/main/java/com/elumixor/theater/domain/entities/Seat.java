package com.elumixor.theater.domain.entities;

import com.elumixor.theater.domain.enumeration.Availability;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_seat")
public class Seat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public int number;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "row", nullable = false)
    @JsonIgnore
    public Row rowRef;

    @Enumerated(EnumType.STRING)
    public Availability availability;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "\"order\"", nullable = false)
    @JsonIgnore
    public Order order;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "session", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Session session;

    public Seat() {}
    public Seat(Order order, Row rowRef, Session session, int number) {
        this(order, rowRef, session, number, Availability.BOOKED);
    }
    public Seat(Order order, Row rowRef, Session session, int number, Availability availability) {
        this.order = order;
        this.rowRef = rowRef;
        this.session = session;
        this.number = number;
        this.availability = availability;
    }

    public int getRow() {
        return rowRef.number;
    }

    public void print() {
        rowRef.print();
        order.print();
        session.print();
        System.out.println(this.availability);
    }
}
