package theater.domain.entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_performance")
//@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
//        allowGetters = true)
public class Performance extends EntityBase<Performance> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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

    @Override
    public void copy(Performance another) {
        author = another.author;
        title = another.title;
        image = another.image;
        description = another.description;
    }

    public Long getId() {
        return id;
    }

    //
    //    @OneToMany(cascade = CascadeType.ALL,
    //            fetch = FetchType.LAZY,
    //            mappedBy = "performance")
    //    public Set<Session> sessions = new HashSet<>();
}
