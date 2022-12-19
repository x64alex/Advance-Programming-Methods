package Controller;

import Exceptions.MyException;
import Model.PrgState;

import java.util.List;

public interface IController {
    List<PrgState> removeCompletedPrg(List<PrgState> inPrgList);
    void allStep();
}
