package Model.ADT.Heap;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MyHeap<Int,V> implements MyIHeap<Int,V> {
    private HashMap<Integer, V> internalDictionary;
    private int freeLocation;
    public MyHeap(){
        this.internalDictionary = new HashMap<Integer, V>();
        this.freeLocation = 1;
    }
    public int getFreeLocation(){return freeLocation;}

    @Override
    public void update(Int id, V val) {
        internalDictionary.put((Integer) id, val);
    }

    @Override
    public void initialize(V val){
        internalDictionary.put(this.freeLocation,val);
        this.freeLocation +=1;
    }
    @Override
    public V lookup(Int id) {
        return internalDictionary.get(id);
    }

    @Override
    public boolean isDefined(Int id) {
        return internalDictionary.containsKey(id);
    }

    @Override
    public Map<Int, V> getContent() {
        return (Map<Int, V>) internalDictionary;
    }

    @Override
    public Collection<V> getValues() {
        return internalDictionary.values();
    }

    @Override
    public void setContent(Map<Int, V> unsafeGarbageCollector) {
        this.internalDictionary = (HashMap<Integer, V>) unsafeGarbageCollector;
    }

    @Override
    public String toString() {
        String s = "";
        for(Integer el:internalDictionary.keySet()){
            s += el+"->"+internalDictionary.get(el)+"\n";
        }
        return s;
    }
}