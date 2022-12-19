package Repository;

import Exceptions.MyException;
import Model.PrgState;

import java.util.List;

public interface MyIRepository {
    void setPrgList(List<PrgState> newList);
    List<PrgState> getPrgList();
    void logPrgStateExec(PrgState pr) throws MyException;
}
