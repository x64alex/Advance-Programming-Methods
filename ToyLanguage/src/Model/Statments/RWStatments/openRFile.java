package Model.Statments.RWStatments;

import Exceptions.MyException;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Statments.IStmt;
import Model.Values.Value;

public class openRFile implements IStmt {

    private Exp exp;

    public openRFile(Exp exp){
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new openRFile(this.exp);
    }
}
