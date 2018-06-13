package theater.domain.entities;


import theater.domain.EntityBase;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_performance")
//@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
//        allowGetters = true)
public class Performance implements Serializable, EntityBase<Performance> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(nullable = false)
    public String author;

    @Column(nullable = false)
    public String title;

    @Column(name = "image_url")
    public String image;

    public String description;

    public Performance() {
    }

    public Performance(String author, String title) {
        this.author = author;
        this.title = title;
    }

    @Override
    public void print() {
        System.out.println("Performance " + title + " by " + author + " (" + id + ")");
    }

    @Override
    public boolean equals(Performance another) {
        return title.equals(another.title) && author.equals(another.author);
    }

    //
    //    @OneToMany(cascade = CascadeType.ALL,
    //            fetch = FetchType.LAZY,
    //            mappedBy = "performance")
    //    public Set<Session> sessions = new HashSet<>();
}
