package com.example.toylanguage.Model.ADT.Heap;

import java.util.Collection;
import java.util.Map;

public interface MyIHeap<T, V> {
    V lookup(T id);

    void initialize(V val);

    int getFreeLocation();
    void update(T id, V val);

    boolean isDefined(T id);

    Map<T, V> getContent();

    Collection<V> getValues();

    void setContent(Map<T, V> unsafeGarbageCollector);
}
