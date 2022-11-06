package Model.Statments;

import Model.MyIStack;
import Model.PrgState;
import Exceptions.MyException;

class CompStmt implements IStmt {
    IStmt first;
    IStmt end;

    public String toString() {
        return "(" + first.toString() + ";" + end.toString() + ")";
    }

    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStk();
        stk.push(end);
        stk.push(first);
        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new CompStmt();
    }
}
