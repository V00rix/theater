package theater.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_hall")
public class Hall extends EntityBase<Hall> implements Serializable {
    //region Fields
    @Column(nullable = false, unique = true)
    public String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "hall")
    protected List<Seat> seats = new ArrayList<>();
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
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                var s = new Seat();
                s.x = j;
                s.y = i;
                seats.add(s);
            }
        }
    }
    //endregion

    public List<Seat> getSeats() {
        seats.size(); // lazy json serialization will fail without this.
        return seats;
    }

    @Override
    public void print() {
        System.out.println("Hall " + name + " (" + id + "). Seats count " + seats.size());
    }

    @Override
    public boolean equalz(Hall another) {
        return name.equals(another.name);
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

    @Entity
    @Table(name = "t_seat")
    @IdClass(Seat.class)
    public static class Seat implements Serializable, Cloneable {
        @Id
        public int x;

        @Id
        public int y;

        @Override
        protected Seat clone() throws CloneNotSupportedException {
            super.clone();
            var s = new Seat();
            s.x = x;
            s.y = y;
            return s;
        }

        @Override
        public String toString() {
            return "Row " + this.y + ". Seat " + this.x;
        }
    }
}

