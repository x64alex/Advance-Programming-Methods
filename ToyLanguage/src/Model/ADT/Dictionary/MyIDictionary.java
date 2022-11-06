package Model.ADT.Dictionary;

public interface MyIDictionary<T,V> {
    V lookup(T id);

    boolean isDefined(T id);

    void update(T id, V val);
}
