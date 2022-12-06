package Controller;

import Exceptions.MyException;
import Model.ADT.Stack.MyIStack;
import Model.PrgState;
import Model.Statments.IStmt;
import Model.Values.RefValue;
import Model.Values.Value;
import Repository.MyIRepository;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller implements IController{
    private MyIRepository repo;

    public Controller(MyIRepository myRepository){
        this.repo = myRepository;
    }


    @Override
    public PrgState oneStep(PrgState state) throws MyException {
        MyIStack<IStmt> stk=state.getStk();
        if(stk.isEmpty()){ throw new MyException("Program State stack is empty");}
        IStmt crtStmt = stk.pop();
        return crtStmt.execute(state);
    }

    Map<Integer, Value> safeGarbageCollector(List<Integer> symTableAddr,List<Integer> heapAddr, Map<Integer,Value> heap){
        return heap.entrySet().stream()
                .filter(e->(symTableAddr.contains(e.getKey()) || heapAddr.contains(e.getKey())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    List<Integer> getAddrFromSymTable(Collection<Value> symTableValues){
        return symTableValues.stream()
                .filter(v-> v instanceof RefValue)
                .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddr();})
                .collect(Collectors.toList());
    }

    List<Integer> getAddrFromHeap(Collection<Value> heapValues){
        return heapValues.stream()
                .filter(v-> v instanceof RefValue)
                .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddr();})
                .collect(Collectors.toList());
    }

    @Override
    public void allStep() {
        PrgState prg = repo.getCrtPrg(); // repo is the controller field of type MyRepoInterface
        //here you can display the prg state
        try {
            repo.logPrgStateExec();
            while (!prg.getStk().isEmpty()) {
                this.oneStep(prg);

                prg.getHeap().setContent(safeGarbageCollector(getAddrFromSymTable(prg.getSymTable().getValues()),getAddrFromHeap(prg.getHeap().getValues()), prg.getHeap().getContent()));
                repo.logPrgStateExec();
                this.displayState();
            }
        }catch(MyException e){
            System.out.print(e);
        }
    }

    @Override
    public void displayState() {
        System.out.print(repo.getCrtPrg());
        System.out.print("\n\n");
    }
}
