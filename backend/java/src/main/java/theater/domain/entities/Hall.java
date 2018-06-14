package theater.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_hall")
//@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
public class Hall extends EntityBase<Hall> implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Integer hall) {
//        id = hall.longValue();
//    }


    @Column(nullable = false, unique = true)
    public String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "hall")
    protected List<Seat> seats = new ArrayList<>();

    public List<Seat> getSeats() {
        seats.size();
        System.out.println(seats);
        return seats;
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

    public Hall() {
    }

    public Hall(String name) {
        this.name = name;
    }

    public Hall(String name, int height, int width) {
        this(height, width);
        this.name = name;
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
    public void copy(Hall another) {
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

    @Entity
    @Table(name = "t_seat")
    @IdClass(Seat.class)
    public static class Seat implements Serializable, Cloneable {
        @Id
        public int x;
        @Id
        public int y;

        @Override
        public String toString() {
            return "Row " + this.y + ". Seat " + this.x;
        }

        @Override
        protected Seat clone() throws CloneNotSupportedException {
//            try {
                super.clone();
//            } catch (CloneNotSupportedException e) {
//                e.printStackTrace();
//            }
            var s = new Seat();
            s.x = x;
            s.y = y;
            return s;
        }
    }
}

