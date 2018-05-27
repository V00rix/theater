package com.elumixor.theater.domain.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_row")
public class Row implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public int number;

    public int seat_number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hall", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Hall hall;
}
