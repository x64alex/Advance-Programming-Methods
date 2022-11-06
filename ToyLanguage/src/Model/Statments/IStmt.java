package Model.Statments;

import Model.PrgState;

public interface IStmt {
    PrgState execute(PrgState state) throws MyException;
}
