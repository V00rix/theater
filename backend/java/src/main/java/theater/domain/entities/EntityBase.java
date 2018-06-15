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
    private static final char delimiter = ':';

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public Long id;

    public EntityBase() {
    }

    String fieldsToString(String... valuesAsString) {
        var r = new StringBuilder();
        var length = valuesAsString.length;
        for (int i = 0; i < length; i++) {
            String v = valuesAsString[i];
            r.append(v);
            if (i < length - 1) {
                r.append(delimiter);
            }
        }
        return r.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public abstract void print();

    public abstract boolean equalz(T another);

    public abstract void update(T another);

    public String stringValue() {
        return this.toString();
    }
}
