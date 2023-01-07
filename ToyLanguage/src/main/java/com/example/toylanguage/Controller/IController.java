package com.example.toylanguage.Controller;


import com.example.toylanguage.Exceptions.MyException;
import com.example.toylanguage.Model.ADT.Heap.MyIHeap;
import com.example.toylanguage.Model.PrgState;
import com.example.toylanguage.Model.Values.Value;

import java.util.List;

public interface IController {
    List<PrgState> removeCompletedPrg(List<PrgState> inPrgList);
    void allStep() throws MyException;

    void oneStepAll() throws  MyException;

    int getNumberPrgStates();

    boolean prgStatesDone();

    MyIHeap<Integer, Value> getHeap();
}
