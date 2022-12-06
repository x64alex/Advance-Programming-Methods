package Model.Expressions;

import Exceptions.MyException;
import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.Heap.MyIHeap;
import Model.Values.Value;

public interface Exp {
    Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer,Value> hp) throws MyException;
}
