package Model.ADT.Heap;

public interface MyIHeap<T, V> {
    V lookup(T id);

    void initialize(V val);

    boolean isDefined(T id);
}
