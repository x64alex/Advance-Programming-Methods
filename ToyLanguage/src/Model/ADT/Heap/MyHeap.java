package Model.ADT.Heap;

import java.util.HashMap;

public class MyHeap<Int,V> implements MyIHeap<Int,V> {
    private final HashMap<Integer, V> internalDictionary;
    private int freeLocation;
    public MyHeap(){
        this.internalDictionary = new HashMap<Integer, V>();
        this.freeLocation = 1;
    }
    public int getFreeLocation(){return freeLocation;}

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
    public String toString() {
        String s = "";
        for(Integer el:internalDictionary.keySet()){
            s +=el+"->"+internalDictionary.get(el)+"\n";
        }
        return s;
    }
}