package theater.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "t_theater")
public class Theater extends EntityBase<Theater> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    public Long getId() {
        return id;
    }

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
    public boolean equalz(Theater another) {
        return name.equals(another.name) && address.equals(another.address);
    }

    @Override
    public void copy(Theater another) {
        name = another.name;
        address = another.address;
        ticketPrice = another.ticketPrice;
        openTime = another.openTime;
        maximumSeats = another.maximumSeats;
    }
}
