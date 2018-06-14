package theater.domain.entities;

public abstract class EntityBase<T extends EntityBase> {
    public abstract void print();
    public abstract boolean equals(T another);
    public abstract void copy(T another);
}
