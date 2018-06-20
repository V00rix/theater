package theater.domain.entities;

import theater.domain.Seat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_order")
public class Order extends EntityBase<Order> implements Serializable {
    //region Fields
    public boolean confirmed = false;

    @Enumerated(EnumType.STRING)
    public Checkout checkout;

    @Column(name = "created_on", nullable = false)
    public Timestamp createdOn;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "session")
    public Session session;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client", nullable = false)
    public Client client;

    @Transient
    private List<theater.domain.Seat> seats = new ArrayList<>();

    @Lob
    @Column(name = "seats")
    private byte[] seatsBytes = new byte[0];

    public List<theater.domain.Seat> getSeats() {
        return seats = theater.domain.Seat.fromBytes(seatsBytes);
    }

    public void setSeats(List<theater.domain.Seat> seats) {
        this.seats = seats;
        seatsBytes = theater.domain.Seat.toBytes(this.seats);
    }
    //endregion

    //region Get/setters
    public Long getClient() {
        return client.getId();
    }

    public void setClient(Integer client) {
        this.client = new Client();
        this.client.setId(client.longValue());
    }

    public Long getSession() {
        return session.getId();
    }

    public void setSession(Long session) {
        this.session = new Session();
        this.session.setId(session);
    }
    //endregion

    //region Constructors
    public Order(Session session, Client client, Checkout checkout) {
        this();
        this.session = session;
        this.client = client;
        this.checkout = checkout;
    }

    public Order(Checkout checkout, Session session, Client client, List<Seat> seats) {
        this(session, client, checkout);
        this.setSeats(seats);
    }

    public Order(Checkout checkout, Session session, Client client, List<Seat> seats, Boolean confirmed) {
        this(checkout, session, client, seats);
        this.confirmed = confirmed;
    }

    private Order() {
        this.createdOn = new Timestamp(System.currentTimeMillis());
    }
    //endregion

    //region Implementation
    @Override
    public void print() {
        System.out.println("Order (" + id + "). Created on " + createdOn
                + ". Checkout: " + checkout + ". Confirmed: " + confirmed);
        client.print();
        session.print();
    }

    @Override
    public boolean equalz(Order another) {
        var confirmedEqual = false;
        confirmedEqual = confirmed == another.confirmed;

        return confirmedEqual && checkout.equals(another.checkout)
                && createdOn.equals(another.createdOn) && session.equalz(another.session)
                && client.equalz(another.client);
    }

    @Override
    public void update(Order another) {
        confirmed = another.confirmed;
        checkout = another.checkout;
        createdOn = another.createdOn;
        client.update(another.client);
        session.update(another.session);
        seats = new ArrayList<>();
        try {
            for (Seat s : another.seats) {
                seats.add(s.clone());
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
    //endregion

    public static enum Checkout {
        DELIVERY,
        SELF_CHECKOUT,
        PAY_BEFORE
    }
}

