package com.elumixor.theater.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "t_session")
public class Session implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    public String date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Performance performance;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "performance", nullable = false)
//    public Performance performance;
}
