package com.example.toylanguage.Model.ADT.List;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements MyIList<T> {

    private List<T> internalList;
    public MyList() {
        this.internalList = new ArrayList<>();
    }

    @Override
    public void add(T element) {
        internalList.add(element);
    }

    @Override
    public List<T> getList() {
        return internalList;
    }

    @Override
    public String toString() {
        String s="";
        for(T el:internalList){
            s += el+"\n";
        }

        return s;
    }
}
