package Model.ADT.FileTable;

import java.util.HashMap;

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
    public String toString() {
        String s = "";
        for(V el:internalDictionary.values()){
            s +=el+"\n";
        }
        return s;
    }
}
