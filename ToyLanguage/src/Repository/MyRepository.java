package Repository;

import Exceptions.MyException;
import Model.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class MyRepository implements MyIRepository {
    private PrgState currentState;
    private String logFilePath;

    public MyRepository(PrgState state, String logFilePath){
        this.currentState = state;
        this.logFilePath = logFilePath;
    }

    @Override
    public PrgState getCrtPrg() {
        return currentState;
    }

    @Override
    public void logPrgStateExec() throws MyException {
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            System.out.print(currentState.toString());
            logFile.append(currentState.toString());
            logFile.close();
        }catch (Exception e){
            throw new MyException(e.toString());
        }
    }
}
