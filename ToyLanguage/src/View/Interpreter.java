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
import Model.Statments.RWStatments.readFile;
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
        // closeRFile(varf)
        MyIStack<IStmt> stk5 = new MyStack<>();
        MyIDictionary<String,Value> sTable5 = new MyDictionary<>();
        MyIList<Value> ot5 = new MyList<>();
        IStmt ex5= new CompStmt(new VarDeclStmt("varf",new StringType()),new CompStmt(new AssignStmt("varf",new ValueExp(new StringValue("test.in"))),
                        new CompStmt(new openRFile(new VarExp("varf")),
                        new CompStmt(new CompStmt(new VarDeclStmt("varc",new IntType()),
                                new CompStmt(new readFile(new VarExp("varf"),"varc"),
                                        new PrintStmt(new VarExp("varc"))
                                )),
                                new closeRFile(new VarExp("varf")
                        ))
                )));
        PrgState prg5 = new PrgState(stk5,sTable5,ot5,ex5);
        MyIRepository repo5 = new MyRepository(prg5,"log5.txt");
        Controller ctr5 = new Controller(repo5);


        //int v; v=4; (while (v>0) print(v);v=v-1);print(v)
        MyIStack<IStmt> stk6 = new MyStack<>();
        MyIDictionary<String,Value> sTable6 = new MyDictionary<>();
        MyIList<Value> ot6 = new MyList<>();
        IStmt ex6= new CompStmt(new VarDeclStmt("v",new IntType()),
                new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(4))), new CompStmt(new WhileStmt(new RelExp(">", new VarExp("v"), new ValueExp(new IntValue(0))),
                        new CompStmt(new PrintStmt(new VarExp("v")),new AssignStmt("v",new ArithExp('-',new VarExp("v"), new ValueExp(new IntValue(1)) ) ))) ,new PrintStmt(new VarExp("v")))));
        PrgState prg6 = new PrgState(stk6,sTable6,ot6,ex6);
        MyIRepository repo6 = new MyRepository(prg6, "log6.txt");
        Controller ctr6 = new Controller(repo6);

        //Ref int v;new(v,20);Ref Ref int a; new(a,v);print(v);print(a);
        MyIStack<IStmt> stk7 = new MyStack<>();
        MyIDictionary<String,Value> sTable7 = new MyDictionary<>();
        MyIList<Value> ot7 = new MyList<>();
        IStmt ex7= new CompStmt(new VarDeclStmt("v",new RefType( new IntType())), new CompStmt( new NewStmt("v", new ValueExp(new IntValue(20))), new CompStmt(
                new VarDeclStmt("a",new RefType(new RefType(new IntType()))), new CompStmt(new NewStmt("a", new VarExp("v")),
                new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new VarExp("a")))
        ))));
        PrgState prg7 = new PrgState(stk7,sTable7,ot7,ex7);
        MyIRepository repo7 = new MyRepository(prg7, "log7.txt");
        Controller ctr7 = new Controller(repo7);

        //Ref int v;new(v,20);Ref Ref int a; new(a,v);print(rH(v));print(rH(rH(a))+5)
        MyIStack<IStmt> stk8 = new MyStack<>();
        MyIDictionary<String,Value> sTable8 = new MyDictionary<>();
        MyIList<Value> ot8 = new MyList<>();
        IStmt ex8= new CompStmt(new VarDeclStmt("v",new RefType( new IntType())), new CompStmt( new NewStmt("v", new ValueExp(new IntValue(20))), new CompStmt(
                new VarDeclStmt("a",new RefType(new RefType(new IntType()))), new CompStmt(new NewStmt("a", new VarExp("v")),
                new CompStmt(new PrintStmt(new RhExp(new VarExp("v"))), new PrintStmt(new ArithExp('+',new RhExp(new RhExp(new VarExp("a"))) ,new ValueExp(new IntValue(5)))))
        ))));
        PrgState prg8 = new PrgState(stk8,sTable8,ot8,ex8);
        MyIRepository repo8 = new MyRepository(prg8, "log8.txt");
        Controller ctr8 = new Controller(repo8);

        //Ref int v;new(v,20);print(rH(v)); wH(v,30);print(rH(v)+5);
        MyIStack<IStmt> stk9 = new MyStack<>();
        MyIDictionary<String,Value> sTable9 = new MyDictionary<>();
        MyIList<Value> ot9 = new MyList<>();
        IStmt ex9= new CompStmt(new VarDeclStmt("v",new RefType( new IntType())), new CompStmt( new NewStmt("v", new ValueExp(new IntValue(20))),
                new CompStmt(new PrintStmt(new RhExp(new VarExp("v"))),
                new CompStmt(new WhStmt("v",new ValueExp(new IntValue(30))), new PrintStmt(new ArithExp('+',new RhExp(new VarExp("v")) ,new ValueExp(new IntValue(5)))))
        )));
        PrgState prg9 = new PrgState(stk9,sTable9,ot9,ex9);
        MyIRepository repo9 = new MyRepository(prg9, "log9.txt");
        Controller ctr9 = new Controller(repo9);

        //Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30); new(v,40);print(rH(rH(a))); print(rH(rH(a)));
        MyIStack<IStmt> stk10 = new MyStack<>();
        MyIDictionary<String,Value> sTable10 = new MyDictionary<>();
        MyIList<Value> ot10 = new MyList<>();
        IStmt ex10= new CompStmt(new VarDeclStmt("v",new RefType( new IntType())), new CompStmt( new NewStmt("v", new ValueExp(new IntValue(20))), new CompStmt(
                new VarDeclStmt("a",new RefType(new RefType(new IntType()))), new CompStmt(new NewStmt("a", new VarExp("v")),
                new CompStmt(new CompStmt(new NewStmt("v", new ValueExp(new IntValue(30))), new NewStmt("v", new ValueExp(new IntValue(40)))), new CompStmt(new PrintStmt(new RhExp(new RhExp(new VarExp("a")))) ,new PrintStmt(new RhExp(new RhExp(new VarExp("a")))))
                )))));
        PrgState prg10 = new PrgState(stk10,sTable10,ot10,ex10);
        MyIRepository repo10 = new MyRepository(prg10, "log10.txt");
        Controller ctr10 = new Controller(repo10);

        //int v; Ref int a; v=10;new(a,22);
        //fork(wH(a,30);v=32;    print(v);print(rH(a)));
        //print(v);print(rH(a))
        MyIStack<IStmt> stk11 = new MyStack<>();
        MyIDictionary<String,Value> sTable11 = new MyDictionary<>();
        MyIList<Value> ot11 = new MyList<>();
        IStmt ex11= new CompStmt(new CompStmt(new CompStmt(
                new VarDeclStmt("v",new IntType()), new VarDeclStmt("a",new RefType(new IntType()))),
                new CompStmt(new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(10))), new NewStmt("a", new ValueExp(new IntValue(22)))),
                        new ForkStmt(new CompStmt(
                                new WhStmt("a", new ValueExp(new IntValue(30))),
                                new CompStmt(
                                        new AssignStmt("v", new ValueExp(new IntValue(32))),
                                        new CompStmt(
                                                new PrintStmt(new VarExp("v")),
                                                new PrintStmt(new RhExp(new VarExp("a")))
                                        )
                                )
                        )

                        ))),
                new CompStmt(
                        new PrintStmt(new VarExp("v")),
                        new CompStmt(
                                new PrintStmt(new VarExp("v")),
                                new PrintStmt(new RhExp(new VarExp("a"))))));
        PrgState prg11 = new PrgState(stk11,sTable11,ot11,ex11);
        MyIRepository repo11 = new MyRepository(prg11, "log11.txt");
        Controller ctr11 = new Controller(repo11);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), ctr1));
        menu.addCommand(new RunExample("2",ex2.toString(), ctr2));
        menu.addCommand(new RunExample("3",ex3.toString(),ctr3));
        menu.addCommand(new RunExample("4",ex4.toString(),ctr4));
        menu.addCommand(new RunExample("5",ex5.toString(),ctr5));
        menu.addCommand(new RunExample("6",ex6.toString(),ctr6));
        menu.addCommand(new RunExample("7",ex7.toString(),ctr7));
        menu.addCommand(new RunExample("8",ex8.toString(),ctr8));
        menu.addCommand(new RunExample("9",ex9.toString(),ctr9));
        menu.addCommand(new RunExample("10",ex10.toString(),ctr10));
        menu.addCommand(new RunExample("11",ex11.toString(),ctr11));
        menu.show();
    }
}