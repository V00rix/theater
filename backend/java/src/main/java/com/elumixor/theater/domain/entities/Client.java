package com.elumixor.theater.domain.entities;

import com.elumixor.theater.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_website_client")
public class Client implements Serializable {
    @Id
    @NotNull
    @Column(name = "email")
    public String email;

    public String name;

    public Client() {
    }

    public Client(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Client(User user) {
        this.name = user.name;
        this.email = user.contact;
    }

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "client")
    @JsonIgnore
    public Set<Order> orders = new HashSet<>();

    public void print() {
        System.out.println("--Client");
        System.out.println(name);
        System.out.println(email);
    }
}
