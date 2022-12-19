package Model.Statments;

import Model.ADT.Stack.MyIStack;
import Model.PrgState;
import Exceptions.MyException;

public class CompStmt implements IStmt {
    IStmt first;
    IStmt end;

    public CompStmt(IStmt first,IStmt end){
        this.first = first;
        this.end = end;
    }

    public String toString() {
        return "(" + first.toString() + ";" + end.toString() + ")";
    }

    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStk();
        stk.push(end);
        stk.push(first);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new CompStmt(this.first,this.end);
    }
}
