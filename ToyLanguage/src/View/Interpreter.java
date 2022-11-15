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
import Model.Statments.RWStatments.closeRFile;
import Model.Statments.RWStatments.openRFile;
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
        MyIRepository repo1 = new MyRepository(prg1, "log1.txt");
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
        MyIRepository repo2 = new MyRepository(prg2,"log2.txt");
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
        MyIRepository repo3 = new MyRepository(prg3,"log3.txt");
        Controller ctr3 = new Controller(repo3);


        //bool v; v=false; bool c; c = false; bool d; d= v and c; print(d)
        MyIStack<IStmt> stk4 = new MyStack<>();
        MyIDictionary<String,Value> sTable4 = new MyDictionary<>();
        MyIList<Value> ot4 = new MyList<>();
        IStmt ex4= new CompStmt(new VarDeclStmt("v",new BoolType()),
                new CompStmt(new AssignStmt("v",new ValueExp(new BoolValue(false))), new CompStmt(new VarDeclStmt("c",new BoolType()),
                        new CompStmt(new AssignStmt("c",new ValueExp(new BoolValue(true))),
                        new CompStmt(new VarDeclStmt("d",new BoolType()),
                                new CompStmt(new AssignStmt("d",new LogicExp("or",new VarExp("c"),new VarExp("d"))),
                                        new PrintStmt(new VarExp("d"))
                                ))))));
        PrgState prg4 = new PrgState(stk4,sTable4,ot4,ex4);
        MyIRepository repo4 = new MyRepository(prg4,"log4.txt");
        Controller ctr4 = new Controller(repo4);

        // string varf;
        // varf="test.in";
        // openRFile(varf);
        // int varc;
        // readFile(varf,varc);print(varc);
        // readFile(varf,varc);print(varc)
        // closeRFile(varf)
        MyIStack<IStmt> stk5 = new MyStack<>();
        MyIDictionary<String,Value> sTable5 = new MyDictionary<>();
        MyIList<Value> ot5 = new MyList<>();
        IStmt ex5= new CompStmt(new VarDeclStmt("varf",new StringType()),new CompStmt(new AssignStmt("varf",new ValueExp(new StringValue("test.in"))),
                        new CompStmt(new openRFile(new VarExp("varf")),
                        new closeRFile(new VarExp("varf"))
                )));
        PrgState prg5 = new PrgState(stk5,sTable5,ot5,ex5);
        MyIRepository repo5 = new MyRepository(prg5,"log5.txt");
        Controller ctr5 = new Controller(repo5);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), ctr1));
        menu.addCommand(new RunExample("2",ex2.toString(), ctr2));
        menu.addCommand(new RunExample("3",ex3.toString(),ctr3));
        menu.addCommand(new RunExample("4",ex4.toString(),ctr4));
        menu.addCommand(new RunExample("5",ex5.toString(),ctr5));
        menu.show();
    }
}