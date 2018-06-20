package theater.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "t_performance")
public class Performance extends EntityBase<Performance> implements Serializable {

    //region Fields
    @Column(nullable = false)
    public String author;

    @Column(nullable = false)
    public String title;

    @Column(name = "image_url")
    public String image;

    public String description;
    //endregion

    //region Constructors
    public Performance() {
    }

    public Performance(String author, String title) {
        this.author = author;
        this.title = title;
    }

    public Performance(String author, String title, String image) {
        this(author, title);
        this.image = image;
    }

    public Performance(String author, String title, String image, String description) {
        this(author, title);
        this.image = image;
        this.description = description;
    }

    //endregion

    //region Implementation
    @Override
    public void print() {
        System.out.println("Performance " + title + " by " + author + " (" + id + ")");
    }

    @Override
    public boolean equalz(Performance another) {
        return title.equals(another.title) && author.equals(another.author);
    }

    @Override
    public void update(Performance another) {
        author = another.author;
        title = another.title;
        image = another.image;
        description = another.description;
    }

    @Override
    public String stringValue() {
        return fieldsToString(title, author);
    }
    //endregion
}
