package com.example.toylanguage.Model.Statments;

import com.example.toylanguage.Exceptions.MyException;
import com.example.toylanguage.Model.ADT.Stack.MyIStack;
import com.example.toylanguage.Model.ADT.SymTable.MyIDictionary;
import com.example.toylanguage.Model.Expressions.Exp;
import com.example.toylanguage.Model.Expressions.RelExp;
import com.example.toylanguage.Model.PrgState;
import com.example.toylanguage.Model.Types.BoolType;
import com.example.toylanguage.Model.Types.Type;
import com.example.toylanguage.Model.Values.BoolValue;
import com.example.toylanguage.Model.Values.Value;

public class SwitchStmt implements IStmt{
    Exp exp;
    Exp exp1;
    Exp exp2;
    IStmt stmt1;
    IStmt stmt2;
    IStmt stmt3;

    public SwitchStmt(Exp e, Exp e1, Exp e2, IStmt s1, IStmt s2, IStmt s3) {
        exp = e;
        exp1 = e1;
        exp2 = e2;
        stmt1 = s1;
        stmt2 = s2;
        stmt3 = s3;
    }

    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStk();

        IStmt expression = new IfStmt(new RelExp("==", exp,exp1), stmt1, new IfStmt(new RelExp("=", exp,exp2), stmt2, stmt3));

        stk.push(expression);

        return null;
    }

    public String toString() {
        return "(Switch("+exp.toString()+")(case "+exp1.toString()+": "+
                stmt1.toString()+")(case "+exp2.toString()+": "+stmt2.toString()+")(default: "+stmt3.toString()+"))";
    }

    @Override
    public IStmt deepCopy() {
        return new SwitchStmt(exp,exp1,exp2,stmt1,stmt2,stmt3);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }
}
