package Model.ADT.Heap;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MyHeap<Int,V> implements MyIHeap<Int,V> {
    private HashMap<Integer, V> internalDictionary;
    static private int freeLocation = 1;
    public MyHeap(){
        this.internalDictionary = new HashMap<Integer, V>();
    }
    public int getFreeLocation(){return freeLocation;}

    synchronized public int getNextFree() {
        this.freeLocation +=1;
        return this.freeLocation;
    }
    @Override
    public void update(Int id, V val) {
        internalDictionary.put((Integer) id, val);
    }

    @Override
    public void initialize(V val){
        //TODO:Check if the position is 0 first
        internalDictionary.put(this.freeLocation,val);
        //TODO: Syncronize getNextFree within the whole app
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