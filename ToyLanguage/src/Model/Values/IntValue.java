package Model.Values;

import Model.Types.*;
import Model.Types.IntType;

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
