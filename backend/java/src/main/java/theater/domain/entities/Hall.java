package theater.domain.entities;

import theater.domain.EntityBase;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_hall")
public class Hall implements Serializable, EntityBase<Hall> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

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
        System.out.println("Hall " + name + " (" + name + "). Seats count " + seats.size());
    }

    @Override
    public boolean equals(Hall another) {
        return name.equals(another.name);
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
    }
}

