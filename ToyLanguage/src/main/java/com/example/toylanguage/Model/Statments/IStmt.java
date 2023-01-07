package com.example.toylanguage.Model.Statments;


import com.example.toylanguage.Exceptions.MyException;
import com.example.toylanguage.Model.ADT.SymTable.MyIDictionary;
import com.example.toylanguage.Model.PrgState;
import com.example.toylanguage.Model.Types.*;


public interface IStmt {
    PrgState execute(PrgState state) throws MyException;

    String toString();

    IStmt deepCopy();

    MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws
            MyException;
}
