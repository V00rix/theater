package theater.domain.entities;

import theater.domain.Seat;

import javax.persistence.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_hall")
public class Hall extends EntityBase<Hall> implements Serializable {
    //region Fields
    @Column(nullable = false, unique = true)
    public String name;

    @Transient
    private List<Seat> seats = new ArrayList<>();

    @Lob
    @Column(name = "seats")
    private byte[] seatsBytes = "".getBytes();

    @PostLoad
    public void postLoad() {
        this.seats.forEach(Seat::print);
    }

    public List<Seat> getSeats() {
        return seats = Seat.fromBytes(seatsBytes);
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
        seatsBytes = Seat.toBytes(seats);
    }

    //endregion

    //region Implementation
    @Override
    public void print() {
        System.out.println("Hall " + name + " (" + id + "). Seats count " + getSeats().size());
    }

    @Override
    public boolean equalz(Hall another) {
        return name.equals(another.name) && getSeats().size() == another.getSeats().size();
    }

    @Override
    public void update(Hall another) {
        name = another.name;
        seats = new ArrayList<>();
        try {
            for (Seat s : another.seats) {
                seats.add(s.clone());
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String stringValue() {
        return name;
    }
    //endregion

    //region Constructors
    public Hall() {
    }

    public Hall(String name) {
        this.name = name;
    }

    public Hall(String name, int height, int width) {
        this(height, width);
        this.name = name;
    }

    private Hall(int height, int width) {
        var seats = new ArrayList<Seat>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                var s = new Seat();
                s.x = j;
                s.y = i;
                seats.add(s);
            }
        }

        setSeats(seats);
    }
    //endregion
}

