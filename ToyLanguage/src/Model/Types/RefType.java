package Model.Types;
import Model.Values.RefValue;
import Model.Values.Value;

public class RefType implements Type{
    Type inner;
    public RefType(Type inner) {this.inner=inner;}
    public Type getInner() {return inner;}

    public boolean equals(Object another){
        if (another instanceof RefType anot) {
            return inner.equals(anot.getInner());
        }
        else
            return false;
    }
    public String toString() { return "Ref(" +inner.toString()+")";}
    public Value defaultValue() { return new RefValue(0,inner);}
}
