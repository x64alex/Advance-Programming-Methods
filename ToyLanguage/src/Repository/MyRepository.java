package Repository;

import Exceptions.MyException;
import Model.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MyRepository implements MyIRepository {
    private List<PrgState> states = new ArrayList<>();
    private String logFilePath;

    public MyRepository(PrgState state, String logFilePath){
        this.states.add(state);
        this.logFilePath = logFilePath;
    }

    @Override
    public void setPrgList(List<PrgState> newList) {
        this.states = newList;
    }

    @Override
    public List<PrgState> getPrgList() { return this.states;}

    @Override
    public void logPrgStateExec(PrgState pr) throws MyException {
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            logFile.append(pr.toString());
            System.out.println(pr.toString());
            logFile.close();
        }catch (Exception e){
            throw new MyException(e.toString());
        }
    }
}
