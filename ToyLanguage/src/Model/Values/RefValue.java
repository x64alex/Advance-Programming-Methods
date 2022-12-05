package Model.Values;

import Model.Types.RefType;
import Model.Types.Type;

import java.util.Objects;

public class RefValue implements Value{
    private int address;
    private Type locationType;

    public RefValue(int add, Type locationType){this.address=add; this.locationType = locationType;}

    @Override
    public Type getType() {
        return new RefType(locationType);}

    public boolean equals(RefValue r){
        return this.address == r.address && Objects.equals(locationType, r.locationType);
    }

    @Override
    public String toString() {
        return "Address:"+address+" Type:"+locationType.toString();
    }
}
