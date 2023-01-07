package com.example.toylanguage.Model.Statments;


import com.example.toylanguage.Exceptions.MyException;
import com.example.toylanguage.Model.ADT.SymTable.MyIDictionary;
import com.example.toylanguage.Model.ADT.Stack.MyIStack;
import com.example.toylanguage.Model.Expressions.Exp;
import com.example.toylanguage.Model.PrgState;
import com.example.toylanguage.Model.Types.BoolType;
import com.example.toylanguage.Model.Types.Type;
import com.example.toylanguage.Model.Values.BoolValue;
import com.example.toylanguage.Model.Values.Value;


public class WhileStmt implements IStmt{

    Exp exp;
    IStmt stmt;

    public WhileStmt(Exp e, IStmt st) {
        exp = e;
        stmt = st;
    }

    public String toString() {
        return "(While(" + exp.toString() + ")" + stmt.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIStack<IStmt> stk = state.getStk();

        Value expression = this.exp.eval(symTbl, state.getHeap());
        Type bool = new BoolType();
        if(bool.equals(expression.getType())){
            BoolValue boolExpression = (BoolValue) expression;
            boolean value = boolExpression.getVal();
            if(value){
                stk.push(this);
                stk.push(this.stmt);
            }
        }
        else{ throw new MyException("Not bool type in while\n");}
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new WhileStmt(this.exp, this.stmt);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typexp=exp.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            stmt.typecheck(typeEnv.deepCopy());
            return typeEnv;
        }
        else
            throw new MyException("The condition of while has not the type bool");
    }
}
