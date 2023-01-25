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
    public Iterable<K> getAll() {
        return map.keySet();
    }

    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append("\n\nBarrierTable:");
        for (Map.Entry<K,V> e : map.entrySet())
        {
            s.append('\n');
            s.append(e.getKey());
            s.append("-->");
            s.append(e.getValue());
        }
        return s.toString();
    }
}
