package theater.domain.entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_performance")
//@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
//        allowGetters = true)
public class Performance implements Serializable {
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

    //
    //    @OneToMany(cascade = CascadeType.ALL,
    //            fetch = FetchType.LAZY,
    //            mappedBy = "performance")
    //    public Set<Session> sessions = new HashSet<>();
}
