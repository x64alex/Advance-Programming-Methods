package com.example.toylanguage.Model.Statments;


import com.example.toylanguage.Exceptions.MyException;
import com.example.toylanguage.Model.ADT.SymTable.MyIDictionary;
import com.example.toylanguage.Model.ADT.List.MyIList;
import com.example.toylanguage.Model.Expressions.Exp;
import com.example.toylanguage.Model.PrgState;
import com.example.toylanguage.Model.Values.Value;
import com.example.toylanguage.Model.Types.*;


public class PrintStmt implements IStmt {
    Exp exp;

    public PrintStmt(Exp exp){
        this.exp = exp;
    }
    public String toString(){ return "print(" +exp.toString()+")";}

    public PrgState execute(PrgState state) throws MyException {
        MyIList<Value> out = state.getIList();
        MyIDictionary<String,Value> symTable = state.getSymTable();
        Value v = this.exp.eval(symTable, state.getHeap());
        out.add(v);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new PrintStmt(this.exp);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        exp.typecheck(typeEnv);
        return typeEnv;
    }
}
