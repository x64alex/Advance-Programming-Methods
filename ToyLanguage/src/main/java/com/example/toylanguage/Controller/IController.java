package com.example.toylanguage.Controller;


import com.example.toylanguage.Exceptions.MyException;
import com.example.toylanguage.Model.ADT.FileTable.MyFileTable;
import com.example.toylanguage.Model.ADT.FileTable.MyIFileTable;
import com.example.toylanguage.Model.ADT.Heap.MyIHeap;
import com.example.toylanguage.Model.ADT.List.MyIList;
import com.example.toylanguage.Model.PrgState;
import com.example.toylanguage.Model.Values.StringValue;
import com.example.toylanguage.Model.Values.Value;
import javafx.scene.control.IndexRange;

import java.io.BufferedReader;
import java.util.List;
import java.util.function.Predicate;

public interface IController {
    List<PrgState> removeCompletedPrg(List<PrgState> inPrgList);
    void allStep() throws MyException;

    void oneStepAll() throws  MyException;

    int getNumberPrgStates();

    boolean prgStatesDone();

    MyIList<Value> getOutput();
    MyIHeap<Integer, Value> getHeap();

    MyIFileTable<StringValue, BufferedReader> getFileTable();

    List<Integer> getPrgStateIdentifiers();

    Integer getFirstPrgState();

    PrgState getPrgState(Integer identifier);
}
