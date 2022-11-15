package Model.ADT.FileTable;

public interface MyIFileTable<T,V> {
    V lookup(T id);

    void initialize(T id, V val);

    boolean isDefined(T id);

    void update(T id, V val);

    void delete(T id);
}
