package Model.ADT.Dictionary;

import java.util.HashMap;

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
    public String toString() {
        return "MyDictionary{" +
                "internalDictionary=" + internalDictionary +
                '}';
    }
}
