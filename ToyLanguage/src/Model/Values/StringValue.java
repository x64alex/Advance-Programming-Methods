package Model.Values;

import Model.Types.StringType;
import Model.Types.Type;

import java.util.Objects;

public class StringValue implements Value{
    private String val;

    public StringValue(String v){val=v;}

    @Override
    public Type getType() {
        return new StringType();
    }

    public boolean equals(StringValue s){
        return Objects.equals(s.val, this.val);
    }

    @Override
    public String toString() {
        return val;
    }
}
