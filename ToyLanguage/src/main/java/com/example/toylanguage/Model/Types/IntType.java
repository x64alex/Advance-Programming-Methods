package com.example.toylanguage.Model.Types;


import com.example.toylanguage.Model.Values.IntValue;
import com.example.toylanguage.Model.Values.Value;

public class IntType implements Type{

    public IntType(){}
    public boolean equals(Object another){
        if (another instanceof IntType) return true;
            else return false;
    }
    public String toString() { return "Int";}

    @Override
    public Value defaultValue() {
        return new IntValue(0);
    }
}
