package Model.Statments;

import Exceptions.MyException;
import Model.ADT.Dictionary.MyIDictionary;
import Model.PrgState;
import Model.Types.Type;

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

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }
}
