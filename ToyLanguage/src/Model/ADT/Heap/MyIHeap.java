package Model.ADT.Heap;

public interface MyIHeap<T, V> {
    V lookup(T id);

    void initialize(V val);

    int getFreeLocation();
    boolean isDefined(T id);
}
