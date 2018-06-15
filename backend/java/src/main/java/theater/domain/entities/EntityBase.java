package theater.domain.entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Client.class, name = "client"),
        @JsonSubTypes.Type(value = Hall.class, name = "hall"),
        @JsonSubTypes.Type(value = Order.class, name = "order"),
        @JsonSubTypes.Type(value = Performance.class, name = "performance"),
        @JsonSubTypes.Type(value = Session.class, name = "session"),
        @JsonSubTypes.Type(value = Theater.class, name = "theater"),
})
@MappedSuperclass
public abstract class EntityBase<T extends EntityBase> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public Long id;

    public EntityBase() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public abstract void print();

    public abstract boolean equalz(T another);

    public abstract void copy(T another);
}
