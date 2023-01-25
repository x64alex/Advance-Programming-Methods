package com.example.toylanguage.Model.ADT.Barrier;
import java.util.HashMap;
import java.util.Map;

public class CyclicBarrier<K,V> implements ICyclicBarrier<K,V>
{
    private Map<K,V> map;

    public CyclicBarrier()
    {
        map = new HashMap<>();
    }

    @Override
    public void add(K key,V value) {
        map.put(key,value);
    }

    @Override
    synchronized public V get(K key) {
        return map.get(key);
    }

    @Override
    synchronized public void update(K key, V value) {
        map.put(key,value);
    }

    @Override
    synchronized public boolean contains(K key) {
        return map.containsKey(key);
    }


    @Override
    public Map<K,V> getBarrier() {
        return map;
    }

    @Override
    public String toString()
    {
        String s = "";
        for (Map.Entry<K,V> e : map.entrySet())
        {
            s += "\n"+e.getKey()+"-->"+e.getValue();
        }
        return s;
    }
}
