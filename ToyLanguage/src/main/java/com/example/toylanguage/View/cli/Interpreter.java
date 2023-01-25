package com.example.toylanguage.View.cli;


import com.example.toylanguage.Model.ADT.Barrier.CyclicBarrier;
import com.example.toylanguage.Model.ADT.Barrier.ICyclicBarrier;
import com.example.toylanguage.Model.ADT.SymTable.MyDictionary;
import com.example.toylanguage.Model.ADT.SymTable.MyIDictionary;
import com.example.toylanguage.Model.ADT.FileTable.MyFileTable;
import com.example.toylanguage.Model.ADT.List.MyIList;
import com.example.toylanguage.Model.ADT.List.MyList;
import com.example.toylanguage.Model.ADT.Stack.MyIStack;
import com.example.toylanguage.Model.ADT.Stack.MyStack;
import com.example.toylanguage.Model.PairBarrier.PairBarrier;
import com.example.toylanguage.Model.PrgState;
import com.example.toylanguage.Model.Statments.IStmt;
import com.example.toylanguage.Model.Values.StringValue;
import com.example.toylanguage.Model.Values.Value;
import com.example.toylanguage.Repository.MyIRepository;
import com.example.toylanguage.Repository.MyRepository;
import com.example.toylanguage.View.Examples;
import com.example.toylanguage.View.cli.Command.ExitCommand;
import com.example.toylanguage.View.cli.Command.RunExample;
import com.example.toylanguage.Controller.*;

import java.io.BufferedReader;
import java.util.List;

class Interpreter {
    public static void main(String[] args) {

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        int pos = 1;
        for(IStmt stmt: Examples.exampleList()) {
            MyIStack<IStmt> stk = new MyStack<>();
            MyIDictionary<String, Value> sTable = new MyDictionary<>();
            MyIList<Value> ot = new MyList<>();
            MyFileTable<StringValue, BufferedReader> ft = new MyFileTable<>();
            ICyclicBarrier<Integer, PairBarrier<Integer, List<Integer>>> barrier = new CyclicBarrier<>();
            PrgState prg = new PrgState(stk,sTable,ot,ft,barrier,stmt);
            MyIRepository repo = new MyRepository(prg, "log"+Integer.toString(pos)+".txt");
            Controller ctr = new Controller(repo);
            menu.addCommand(new RunExample(Integer.toString(pos), stmt.toString(), ctr));
            pos += 1;
        }
        menu.show();
    }
}