package theater.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "t_client")
public class Client implements Serializable {
    @Id
    @NotNull
    @Column(name = "email")
    public String email;

//    public String name;
//
//    public Client() {
//    }
//
//    public Client(String name, String email) {
//        this.name = name;
//        this.email = email;
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
