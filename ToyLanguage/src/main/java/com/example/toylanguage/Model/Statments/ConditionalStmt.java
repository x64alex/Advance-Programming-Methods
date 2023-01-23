package com.example.toylanguage.Model.Statments;

import com.example.toylanguage.Exceptions.MyException;
import com.example.toylanguage.Model.ADT.Stack.MyIStack;
import com.example.toylanguage.Model.ADT.SymTable.MyIDictionary;
import com.example.toylanguage.Model.Expressions.Exp;
import com.example.toylanguage.Model.PrgState;
import com.example.toylanguage.Model.Types.Type;


public class ConditionalStmt implements IStmt{

    String variable;
    Exp exp1;
    Exp exp2;
    Exp exp3;

    public ConditionalStmt(String var, Exp e1, Exp e2, Exp e3) {
        variable = var;
        exp1 = e1;
        exp2 = e2;
        exp3 = e3;
    }

    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStk();

        IStmt newStmt= new IfStmt(exp1, new AssignStmt(variable, exp2), new AssignStmt(variable, exp3));

        stk.push(newStmt);

        return null;
    }

    public String toString() {
        return "("+variable+"=" + exp1.toString() + "?" + exp2.toString() + ":" + exp3.toString() + ")";
    }

    @Override
    public IStmt deepCopy() {
        return new ConditionalStmt(variable,exp1,exp2,exp3);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }
}
