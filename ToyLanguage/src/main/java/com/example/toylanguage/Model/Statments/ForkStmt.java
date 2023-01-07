package com.example.toylanguage.Model.Statments;


import com.example.toylanguage.Exceptions.MyException;
import com.example.toylanguage.Model.ADT.Dictionary.MyIDictionary;
import com.example.toylanguage.Model.ADT.List.MyIList;
import com.example.toylanguage.Model.ADT.Stack.MyIStack;
import com.example.toylanguage.Model.ADT.Stack.MyStack;
import com.example.toylanguage.Model.PrgState;
import com.example.toylanguage.Model.Values.Value;
import com.example.toylanguage.Model.Types.*;

public class ForkStmt implements IStmt{
    IStmt forkstmt;

    public ForkStmt(IStmt forkstmt){
        this.forkstmt = forkstmt;
    }

    public String toString() {
        return "Fork:(" + forkstmt.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = new MyStack<>();
        MyIDictionary<String, Value> sTable = state.getSymTable().deepCopy();
        MyIList<Value> ot = state.getIList();
        PrgState newPrgState = new PrgState(stk, sTable,ot, forkstmt);
        newPrgState.setHeap(state.getHeap());
        return newPrgState;
    }

    @Override
    public IStmt deepCopy() {
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        forkstmt.typecheck(typeEnv.deepCopy());
        return  typeEnv;
    }
}
