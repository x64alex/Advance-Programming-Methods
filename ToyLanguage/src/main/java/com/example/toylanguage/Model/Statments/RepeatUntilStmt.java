package com.example.toylanguage.Model.Statments;

import com.example.toylanguage.Exceptions.MyException;
import com.example.toylanguage.Model.ADT.Stack.MyIStack;
import com.example.toylanguage.Model.ADT.SymTable.MyIDictionary;
import com.example.toylanguage.Model.Expressions.Exp;
import com.example.toylanguage.Model.Expressions.LogicExp;
import com.example.toylanguage.Model.Expressions.NotExp;
import com.example.toylanguage.Model.Expressions.RelExp;
import com.example.toylanguage.Model.PrgState;
import com.example.toylanguage.Model.Types.Type;

public class RepeatUntilStmt implements IStmt{
    Exp exp;

    IStmt stmt;

    public RepeatUntilStmt(Exp e, IStmt s) {
        exp = e;
        stmt = s;
    }

    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStk();

        IStmt expression = new CompStmt(stmt, new WhileStmt(new NotExp(exp),stmt));

        stk.push(expression);

        return null;
    }

    public String toString() {
        return "(Repeat "+stmt+" until "+exp+")";
    }

    @Override
    public IStmt deepCopy() {
        return new RepeatUntilStmt(exp,stmt);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }
}
