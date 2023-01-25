package com.example.toylanguage.Model.PairBarrier;

public class PairBarrier<K,V>
{
    private K first;
    private V second;

    public PairBarrier(K f,V s)
    {
        first = f;
        second = s;
    }

    public K getFirst() {
        return first;
    }

    public void setFirst(K first) {
        this.first = first;
    }


    public V getSecond() {
        return second;
    }

    public void setSecond(V second) {
        this.second = second;
    }


    @Override
    public String toString()
    {
        return "First:"+ first.toString() + "  Second:" + second.toString();
    }
}
