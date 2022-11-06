package Model.Statments;

import Model.Expressions.Exp;
import Model.PrgState;
import Exceptions.MyException;


class PrintStmt implements IStmt {
    Exp exp;
    public String toString(){ return "print(" +exp.toString()+")";}

    public PrgState execute(PrgState state) throws MyException{
        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new PrintStmt();
    }
}
