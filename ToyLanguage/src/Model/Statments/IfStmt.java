package Model.Statments;

import Exceptions.MyException;
import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.Stack.MyIStack;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Types.BoolType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.Value;

public class IfStmt implements IStmt {
    Exp exp;
    IStmt thenS;
    IStmt elseS;

    public IfStmt(Exp e, IStmt t, IStmt el) {
        exp = e;
        thenS = t;
        elseS = el;
    }

    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIStack<IStmt> stk = state.getStk();

        Value expression = this.exp.eval(symTbl);
        Type bool = new BoolType();
        if(bool.equals(expression.getType())){
            BoolValue boolExpression = (BoolValue) expression;
            boolean value = boolExpression.getVal();
            if(value){
                stk.push(this.thenS);
            }
            else {
                stk.push(this.elseS);
            }
        }
        else{ throw new MyException("Not bool type in if\n");}
        return state;
    }

    public String toString() {
        return "(IF(" + exp.toString() + ") THEN(" + thenS.toString() + ")ELSE(" + elseS.toString() + "))";
    }

    @Override
    public IStmt deepCopy() {
        return new IfStmt(this.exp,this.thenS,this.elseS);
    }
}
