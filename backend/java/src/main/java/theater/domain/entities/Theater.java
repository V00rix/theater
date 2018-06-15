package theater.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "t_theater")
public class Theater extends EntityBase<Theater> implements Serializable {
    //region Fields
    @Column(nullable = false)
    public String name;

    @Column(nullable = false)
    public String address;

    @Column(name = "ticket_price")
    public BigDecimal ticketPrice;

    @Column(name = "open_time")
    public String openTime;

    @Column(name = "maximum_seats")
    @Min(1)
    public int maximumSeats;
    //endregion

    //region Constructors
    public Theater() {

    }

    public Theater(String address, String name, BigDecimal ticketPrice, String openTime, int maximumSeats) {
        this(address, name);
        this.ticketPrice = ticketPrice;
        this.openTime = openTime;
        this.maximumSeats = maximumSeats;
    }

    public Theater(String address, String name) {
        this.address = address;
        this.name = name;
    }
    //endregion

    @Override
    public void print() {
        System.out.println("Theater " + name + ". Address: " + address + " (" + id + ")");
    }

    @Override
    public boolean equalz(Theater another) {
        return name.equals(another.name) && address.equals(another.address);
    }

    @Override
    public void update(Theater another) {
        name = another.name;
        address = another.address;
        ticketPrice = another.ticketPrice;
        openTime = another.openTime;
        maximumSeats = another.maximumSeats;
    }

    @Override
    public String stringValue() {
        return fieldsToString(name, address);
    }
}
