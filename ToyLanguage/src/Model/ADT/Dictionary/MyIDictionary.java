package Model.ADT.Dictionary;

import Model.Values.Value;

import java.util.Collection;
import java.util.List;

public interface MyIDictionary<T,V> {
    V lookup(T id);

    void initialize(T id, V val);

    boolean isDefined(T id);

    void update(T id, V val);

    Collection<V> getValues();
}
