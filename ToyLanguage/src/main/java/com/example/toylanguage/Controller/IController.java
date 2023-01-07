package com.example.toylanguage.Controller;


import com.example.toylanguage.Exceptions.MyException;
import com.example.toylanguage.Model.PrgState;

import java.util.List;

public interface IController {
    List<PrgState> removeCompletedPrg(List<PrgState> inPrgList);
    void allStep() throws MyException;
}
