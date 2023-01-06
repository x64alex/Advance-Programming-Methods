package Model.Expressions;

import Exceptions.MyException;
import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.Heap.MyIHeap;
import Model.Types.Type;
import Model.Values.*;

public class ValueExp implements Exp{
    private Value e;

    public ValueExp(Value e){
        this.e = e;
    }
    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer,Value> hp) throws MyException {return e;}

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return e.getType();
    }

    @Override
    public String toString() {
        return "ValueExp{" +
                "e=" + e.toString() +
                '}';
    }
}
