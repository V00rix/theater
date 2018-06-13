package theater.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_order")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(value = "code")
    public Long id;
//
//    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
//    @JoinColumn(name = "date", nullable = false)
//    private Timestamp date;
//
//    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
//    @JoinColumn(name = "email", nullable = false)
//    public Client client;
//
//    public boolean is_purchase;
//
//    public boolean is_digital;
//
//    public Boolean confirmed;
//
//    @OneToMany(cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY,
//            mappedBy = "order")
//    public Set<Seat> seats = new HashSet<>();
//
//    public Order() {
//        this.date = new Timestamp();
//        this.is_digital = true;
//        this.is_purchase = false;
//    }
//
//    public Order(Client client, Checkout checkout) {
//        this();
//        this.client = client;
//        this.checkout = checkout;
//    }
//
//    @Enumerated(EnumType.STRING)
//    public Checkout checkout;
//
//    public String getDate() {
//        return date.toString();
//    }
//
//    public void print() {
//        System.out.println("--Order--");
//        System.out.println(id);
//        date.print();
////        seats.forEach(Seat::print);
////        client.print();
//    }
//
////    @Transient
////    public Session getSession() {
////        var seat = this.seats.stream().findFirst();
////        return seat.map(s -> {
////            var session = s.session;
////            return new Session(session.performance.title, session.getDate());
////        }).orElse(null);
////    }
//
//    public class Session {
//        @Transient
//        public String performance;
//        @Transient
//        public String date;
//
//        Session(String performance, String date) {
//            this.performance = performance;
//            this.date = date;
//        }
//
//        @Override
//        public boolean equals(Object obj) {
//            if (obj == this) return true;
//            if (!(obj instanceof Session)) return false;
//            var s = (Session)obj;
//            return s.performance.equals(this.performance) && s.date.equals(this.date);
//        }
//
//        @Override
//        public int hashCode() {
//            int result = 17;
//            result = 31 * result + performance.hashCode();
//            result = 31 * result + date.hashCode();
//            return result;
//        }
//    }
}
