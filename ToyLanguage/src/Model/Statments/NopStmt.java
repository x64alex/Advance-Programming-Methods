package Model.Statments;

import Exceptions.MyException;
import Model.PrgState;

public class NopStmt implements IStmt{
    public NopStmt(){}

    @Override
    public PrgState execute(PrgState state) throws MyException {
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new NopStmt();
    }
}
