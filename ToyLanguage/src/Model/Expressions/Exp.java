package Model.Expressions;

import Exceptions.MyException;
import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.Heap.MyIHeap;
import Model.Values.Value;
import Model.Types.*;

public interface Exp {
    Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer,Value> hp) throws MyException;

    Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyException;
}
