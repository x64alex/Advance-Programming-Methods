package Model.Values;

import Model.Types.BoolType;
import Model.Types.Type;

public class BoolValue implements Value{

    boolean val;

    public BoolValue(boolean v){this.val = v;}

    public boolean getVal() {return val;}
    @Override
    public Type getType() { return new BoolType();}

    @Override
    public String toString() {
        return "BoolValue{" +
                "val=" + val +
                '}';
    }
}
