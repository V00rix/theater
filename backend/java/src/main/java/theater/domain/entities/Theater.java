package theater.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import theater.domain.EntityBase;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "t_theater")
public class Theater implements Serializable, EntityBase<Theater> {

    //region Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(nullable = false)
    public String name;

    @Column(nullable = false)
    public String address;

    @Column(name = "ticket_price")
    public BigDecimal ticketPrice;

    @Column(name = "open_time")
    public String openTime;

    @Column(name = "maximum_seats")
    public int maximumSeats;
    //endregion

    //region Constructors
    public Theater() {

    }

    public Theater(String address, String name) {
        this.address = address;
        this.name = name;
    }

    public Theater(String address, String name, BigDecimal ticketPrice, String openTime, int maximumSeats) {
        this(address, name);
        this.ticketPrice = ticketPrice;
        this.openTime = openTime;
        this.maximumSeats = maximumSeats;
    }

    @Override
    public void print() {
        System.out.println("Theater " + name + ". Address: " + address + " (" + id + ")");
    }

    @Override
    public boolean equals(Theater another) {
        return name.equals(another.name) && address.equals(another.address);
    }
    //endregion
}
