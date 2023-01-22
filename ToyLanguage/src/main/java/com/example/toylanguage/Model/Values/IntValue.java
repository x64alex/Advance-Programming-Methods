package com.example.toylanguage.Model.Values;


import com.example.toylanguage.Model.Types.IntType;
import com.example.toylanguage.Model.Types.*;


public class IntValue implements Value{
    private int val;
    public IntValue(int v){val=v;}
    public int getVal() {return val;}
    @Override
    public Type getType() { return new IntType();}

    public boolean equals(IntValue bv){
        return bv.val == this.val;
    }

    @Override
    public String toString() {
        return Integer.toString(val);
    }
}
