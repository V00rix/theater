package com.elumixor.theater.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;

@Entity
@Table(name = "t_timestamp")
public class Timestamp implements Serializable  {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private java.sql.Timestamp date;

//    @OneToOne(fetch = FetchType.LAZY,
//            cascade =  CascadeType.ALL,
//            mappedBy = "date")
//    public Timestamp date;
    @Override
    public String toString() {
        return format.format(this.date);
    }
}
