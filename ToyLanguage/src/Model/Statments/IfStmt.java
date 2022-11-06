package Model.Statments;

import Exceptions.MyException;
import Model.Expressions.Exp;
import Model.PrgState;

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
