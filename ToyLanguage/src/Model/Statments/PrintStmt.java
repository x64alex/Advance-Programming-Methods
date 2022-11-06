package Model.Statments;

import Model.PrgState;

class PrintStmt implements IStmt {
    Exp exp;
    String toString(){ return "print(" +exp.toString()+")");}
    PrgState execute(PrgState state) throws MyException{ return state;
    }
}
