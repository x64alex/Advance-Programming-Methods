package Model.ADT.Heap;

public interface MyIHeap<T, V> {
    V lookup(T id);

    void initialize(V val);

    int getFreeLocation();
    void update(T id, V val);

    boolean isDefined(T id);
}
