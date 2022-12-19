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
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller implements IController{
    private MyIRepository repo;

    private ExecutorService executor;
    public Controller(MyIRepository myRepository){
        this.repo = myRepository;
    }

    private void conservativeGarbageCollector(List<PrgState> prgList){
        for (PrgState prg:prgList) {
            prg.getHeap().setContent(safeGarbageCollector(getAddrFromSymTable(prg.getSymTable().getValues()),getAddrFromHeap(prg.getHeap().getValues()), prg.getHeap().getContent()));
        }
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
    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) {
        return (List<PrgState>) inPrgList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
    }

    void oneStepForAllPrg(List<PrgState> prgList) {
        prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (MyException e) {
                throw new RuntimeException(e);
            }
        });
        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>) (() -> {
                    return p.oneStep();
                }))
                .collect(Collectors.toList());


        try {
            List<PrgState> newPrgList = executor.invokeAll(callList).stream()
                    .map(future -> { try {
                        return future.get();
                    }catch (Exception e) {
                        return null;
                    }})
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            prgList.addAll(newPrgList);

        }catch (Exception e){
            System.out.println(e);
        }


        prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (MyException e) {
                System.out.println(e);
            }
        });


        repo.setPrgList(prgList);
    }

    @Override
    public void allStep() {
            executor = Executors.newFixedThreadPool(1);
            List<PrgState> prgList=removeCompletedPrg(repo.getPrgList());
            while(prgList.size() > 0){
                conservativeGarbageCollector(prgList);
                oneStepForAllPrg(prgList);
//remove the completed programs
                prgList=removeCompletedPrg(repo.getPrgList());

            }
            executor.shutdownNow();
//HERE the repository still contains at least one Completed Prg
// and its List<PrgState> is not empty. Note that oneStepForAllPrg calls the method
//setPrgList of repository in order to change the repository
// update the repository state
            repo.setPrgList(prgList);
        }
}
