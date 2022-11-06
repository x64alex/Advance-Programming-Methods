package Model.Statments;
import Model.PrgState;
import Exceptions.MyException;
public interface IStmt {
    PrgState execute(PrgState state) throws MyException;

    String toString();

    IStmt deepCopy();
}
