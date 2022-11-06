package Model.Statments;

import Model.PrgState;

class CompStmt implements IStmt {
    IStmt first;
    IStmt end;
    String toString() {
        return "("+first.toString() + ";" + snd.toString()+")";
    }
    PrgState execute(PrgState state) throws MyException{
        MyIStack<IStmt> stk=state.getStk() stk.push(snd);
        stk.push(first);
        return state;
    }
