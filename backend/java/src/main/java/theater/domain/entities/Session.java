package theater.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "t_session")
public class Session extends EntityBase<Session> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hall", nullable = false)
    public Hall hall;

//    @Transactional
    public Long getHall() {
        return hall.getId();
    }

//    @Transactional
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance", nullable = false)
//    @JsonProperty("pRef")
//    @JsonIgnore
    public Performance performance;

    public Long getPerformance() {
        return performance.getId();
    }
    //    @JsonProperty

    public Timestamp date;

    public Session() {
    }

    public Session(Hall hall, Performance performance, Timestamp date) {
        this.hall = hall;
        this.performance = performance;
        this.date = date;
    }

    @Override
    public void print() {
        System.out.println("Session " + id + " on (" + date.toString() + ").");
        performance.print();
        hall.print();
    }

    @Override
    public boolean equals(Session another) {
        return hall.equals(another.hall) && performance.equals(another.performance) && date.equals(another.date);
    }

    @Override
    public void copy(Session another) {
        hall.copy(another.hall);
        performance.copy(another.performance);
        date = another.date;
    }

    //    @OneToOne(fetch = FetchType.LAZY)
    //    @JoinColumn(name = "date", nullable = false)
    //    private Timestamp date;
    //
    //    @OneToOne(fetch = FetchType.EAGER)
    //    @JoinColumn(name = "hall", nullable = false)
    //    @JsonIgnore
    //    public Hall hall;
    //
    //    @ManyToOne(fetch = FetchType.EAGER)
    //    @JoinColumn(name = "performance", nullable = false)
    //    @OnDelete(action = OnDeleteAction.CASCADE)
    //    @JsonIgnore
    //    public Performance performance;
    //
    //    @OneToMany(cascade = CascadeType.ALL,
    //            fetch = FetchType.LAZY,
    //            mappedBy = "session")
    //    @JsonIgnore
    //    public Set<Seat> seatsRef = new HashSet<>();
    //
    //    public String getDate() {
    //        return date.toString();
    //    }
    //
    //    public String getHall() {
    //        return hall.toString();
    //    }
    //
    //    public List<List<Availability>> getSeats() {
    //        var rows = new ArrayList<List<Availability>>();
    //
    ////        this.hall.rows.forEach(r -> {
    ////            var row = new ArrayList<Availability>();
    ////
    ////            for (int i = 0; i < r.seat_number; i++) {
    ////                row.add(Availability.FREE);
    ////            }
    ////
    ////            rows.add(row);
    ////        });
    //
    //        this.seatsRef.forEach(seat -> {
    ////            rows.get(seat.rowRef.number - 1).set(seat.number - 1, seat.availability);
    //        });
    //
    //        return rows;
    //    }
    //
    //    public Session() {
    //    }
    //
    //    @Transient
    //    public String getPerformance_id() {
    //        return this.performance.id.toString();
    //    }
    //
    //    @Transient
    //    public String getPerformance_title() {
    //        return this.performance.title.toString();
    //    }
    //
    ////    @Transient
    ////    public List<Order> getOrders() {
    ////        var k = new ArrayList<Order>();
    ////        this.seatsRef.stream().collect(Collectors.groupingBy(seat -> seat.order, Collectors.toList()))
    ////                .forEach(((order, seats) -> k.add(order)));
    ////
    ////        var t = new ArrayList<Order>();
    ////        k.stream().collect(Collectors.groupingBy(o -> o.client, Collectors.toList()))
    ////                .forEach(((client, orders) -> {
    ////                    var ord = orders.stream().reduce(
    ////                            orders.stream().findFirst().orElse(new Order(client, Checkout.SELF_CHECKOUT)),
    ////                            (o1, o2) -> {
    ////                                o1.seats.addAll(o2.seats);
    ////                                o1.checkout = o2.checkout;
    ////                                return o1;
    ////                            });
    ////                    t.add(ord);
    ////                }));
    ////
    ////        return t;
    ////    }
    //
    //    public void print() {
    //        System.out.println(date);
    //    }
}
