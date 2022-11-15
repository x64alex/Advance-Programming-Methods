package Model.Types;

import Model.Values.StringValue;
import Model.Values.Value;

public class StringType implements Type{

    public StringType(){}

    public boolean equals(Object another){
        if (another instanceof StringType) return true;
        else return false;
    }

    public String toString() { return "String";}
    @Override
    public Value defaultValue() {
        return new StringValue("");
    }
}
