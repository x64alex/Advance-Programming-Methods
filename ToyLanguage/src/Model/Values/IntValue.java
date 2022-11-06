package Model.Values;

import Model.Types.*;
import Model.Types.IntType;

public class IntValue implements Value{
    private int val;
    public IntValue(int v){val=v;}
    public int getVal() {return val;}
    public String toString() {return "IntValue";}

    @Override
    public Type getType() { return new IntType();}
}
