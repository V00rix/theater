package theater.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "t_client")
public class Client extends EntityBase<Client> implements Serializable {
    //region Fields
    @Column(unique = true, nullable = false)
    public String contact;

    public String name;
    //endregion

    //region Constructors
    public Client() {
    }

    public Client(String contact, String name) {
        this.contact = contact;
        this.name = name;
    }
    //endregion

    @Override
    public void print() {
        System.out.println("Client " + name + " (" + contact + ")");
    }

    @Override
    public boolean equalz(Client another) {
        return contact.equals(another.contact) && name.equals(another.name);
    }

    @Override
    public void copy(Client another) {
        contact = another.contact;
        name = another.name;
    }
}
