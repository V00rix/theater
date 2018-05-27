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


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "row", nullable = false)
    @JsonIgnore
    public Row rowRef;

//    private int row;

    @Enumerated(EnumType.STRING)
    public Availability availability;

    // TODO: 27-May-18 ORDER

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Session session;

    public int getRow() {
        return rowRef.number;
    }
}
