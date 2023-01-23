package com.example.toylanguage.Model.Statments.Lock;

import com.example.toylanguage.Exceptions.MyException;
import com.example.toylanguage.Model.ADT.LockTable.MyILockTable;
import com.example.toylanguage.Model.ADT.Stack.MyIStack;
import com.example.toylanguage.Model.ADT.SymTable.MyIDictionary;
import com.example.toylanguage.Model.PrgState;
import com.example.toylanguage.Model.Statments.IStmt;
import com.example.toylanguage.Model.Types.Type;
import com.example.toylanguage.Model.Values.IntValue;
import com.example.toylanguage.Model.Values.Value;

public class lock implements IStmt {
    String variable;

    public lock(String var) {
        this.variable = var;
    }

    public String toString() {
        return "lock(" + variable + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStk();
        MyILockTable<Integer, Integer> lockTable = state.getLockTable();
        MyIDictionary<String, Value> symTbl = state.getSymTable();

        if(symTbl.isDefined(variable)){
            Value value = symTbl.lookup(variable);
            try{
                Integer foundIndex = (Integer) ((IntValue) value).getVal();
                if(lockTable.isDefined(foundIndex)){
                    if(lockTable.lookup(foundIndex) == -1){
                        lockTable.update(foundIndex, state.currentId);
                    }
                }else throw new MyException("Exception");
            }catch (Error e){
                throw new MyException("the variable "+ variable+ " has not int type");
            }
        }
        else throw new MyException("the used variable" + variable + " was not declared before");

        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new lock(variable);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }
}
