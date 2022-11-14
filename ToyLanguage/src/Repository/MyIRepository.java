package Repository;

import Exceptions.MyException;
import Model.PrgState;

public interface MyIRepository {
    PrgState getCrtPrg();
    void logPrgStateExec() throws MyException;
}
