package com.elumixor.theater.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_session")
public class Session implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "date", nullable = false)
    private Timestamp date;

//    @NotNull
//    public String date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Performance performance;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "performance", nullable = false)
//    public Performance performance;

    public String getDate() {
        return date.toString();
    }
}
