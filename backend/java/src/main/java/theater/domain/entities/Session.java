package theater.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "t_session")
public class Session extends EntityBase<Session> implements Serializable {



    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hall", nullable = false)
    public Hall hall;

    public Long getHall() {
        return hall.getId();
    }

    public void setHall(Integer hall) {
        this.hall = new Hall();
        this.hall.setId(hall.longValue());
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance", nullable = false)
    public Performance performance;

    public void setPerformance(Integer performance) {
        this.performance = new Performance();
        this.performance.setId(performance.longValue());
    }

    public Long getPerformance() {
        return performance.getId();
    }

    public Timestamp date;

    public void setDate(Timestamp date) {
        this.date = date;
    }

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
    public boolean equalz(Session another) {
        return hall.equalz(another.hall) && performance.equalz(another.performance) && date.equals(another.date);
    }

    @Override
    public void copy(Session another) {
        hall.copy(another.hall);
        performance.copy(another.performance);
        date = another.date;
    }
}
