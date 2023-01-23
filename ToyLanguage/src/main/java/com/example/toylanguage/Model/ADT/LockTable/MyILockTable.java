package com.example.toylanguage.Model.ADT.LockTable;

import java.util.Collection;
import java.util.Map;

public interface MyILockTable <T, V> {
    void initialize(V val);

    boolean isDefined(T id);

    void update(T id, V val);

    V lookup(T id);

    int getFreeLocation();

}
