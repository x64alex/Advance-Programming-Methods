package com.example.toylanguage.Model.Statments;

import com.example.toylanguage.Exceptions.MyException;
import com.example.toylanguage.Model.ADT.Stack.MyIStack;
import com.example.toylanguage.Model.ADT.SymTable.MyIDictionary;
import com.example.toylanguage.Model.Expressions.ArithExp;
import com.example.toylanguage.Model.Expressions.Exp;
import com.example.toylanguage.Model.Expressions.ValueExp;
import com.example.toylanguage.Model.PrgState;
import com.example.toylanguage.Model.Types.BoolType;
import com.example.toylanguage.Model.Types.IntType;
import com.example.toylanguage.Model.Types.Type;
import com.example.toylanguage.Model.Values.BoolValue;
import com.example.toylanguage.Model.Values.IntValue;
import com.example.toylanguage.Model.Values.Value;

public class WaitStmt implements IStmt{

    IntValue intValue;

    public WaitStmt(IntValue val){
        this.intValue = val;
    }

    public String toString() {
        return "Wait("+intValue.toString()+")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIStack<IStmt> stk = state.getStk();


        Type intType = new IntType();
        if(intType.equals(intValue.getType())){
            IntValue v = new IntValue(0);
            if(! v.equals(intValue)){
                Exp arithExp = new ArithExp('-',new ValueExp(intValue), new ValueExp(new IntValue(1)));
                Value valueExp = arithExp.eval(symTbl, state.getHeap());
                IntValue newValueExp = (IntValue) valueExp;

                stk.push(new CompStmt(new PrintStmt(new ValueExp(intValue)),new WaitStmt(newValueExp)));
            }
        }
        else{ throw new MyException("Not Int type in wait\n");}
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new WaitStmt(intValue);
    }



    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }
}
