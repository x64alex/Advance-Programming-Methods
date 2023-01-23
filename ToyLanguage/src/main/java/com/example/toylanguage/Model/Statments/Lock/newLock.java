package com.example.toylanguage.Model.Statments.Lock;

import com.example.toylanguage.Exceptions.MyException;
import com.example.toylanguage.Model.ADT.LockTable.MyILockTable;
import com.example.toylanguage.Model.ADT.Stack.MyIStack;
import com.example.toylanguage.Model.ADT.SymTable.MyIDictionary;
import com.example.toylanguage.Model.Expressions.Exp;
import com.example.toylanguage.Model.PrgState;
import com.example.toylanguage.Model.Statments.IStmt;
import com.example.toylanguage.Model.Types.Type;
import com.example.toylanguage.Model.Values.IntValue;
import com.example.toylanguage.Model.Values.Value;

public class newLock implements IStmt {

    String variable;

    public newLock(String var) {
        this.variable = var;
    }

    public String toString() {
        return "newLock(" + variable + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStk();
        MyILockTable<Integer, Integer> lockTable = state.getLockTable();
        MyIDictionary<String, Value> symTbl = state.getSymTable();

        if(symTbl.isDefined(variable)){
            int val = lockTable.getFreeLocation();
            lockTable.initialize(-1);
            IntValue intValue = new IntValue(val);
            symTbl.update(variable, intValue);

        }
        else throw new MyException("the used variable" + variable + " was not declared before");

        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new newLock(variable);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }
}
