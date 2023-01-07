package com.example.toylanguage.Model;


import com.example.toylanguage.Exceptions.MyException;
import com.example.toylanguage.Model.ADT.Dictionary.MyIDictionary;
import com.example.toylanguage.Model.ADT.FileTable.MyIFileTable;
import com.example.toylanguage.Model.ADT.Heap.MyHeap;
import com.example.toylanguage.Model.ADT.Heap.MyIHeap;
import com.example.toylanguage.Model.ADT.List.MyIList;
import com.example.toylanguage.Model.ADT.Stack.MyIStack;
import com.example.toylanguage.Model.Statments.IStmt;
import com.example.toylanguage.Model.Values.StringValue;
import com.example.toylanguage.Model.Values.Value;


import java.io.BufferedReader;

public class PrgState {
    static int id = 0;

    int currentId = 0;
    MyIStack<IStmt> exeStack;
    MyIDictionary<String, Value> symTable;
    MyIList<Value> out;

    MyIHeap<Integer, Value> heap;

    MyIFileTable<StringValue, BufferedReader> FileTable;
    IStmt originalProgram; //optional field, but good to have
    public PrgState(MyIStack<IStmt> stk,
                    MyIDictionary<String,Value> sTable,
                    MyIList<Value> ot,
                    MyIFileTable<StringValue, BufferedReader> ft,
                    IStmt prg){
        exeStack=stk;
        symTable=sTable;
        out = ot;
        originalProgram=prg.deepCopy();//recreate the entire original prg
        FileTable = ft;
        heap = new MyHeap<>();
        stk.push(prg);
        this.currentId = id;
        id +=1;
    }

    public PrgState oneStep() throws MyException {
        if(exeStack.isEmpty()){ throw new MyException("Program State stack is empty");}
        IStmt crtStmt = exeStack.pop();
        return crtStmt.execute(this);
    }
    public MyIStack<IStmt> getStk(){
        return this.exeStack;
    }

    public MyIDictionary<String, Value> getSymTable() {return this.symTable; }

    public MyIList<Value> getIList() {return this.out; }

    public MyIFileTable<StringValue, BufferedReader> getFileTable() {return  this.FileTable;}

    public MyIHeap<Integer, Value> getHeap() {return this.heap; }

    public void setHeap(MyIHeap<Integer, Value> newHeap) {this.heap = newHeap; }


    public Boolean isNotCompleted() {return !this.exeStack.isEmpty();}
    @Override
    public String toString() {
        return "Id:"+this.currentId+"\n"+
                "ExeStack:\n" + exeStack +
                "\nSymTable:\n" + symTable +
                "\nOut:\n" + out +
                "\nFileTable:\n" + FileTable+
                "\nHeap:\n" + heap+
                "\n\n";
    }
}
