package Model.Types;

import Model.Values.BoolValue;
import Model.Values.Value;

public class BoolType implements Type{
    public boolean equals(Object another){
        if (another instanceof IntType) return true;
        else return false;
    }
    public String toString() { return "bool";}

    @Override
    public Value defaultValue() {
        return new BoolValue(false);
    }
}
