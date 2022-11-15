package Model.Types;

import Model.Values.IntValue;
import Model.Values.Value;

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
