package com.example.toylanguage.Model.Statments;

import com.example.toylanguage.Exceptions.MyException;
import com.example.toylanguage.Model.ADT.Stack.MyIStack;
import com.example.toylanguage.Model.ADT.SymTable.MyIDictionary;
import com.example.toylanguage.Model.Expressions.Exp;
import com.example.toylanguage.Model.Expressions.RelExp;
import com.example.toylanguage.Model.Expressions.ValueExp;
import com.example.toylanguage.Model.Expressions.VarExp;
import com.example.toylanguage.Model.PrgState;
import com.example.toylanguage.Model.Types.IntType;
import com.example.toylanguage.Model.Types.Type;
import com.example.toylanguage.Model.Values.IntValue;
import com.example.toylanguage.Model.Values.Value;

public class ForStmt implements IStmt{

    String variable;
    Exp exp1;
    Exp exp2;
    Exp exp3;
    IStmt stmt;

    public ForStmt(String var, Exp e1, Exp e2, Exp e3, IStmt st) {
        variable = var;
        exp1 = e1;
        exp2 = e2;
        exp3 = e3;
        stmt = st;
    }

    public String toString() {
        return "(For(" + variable +"="+ exp1.toString() +
                ";"+ variable +"=" +exp2.toString()+
                ";"+ variable +"=" +exp3.toString()+")" + stmt.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStk();

        IStmt newStmt= new CompStmt(new AssignStmt(variable,exp1), new WhileStmt(new RelExp("<",new VarExp(variable),exp2),new CompStmt(stmt,new AssignStmt("v",exp3))));

        stk.push(newStmt);

        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new ForStmt(variable,exp1,exp2,exp3,stmt);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }
}
