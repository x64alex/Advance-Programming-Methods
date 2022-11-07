package Model.Types;

import Model.Values.BoolValue;
import Model.Values.Value;

public class BoolType implements Type{
    public boolean equals(Object another){
        if (another instanceof BoolType) return true;
        else return false;
    }
    public String toString() { return "Bool Type";}

    @Override
    public Value defaultValue() {
        return new BoolValue(false);
    }
}
