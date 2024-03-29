package com.example.toylanguage.Model.ADT.FileTable;

import java.util.HashMap;
import java.util.List;

public class MyFileTable<T,V> implements MyIFileTable<T,V>{
    private final HashMap<T,V> internalDictionary;
    public MyFileTable(){
        this.internalDictionary = new HashMap<>();
    }

    @Override
    public void initialize(T id, V val){ internalDictionary.put(id,val);}
    @Override
    public V lookup(T id) {
        return internalDictionary.get(id);
    }

    @Override
    public boolean isDefined(T id) {
        return internalDictionary.containsKey(id);
    }

    @Override
    public void update(T id, V val) {
        internalDictionary.put(id,val);
    }

    @Override
    public void delete(T id) {
        internalDictionary.remove(id);
    }

    @Override
    public List<T> getFileNames() {
        return internalDictionary.keySet().stream().toList();
    }

    @Override
    public String toString() {
        String s = "";
        for(T el:internalDictionary.keySet()){
            s += el+"\n";
        }
        return s;
    }
}
