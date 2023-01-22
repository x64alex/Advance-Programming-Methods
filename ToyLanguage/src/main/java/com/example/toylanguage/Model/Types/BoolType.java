package com.example.toylanguage.Model.Types;


import com.example.toylanguage.Model.Values.BoolValue;
import com.example.toylanguage.Model.Values.Value;

public class BoolType implements Type{
    public boolean equals(Object another){
        if (another instanceof BoolType) return true;
        else return false;
    }
    public String toString() { return "Bool";}

    @Override
    public Value defaultValue() {
        return new BoolValue(false);
    }
}
