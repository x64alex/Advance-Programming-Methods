package com.example.toylanguage.Repository;


import com.example.toylanguage.Exceptions.MyException;
import com.example.toylanguage.Model.PrgState;

import java.util.List;

public interface MyIRepository {
    void setPrgList(List<PrgState> newList);
    List<PrgState> getPrgList();
    void logPrgStateExec(PrgState pr) throws MyException;
}
