package theater.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_hall")
public class Hall extends EntityBase<Hall> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    public String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "hall")
    public List<Seat> seats = new ArrayList<>();

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
    public boolean equals(Hall another) {
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

    public Long getId() {
        return id;
    }

    @Entity
    @Table(name = "t_seat")
    @IdClass(Seat.class)
    public static class Seat implements Serializable {
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
            super.clone();
            var s = new Seat();
            s.x = x;
            s.y = y;
            return s;
        }
    }
}

