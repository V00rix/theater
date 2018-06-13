package theater.domain;

public interface EntityBase<T extends EntityBase> {
    public void print();
    public boolean equals(T another);
}
