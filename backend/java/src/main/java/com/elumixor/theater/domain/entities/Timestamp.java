package com.elumixor.theater.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;

@Entity
//@SequenceGenerator(name="scanIdGen", sequenceName="scan_seq", allocationSize=1)
@Table(name = "t_timestamp")
public class Timestamp implements Serializable  {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private java.sql.Timestamp date;

    public Timestamp() {
        this.date = new java.sql.Timestamp(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return format.format(this.date);
    }

    public void print() {
        System.out.println("----Timestamp");
        System.out.println(id);
        System.out.println(date);
    }
}
