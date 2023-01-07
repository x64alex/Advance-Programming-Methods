package com.example.toylanguage.Model.ADT.SymTable;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface MyIDictionary<T,V> {
    V lookup(T id);

    void initialize(T id, V val);

    boolean isDefined(T id);

    void update(T id, V val);

    MyIDictionary<T,V> deepCopy();

    Collection<V> getValues();

    Map<T, V> getContent();

}
