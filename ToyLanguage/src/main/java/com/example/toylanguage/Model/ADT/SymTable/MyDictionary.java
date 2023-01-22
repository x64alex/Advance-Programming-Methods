package com.example.toylanguage.Model.ADT.SymTable;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyDictionary<T,V> implements MyIDictionary<T,V>{

    private final HashMap<T,V> internalDictionary;
    public MyDictionary(){
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
    public MyIDictionary<T, V> deepCopy() {
        MyIDictionary<T,V> newDict =  new MyDictionary<>();
        for(T key: this.getKeys()){
            V value = this.lookup(key);
            newDict.initialize(key, value);
        }
        return newDict;
    }

    @Override
    public Collection<V> getValues() {
        return internalDictionary.values();
    }

    @Override
    public Map<T, V> getContent(){
        return internalDictionary;
    }

    public Collection<T> getKeys() {
        return internalDictionary.keySet();
    }

    @Override
    public String toString() {
        String s = "";
        for(T el:internalDictionary.keySet()){
            s +=el+"="+this.lookup(el)+"\n";
        }
        return s;
    }
}
