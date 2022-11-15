package Model.Values;

import Model.Types.BoolType;
import Model.Types.Type;

public class BoolValue implements Value{

    boolean val;

    public BoolValue(boolean v){this.val = v;}

    public boolean getVal() {return val;}
    @Override
    public Type getType() { return new BoolType();}

    public boolean equals(BoolValue bv){
        return bv.val == this.val;
    }
    @Override
    public String toString() {
        return Boolean.toString(val);
    }
}
