package theater.domain.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_client")
public class Client extends EntityBase<Client> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(unique = true, nullable = false)
    public String contact;

    public String name;

    public Client() {
    }

    public Client(String contact, String name) {
        this.contact = contact;
        this.name = name;
    }

    @Override
    public void print() {
        System.out.println("Client " + name + " (" + contact + ")");
    }

    @Override
    public boolean equals(Client another) {
        return contact.equals(another.contact) && name.equals(another.name);
    }
    //    }
    //
    //    @OneToMany(cascade = CascadeType.ALL,
    //            fetch = FetchType.LAZY,
    //            mappedBy = "client")
    //    @JsonIgnore
    //    public Set<Order> orders = new HashSet<>();
    //
    //    public void print() {
    //        System.out.println("--Client");
    //        System.out.println(name);
    //        System.out.println(email);
    //    }
}
