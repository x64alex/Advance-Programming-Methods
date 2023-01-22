package com.example.toylanguage.Model.ADT.List;

import java.util.List;

public interface MyIList<T> {
    void add(T element);

    List<T> getList();
}
