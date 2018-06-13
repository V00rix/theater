package theater.domain.entities;

import theater.domain.EntityBase;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_theater")
public class Theater implements Serializable, EntityBase<Theater> {

    //region Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    public String country;

    @Column(nullable = false)
    public String city;

    @Column(nullable = false)
    public String street;

    @Column(nullable = false)
    public String house;

    @Column(name = "post_code")
    public String postCode;
    @Column(name = "city_part")
    public String cityPart;

    @Column(nullable = false)
    public String name;

    @Column(name = "maximum_seats")
    public int maximumSeats;
    //endregion

    //region Constructors
    public Theater() {};

    public Theater(String country, String city, String street, String house, String name) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.house = house;
        this.name = name;
    }

    public Theater(String country, String city, String street, String house, String name, int maximumSeats) {
        this(country, city, street, house, name);
        this.maximumSeats = maximumSeats;
    }

    @Override
    public void print() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Theater another) {
        throw new UnsupportedOperationException();
    }
    //endregion
}
