package Model.Expressions;

import Exceptions.MyException;
import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.Heap.MyIHeap;
import Model.Values.*;

public class VarExp implements Exp{
    String id;

    public VarExp(String id){
        this.id = id;
    }

    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer,Value> hp) throws MyException
    {return tbl.lookup(id);}

    @Override
    public String toString() {
        return "VarExp{" +
                "id='" + id + '\'' +
                '}';
    }
}
