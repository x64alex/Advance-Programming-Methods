package com.example.toylanguage.Model.Values;


import com.example.toylanguage.Model.Types.RefType;

import java.util.Objects;
import com.example.toylanguage.Model.Types.*;


public class RefValue implements Value{
    private int address;
    private Type locationType;

    public RefValue(int add, Type locationType){
        this.address=add;
        this.locationType = locationType;
    }

    @Override
    public Type getType() {
        return new RefType(locationType);}

    public boolean equals(RefValue r){
        return this.address == r.address && Objects.equals(locationType, r.locationType);
    }
    public int getAddr() {return address;}

    @Override
    public String toString() {
        return "("+address+","+locationType.toString()+")";
    }
}
