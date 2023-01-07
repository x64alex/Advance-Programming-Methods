package View.cli;

import Model.ADT.Dictionary.MyDictionary;
import Model.ADT.List.MyList;
import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.List.MyIList;
import Model.ADT.Stack.MyIStack;
import Model.ADT.Stack.MyStack;
import Model.PrgState;
import Model.Statments.*;
import Controller.Controller;
import Model.Values.*;
import Repository.MyIRepository;
import Repository.MyRepository;
import View.cli.Command.ExitCommand;
import View.cli.Command.RunExample;
import View.Examples;


class Interpreter {
    public static void main(String[] args) {

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        int pos = 1;
        for(IStmt stmt: Examples.exampleList()) {
            MyIStack<IStmt> stk = new MyStack<>();
            MyIDictionary<String,Value> sTable = new MyDictionary<>();
            MyIList<Value> ot = new MyList<>();
            PrgState prg = new PrgState(stk,sTable,ot,stmt);
            MyIRepository repo = new MyRepository(prg, "log"+Integer.toString(pos)+".txt");
            Controller ctr = new Controller(repo);
            menu.addCommand(new RunExample(Integer.toString(pos), stmt.toString(), ctr));
            System.out.println(pos);
            pos += 1;
        }
        menu.show();
    }
}