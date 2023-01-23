package com.example.toylanguage.View;


import com.example.toylanguage.Model.Expressions.*;
import com.example.toylanguage.Model.Statments.*;
import com.example.toylanguage.Model.Statments.Lock.lock;
import com.example.toylanguage.Model.Statments.Lock.newLock;
import com.example.toylanguage.Model.Statments.Lock.unlock;
import com.example.toylanguage.Model.Statments.RWStatments.closeRFile;
import com.example.toylanguage.Model.Statments.RWStatments.openRFile;
import com.example.toylanguage.Model.Statments.RWStatments.readFile;
import com.example.toylanguage.Model.Types.BoolType;
import com.example.toylanguage.Model.Types.IntType;
import com.example.toylanguage.Model.Types.RefType;
import com.example.toylanguage.Model.Types.StringType;
import com.example.toylanguage.Model.Values.BoolValue;
import com.example.toylanguage.Model.Values.IntValue;
import com.example.toylanguage.Model.Values.StringValue;
import com.example.toylanguage.Model.Values.Value;

public class Examples {
    public static IStmt[] exampleList() {
        //int v; v=2;Print(v)
        IStmt ex1= new CompStmt(new VarDeclStmt("v",new IntType()),
                new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));

        //int a;int b; a=2+3*5;b=a+1;Print(b)
        IStmt ex2 = new CompStmt( new VarDeclStmt("a",new IntType()),
                new CompStmt(new VarDeclStmt("b",new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp('+',new ValueExp(new IntValue(2)),new ArithExp('*',new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                new CompStmt(new AssignStmt("b",new ArithExp('+',new VarExp("a"), new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));


        //bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)
        IStmt ex3 = new CompStmt(new VarDeclStmt("a",new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                                        VarExp("v"))))));


        //bool v; v=false; bool c; c = false; bool d; d= v and c; print(d)
        IStmt ex4= new CompStmt(new VarDeclStmt("v",new BoolType()),
                new CompStmt(new AssignStmt("v",new ValueExp(new BoolValue(false))), new CompStmt(new VarDeclStmt("c",new BoolType()),
                        new CompStmt(new AssignStmt("c",new ValueExp(new BoolValue(true))),
                                new CompStmt(new VarDeclStmt("d",new BoolType()),
                                        new CompStmt(new AssignStmt("d",new LogicExp("or",new VarExp("c"),new VarExp("d"))),
                                                new PrintStmt(new VarExp("d"))
                                        ))))));

        // string varf;
        // varf="test.in";
        // openRFile(varf);
        // int varc;
        // readFile(varf,varc);print(varc);
        // closeRFile(varf)
        IStmt ex5= new CompStmt(new VarDeclStmt("varf",new StringType()),new CompStmt(new AssignStmt("varf",new ValueExp(new StringValue("test.in"))),
                new CompStmt(new openRFile(new VarExp("varf")),
                        new CompStmt(new CompStmt(new VarDeclStmt("varc",new IntType()),
                                new CompStmt(new readFile(new VarExp("varf"),"varc"),
                                        new PrintStmt(new VarExp("varc"))
                                )),
                                new closeRFile(new VarExp("varf")
                                ))
                )));


        //int v; v=4; (while (v>0) print(v);v=v-1);print(v)
        IStmt ex6= new CompStmt(new VarDeclStmt("v",new IntType()),
                new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(4))), new CompStmt(new WhileStmt(new RelExp(">", new VarExp("v"), new ValueExp(new IntValue(0))),
                        new CompStmt(new PrintStmt(new VarExp("v")),new AssignStmt("v",new ArithExp('-',new VarExp("v"), new ValueExp(new IntValue(1)) ) ))) ,new PrintStmt(new VarExp("v")))));

        //Ref int v;new(v,20);Ref Ref int a; new(a,v);print(v);print(a);
        IStmt ex7= new CompStmt(new VarDeclStmt("v",new RefType( new IntType())), new CompStmt( new NewStmt("v", new ValueExp(new IntValue(20))), new CompStmt(
                new VarDeclStmt("a",new RefType(new RefType(new IntType()))), new CompStmt(new NewStmt("a", new VarExp("v")),
                new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new VarExp("a")))
        ))));

        //Ref int v;new(v,20);Ref Ref int a; new(a,v);print(rH(v));print(rH(rH(a))+5)
        IStmt ex8= new CompStmt(new VarDeclStmt("v",new RefType( new IntType())), new CompStmt( new NewStmt("v", new ValueExp(new IntValue(20))), new CompStmt(
                new VarDeclStmt("a",new RefType(new RefType(new IntType()))), new CompStmt(new NewStmt("a", new VarExp("v")),
                new CompStmt(new PrintStmt(new RhExp(new VarExp("v"))), new PrintStmt(new ArithExp('+',new RhExp(new RhExp(new VarExp("a"))) ,new ValueExp(new IntValue(5)))))
        ))));

        //Ref int v;new(v,20);print(rH(v)); wH(v,30);print(rH(v)+5);
        IStmt ex9= new CompStmt(new VarDeclStmt("v",new RefType( new IntType())), new CompStmt( new NewStmt("v", new ValueExp(new IntValue(20))),
                new CompStmt(new PrintStmt(new RhExp(new VarExp("v"))),
                        new CompStmt(new WhStmt("v",new ValueExp(new IntValue(30))), new PrintStmt(new ArithExp('+',new RhExp(new VarExp("v")) ,new ValueExp(new IntValue(5)))))
                )));

        //Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30); new(v,40);print(rH(rH(a))); print(rH(rH(a)));
        IStmt ex10= new CompStmt(new VarDeclStmt("v",new RefType( new IntType())), new CompStmt( new NewStmt("v", new ValueExp(new IntValue(20))), new CompStmt(
                new VarDeclStmt("a",new RefType(new RefType(new IntType()))), new CompStmt(new NewStmt("a", new VarExp("v")),
                new CompStmt(new CompStmt(new NewStmt("v", new ValueExp(new IntValue(30))), new NewStmt("v", new ValueExp(new IntValue(40)))), new CompStmt(new PrintStmt(new RhExp(new RhExp(new VarExp("a")))) ,new PrintStmt(new RhExp(new RhExp(new VarExp("a")))))
                )))));


        //int v; Ref int a; v=10;new(a,22);
        //fork(wH(a,30);v=32;    print(v);print(rH(a)));
        //print(v);print(rH(a))
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

        IStmt ex12 = new CompStmt(new VarDeclStmt("v", new IntType()), new AssignStmt("v",new ValueExp(new StringValue("ad"))));


        // Ref int v1; Ref int v2; int x; int q;
        //new(v1,20);new(v2,30);newLock(x);
        //fork(
        //fork(
        //lock(x);wh(v1,rh(v1)-1);unlock(x)
        //);
        //lock(x);wh(v1,rh(v1)*10);unlock(x)
        //)
        IStmt ex13 = new CompStmt(new CompStmt(
                new VarDeclStmt("v1", new RefType(new IntType())),new VarDeclStmt("v2", new RefType(new IntType()))
        ),
                new CompStmt(new CompStmt( new VarDeclStmt("x", new IntType()),
                        new VarDeclStmt("q", new IntType())
                ), new CompStmt(
                        new CompStmt(new CompStmt(new NewStmt("v1",new ValueExp(new IntValue(20))), new NewStmt("v2",new ValueExp(new IntValue(30)))),
                                new ForkStmt(new CompStmt (new ForkStmt(new CompStmt(new CompStmt(new lock("x"), new WhStmt("v1",new ArithExp('-',new RhExp(new VarExp("v1")), new ValueExp(new IntValue(1))))),new unlock("x"))),
                                        new CompStmt(new CompStmt(new lock("x"), new WhStmt("v1",new ArithExp('*',new RhExp(new VarExp("v1")), new ValueExp(new IntValue(10))))),new unlock("x"))))
                                ),
                       new CompStmt(new CompStmt(
                               new CompStmt(new NopStmt(), new NopStmt()),
                               new CompStmt(new CompStmt(new lock("x"), new PrintStmt(new RhExp(new VarExp("v1")))), new unlock("x")
                       )),
                               new newLock("q")
                               )
                )
                        ));

        return new IStmt[]{ex1, ex2, ex3, ex4, ex5, ex6, ex7, ex8, ex9, ex10, ex11, ex12, ex13};
    }
}
