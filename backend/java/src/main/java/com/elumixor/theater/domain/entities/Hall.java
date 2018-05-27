package com.elumixor.theater.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_hall")
public class Hall implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "hall")
//    @JsonIgnore
    @OrderBy("number ASC")
    public Set<Row> rows = new HashSet<>();

    @Override
    public String toString() {
        return this.name;
    }
}
