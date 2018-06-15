package theater.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_order")
public class Order extends EntityBase<Order> implements Serializable {
    //region Fields
    public Boolean confirmed;

    @Enumerated(EnumType.STRING)
    public Checkout checkout;

    @Column(name = "created_on", nullable = false)
    public Timestamp createdOn;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session", nullable = false)
    public Session session;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client", nullable = false)
    public Client client;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "order")
    public List<Seat> seats = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(value = "code")
    private Long id;
    //endregion

    //region Constructors
    public Order(Session session, Client client, Checkout checkout) {
        this();
        this.session = session;
        this.client = client;
        this.checkout = checkout;
    }

    private Order() {
        this.createdOn = new Timestamp(System.currentTimeMillis());
    }
    //endregion

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
        if (confirmed == null) {
            confirmedEqual = another.confirmed == null;
        } else {
            confirmedEqual = confirmed.equals(another.confirmed);
        }

        return confirmedEqual && checkout.equals(another.checkout)
                && createdOn.equals(another.createdOn) && session.equalz(another.session)
                && client.equalz(another.client);
    }

    @Override
    public void copy(Order another) {
        confirmed = another.confirmed;
        checkout = another.checkout;
        createdOn = another.createdOn;
        client.copy(another.client);
        session.copy(another.session);
        seats = new ArrayList<>();
        try {
            for (Seat s : another.seats) {
                seats.add(s.clone());
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public static enum Checkout {
        DELIVERY, SELF_CHECKOUT, PAY_BEFORE
    }

    @Entity
    @Table(name = "t_order_seat")
    public static class Seat implements Serializable, Cloneable {
        @Id
        public int row;

        @Id
        public int seat;

        @Override
        protected Seat clone() throws CloneNotSupportedException {
            super.clone();
            var s = new Seat();
            s.row = row;
            s.seat = seat;
            return s;
        }

        @Override
        public String toString() {
            return "Row " + this.row + ". Seat " + this.seat;
        }
    }
}

