package Model;


import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.FileTable.MyFileTable;
import Model.ADT.FileTable.MyIFileTable;
import Model.ADT.Heap.MyHeap;
import Model.ADT.Heap.MyIHeap;
import Model.ADT.List.MyIList;
import Model.ADT.Stack.MyIStack;
import Model.Statments.IStmt;
import Model.Types.Type;
import Model.Values.StringValue;
import Model.Values.Value;
import java.io.BufferedReader;


public class PrgState {
    MyIStack<IStmt> exeStack;
    MyIDictionary<String, Value> symTable;
    MyIList<Value> out;

    MyIHeap<Integer, Value> heap;

    MyIFileTable<StringValue, BufferedReader> FileTable;
    IStmt originalProgram; //optional field, but good to have
    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String,Value> sTable, MyIList<Value> ot, IStmt prg){
        exeStack=stk;
        symTable=sTable;
        out = ot;
        originalProgram=prg.deepCopy();//recreate the entire original prg
        FileTable = new MyFileTable<>();
        heap = new MyHeap<>();
        stk.push(prg);
    }
    public MyIStack<IStmt> getStk(){
        return this.exeStack;
    }

    public MyIDictionary<String, Value> getSymTable() {return this.symTable; }

    public MyIList<Value> getIList() {return this.out; }

    public MyIFileTable<StringValue, BufferedReader> getFileTable() {return  this.FileTable;}

    public MyIHeap<Integer, Value> getHeap() {return this.heap; }

    @Override
    public String toString() {
        return "ExeStack:\n" + exeStack +
                "\nSymTable:\n" + symTable +
                "\nOut:\n" + out +
                "\nFileTable:\n" + FileTable+
                "\nHeap:\n" + heap+
                "\n\n";
    }
}
