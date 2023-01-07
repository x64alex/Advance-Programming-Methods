package com.example.toylanguage.Controller;


import com.example.toylanguage.Exceptions.MyException;
import com.example.toylanguage.Model.ADT.Dictionary.MyDictionary;
import com.example.toylanguage.Model.ADT.Dictionary.MyIDictionary;
import com.example.toylanguage.Model.ADT.FileTable.MyFileTable;
import com.example.toylanguage.Model.ADT.FileTable.MyIFileTable;
import com.example.toylanguage.Model.ADT.Heap.MyHeap;
import com.example.toylanguage.Model.ADT.Heap.MyIHeap;
import com.example.toylanguage.Model.ADT.List.MyIList;
import com.example.toylanguage.Model.ADT.List.MyList;
import com.example.toylanguage.Model.PrgState;
import com.example.toylanguage.Model.Values.RefValue;
import com.example.toylanguage.Model.Values.StringValue;
import com.example.toylanguage.Model.Values.Value;
import com.example.toylanguage.Repository.MyIRepository;
import com.example.toylanguage.Model.Types.*;


import java.io.BufferedReader;
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
    Map<Integer, Value> safeGarbageCollector(List<Integer> symTableAddr, List<Integer> heapAddr, Map<Integer,Value> heap){
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

    public void runTypeChecker() throws MyException {
        for (PrgState state: repo.getPrgList()) {
            MyIDictionary<String, Type> typeTable = new MyDictionary<>();
            state.getStk().peek().typecheck(typeTable);
        }
    }
    @Override
    public void allStep() throws MyException{
            runTypeChecker();
            executor = Executors.newFixedThreadPool(1);
            List<PrgState> prgList=removeCompletedPrg(repo.getPrgList());
            while(prgList.size() > 0){
                conservativeGarbageCollector(prgList);
                oneStepForAllPrg(prgList);
                //remove the completed programs
                prgList=removeCompletedPrg(repo.getPrgList());

            }
            executor.shutdownNow();
            repo.setPrgList(prgList);
        }

    @Override
    public void oneStepAll() throws MyException {
        executor = Executors.newFixedThreadPool(1);
        List<PrgState> prgList=removeCompletedPrg(repo.getPrgList());
        conservativeGarbageCollector(prgList);
        oneStepForAllPrg(prgList);
        //remove the completed programs
        prgList=removeCompletedPrg(repo.getPrgList());

        executor.shutdownNow();
        repo.setPrgList(prgList);
    }

    @Override
    public int getNumberPrgStates() {
        return repo.getPrgList().size();
    }

    @Override
    public boolean prgStatesDone() {
        return repo.getPrgList().size() == 0 || (repo.getPrgList().size() == 1 && repo.getPrgList().get(0).getStk().isEmpty());
    }

    @Override
    public MyIList<Value> getOutput() {
        MyIList<Value> output;
        if(getNumberPrgStates() > 0){
            PrgState prgstate =  repo.getPrgList().get(0);
            output = prgstate.getIList();
        }
        else output = new MyList<>();
        return output;
    }

    @Override
    public MyIHeap<Integer, Value> getHeap() {
        MyIHeap<Integer, Value> heap;
        if(getNumberPrgStates() > 0){
            PrgState prgstate =  repo.getPrgList().get(0);
            heap = prgstate.getHeap();
        }
        else heap = new MyHeap();
        return heap;
    }

    @Override
    public MyIFileTable<StringValue, BufferedReader> getFileTable() {
        MyIFileTable<StringValue, BufferedReader> fileTable;
        if(getNumberPrgStates() > 0){
            PrgState prgstate =  repo.getPrgList().get(0);
            fileTable = prgstate.getFileTable();
        }
        else fileTable = new MyFileTable<>();
        return fileTable;
    }
}
