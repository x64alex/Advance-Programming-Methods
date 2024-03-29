package com.example.toylanguage.Model.Statments;


import com.example.toylanguage.Exceptions.MyException;
import com.example.toylanguage.Model.ADT.SymTable.MyIDictionary;
import com.example.toylanguage.Model.ADT.Stack.MyIStack;
import com.example.toylanguage.Model.PrgState;
import com.example.toylanguage.Model.Types.*;


public class CompStmt implements IStmt {
    IStmt first;
    IStmt end;

    public CompStmt(IStmt first,IStmt end){
        this.first = first;
        this.end = end;
    }

    public String toString() {
        return "(" + first.toString() + ";" + end.toString() + ")";
    }

    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStk();
        stk.push(end);
        stk.push(first);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new CompStmt(this.first,this.end);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        //MyIDictionary<String,Type> typEnv1 = first.typecheck(typeEnv);
        //MyIDictionary<String,Type> typEnv2 = end.typecheck(typEnv1);
        //return typEnv2;
        return end.typecheck(first.typecheck(typeEnv));
    }
}
