package Controller;

import Exceptions.MyException;
import Model.PrgState;

public interface IController {
    public PrgState oneStep(PrgState state) throws MyException;
    void allStep();
    void displayState();
}
