package com.example.toylanguage.Model.ADT.Dictionary;

import java.util.Collection;

public interface MyIDictionary<T,V> {
    V lookup(T id);

    void initialize(T id, V val);

    boolean isDefined(T id);

    void update(T id, V val);

    MyIDictionary<T,V> deepCopy();

    Collection<V> getValues();
}
