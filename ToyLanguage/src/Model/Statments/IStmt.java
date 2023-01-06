package Model.Statments;
import Model.ADT.Dictionary.MyIDictionary;
import Model.PrgState;
import Model.Types.*;
import Exceptions.MyException;
public interface IStmt {
    PrgState execute(PrgState state) throws MyException;

    String toString();

    IStmt deepCopy();

    MyIDictionary<String,Type> typecheck(MyIDictionary<String,Type> typeEnv) throws
            MyException;
}
