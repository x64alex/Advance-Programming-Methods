package com.example.toylanguage.Model.Expressions;


import com.example.toylanguage.Exceptions.MyException;
import com.example.toylanguage.Model.ADT.SymTable.MyIDictionary;
import com.example.toylanguage.Model.ADT.Heap.MyIHeap;
import com.example.toylanguage.Model.Values.Value;
import com.example.toylanguage.Model.Types.*;

public interface Exp {
    Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer,Value> hp) throws MyException;

    Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyException;
}
