package com.elumixor.theater.domain.entities;

import com.elumixor.theater.domain.enumeration.Checkout;

import javax.persistence.*;

@Entity
@Table(name = "t_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "date", nullable = false)
    private Timestamp date;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "email", nullable = false)
    public Client client;

    public boolean is_purchase;

    public boolean is_digital;

    public boolean confirmed;

    public Order() {
        this.date = new Timestamp();
        this.is_digital = true;
        this.is_purchase = false;
    }

    public Order(Client client, Checkout checkout) {
        this();
        this.client = client;
        this.checkout = checkout;
    }

    @Enumerated(EnumType.STRING)
    public Checkout checkout;

    public String getDate() {
        return date.toString();
    }

    public void print() {
        System.out.println("--Order--");
        System.out.println(id);
        date.print();
        client.print();
    }
}
