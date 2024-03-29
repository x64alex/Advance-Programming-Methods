package com.example.toylanguage.Model.Statments;


import com.example.toylanguage.Exceptions.MyException;
import com.example.toylanguage.Model.ADT.SymTable.MyIDictionary;
import com.example.toylanguage.Model.PrgState;
import com.example.toylanguage.Model.Types.*;


public class NopStmt implements IStmt{
    public NopStmt(){}

    @Override
    public PrgState execute(PrgState state) throws MyException {
        return null;
    }


    public String toString() {
        return "(Nop)";
    }

    @Override
    public IStmt deepCopy() {
        return new NopStmt();
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }
}
