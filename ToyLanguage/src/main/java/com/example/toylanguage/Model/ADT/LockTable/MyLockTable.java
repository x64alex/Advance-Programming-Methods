package com.example.toylanguage.Model.ADT.LockTable;

import java.util.HashMap;

public class MyLockTable<Integer,Int> implements MyILockTable<Integer,Integer>{

    static private int freeLocation = 1;

    private HashMap<java.lang.Integer, Integer> internalDictionary;

    public MyLockTable(){
        this.internalDictionary = new HashMap<java.lang.Integer, Integer>();
    }

    public int getFreeLocation(){return freeLocation;}

    synchronized public void getNextFree() {
        this.freeLocation +=1;
    }


    @Override
    synchronized public boolean isDefined(Integer id) {
        return internalDictionary.containsKey(id);
    }

    @Override
    public void update(Integer id, Integer val) {
        internalDictionary.put((java.lang.Integer) id,val);
    }

    @Override
    public Integer lookup(Integer id) {
        return internalDictionary.get(id);
    }

    @Override
    synchronized public void initialize(Integer val){
        internalDictionary.put(this.freeLocation,val);
        getNextFree();
    }



}
