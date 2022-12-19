package Model.Statments;

import Exceptions.MyException;
import Model.ADT.Dictionary.MyDictionary;
import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.List.MyIList;
import Model.ADT.List.MyList;
import Model.ADT.Stack.MyIStack;
import Model.ADT.Stack.MyStack;
import Model.PrgState;
import Model.Values.Value;

public class ForkStmt implements IStmt{
    IStmt forkstmt;

    public ForkStmt(IStmt forkstmt){
        this.forkstmt = forkstmt;
    }

    public String toString() {
        return "Fork:(" + forkstmt.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = new MyStack<>();
        MyIDictionary<String, Value> sTable = state.getSymTable().deepCopy();
        MyIList<Value> ot = state.getIList();
        PrgState newPrgState = new PrgState(stk, sTable,ot, forkstmt);
        newPrgState.setHeap(state.getHeap());
        return newPrgState;
    }

    @Override
    public IStmt deepCopy() {
        return null;
    }
}
