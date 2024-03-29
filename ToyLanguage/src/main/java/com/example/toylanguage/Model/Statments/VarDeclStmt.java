package com.example.toylanguage.Model.Statments;

import com.example.toylanguage.Exceptions.MyException;
import com.example.toylanguage.Model.ADT.SymTable.MyIDictionary;
import com.example.toylanguage.Model.PrgState;
import com.example.toylanguage.Model.Types.*;
import com.example.toylanguage.Model.Values.Value;


public class VarDeclStmt implements IStmt{
    String name;
    Type typ;

    public VarDeclStmt(String name, Type typ){
        this.name = name;
        this.typ = typ;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        if(! symTable.isDefined(this.name)){
            symTable.initialize(this.name,this.typ.defaultValue());
        }else throw new MyException("Variable already exists");

        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new VarDeclStmt(this.name, this.typ);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        typeEnv.initialize(name, typ);
        return typeEnv;
    }

    @Override
    public String toString() {
        return name +"="+ typ;
    }
}
