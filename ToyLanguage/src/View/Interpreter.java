package View;

import Model.ADT.Dictionary.MyDictionary;
import Model.ADT.List.MyList;
import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.List.MyIList;
import Model.ADT.Stack.MyIStack;
import Model.ADT.Stack.MyStack;
import Model.Expressions.VarExp;
import Model.PrgState;
import Model.Statments.*;
import Controller.Controller;
import Model.Types.*;
import Model.Values.*;
import Model.Expressions.*;
import Repository.MyIRepository;
import Repository.MyRepository;


class Interpreter {
    public static void main(String[] args) {

        //int v; v=2;Print(v)
        MyIStack<IStmt> stk1 = new MyStack<>();
        MyIDictionary<String,Value> sTable1 = new MyDictionary<>();
        MyIList<Value> ot1 = new MyList<>();
        IStmt ex1= new CompStmt(new VarDeclStmt("v",new IntType()),
                new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
        PrgState prg1 = new PrgState(stk1,sTable1,ot1,ex1);
        MyIRepository repo1 = new MyRepository(prg1);
        Controller ctr1 = new Controller(repo1);

        //int a;int b; a=2+3*5;b=a+1;Print(b)
        MyIStack<IStmt> stk2 = new MyStack<>();
        MyIDictionary<String,Value> sTable2 = new MyDictionary<>();
        MyIList<Value> ot2 = new MyList<>();
        IStmt ex2 = new CompStmt( new VarDeclStmt("a",new IntType()),
                new CompStmt(new VarDeclStmt("b",new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp('+',new ValueExp(new IntValue(2)),new ArithExp('*',new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                new CompStmt(new AssignStmt("b",new ArithExp('+',new VarExp("a"), new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
        PrgState prg2 = new PrgState(stk2, sTable2, ot2, ex2);
        MyIRepository repo2 = new MyRepository(prg2);
        Controller ctr2 = new Controller(repo2);

        //bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)
        MyIStack<IStmt> stk3 = new MyStack<>();
        MyIDictionary<String,Value> sTable3 = new MyDictionary<>();
        MyIList<Value> ot3 = new MyList<>();
        IStmt ex3 = new CompStmt(new VarDeclStmt("a",new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                                        VarExp("v"))))));
        PrgState prg3 = new PrgState(stk3,sTable3,ot3,ex3);
        MyIRepository repo3 = new MyRepository(prg3);
        Controller ctr3 = new Controller(repo3);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), ctr1));
        menu.addCommand(new RunExample("2",ex2.toString(), ctr2));
        menu.addCommand(new RunExample("3",ex3.toString(),ctr3));
        menu.show();
    }
}