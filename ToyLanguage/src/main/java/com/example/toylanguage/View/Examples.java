package com.example.toylanguage.View;


import com.example.toylanguage.Model.Expressions.*;
import com.example.toylanguage.Model.Statments.*;
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


        //v=0;
        //(for(v=0;v<3;v=v+1) fork(print(v);v=v+1) );
        //print(v*10)
        IStmt ex13 = new CompStmt(new  VarDeclStmt("v", new IntType()),new CompStmt(
                new ForStmt("v",new ValueExp(new IntValue(0)),new ValueExp(new IntValue(3)),new ArithExp('+', new VarExp("v"), new ValueExp(new IntValue(1))),
                        new ForkStmt(new CompStmt(new PrintStmt(new VarExp("v")),new AssignStmt("v", new ArithExp('+', new VarExp("v"), new ValueExp(new IntValue(1))))))
                        ),
                new PrintStmt(new ArithExp('*',new VarExp("v"), new ValueExp(new IntValue(10))))
        ));

        //v=20; wait(10);print(v*10)
        IStmt ex14 = new CompStmt(new VarDeclStmt("v",new IntType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(20))),
                new CompStmt(new WaitStmt(new IntValue(10)), new PrintStmt(new ArithExp('*', new VarExp("v"), new ValueExp(new IntValue(10)))))
                )
        );

        //v=0;
        //(while(v<3) (fork(print(v);v=v+1);v=v+1);
        //sleep(5);
        //print(v*10)
        IStmt ex15 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(
                new WhileStmt(new RelExp("<",new VarExp("v"), new ValueExp(new IntValue(3))), new CompStmt(new ForkStmt(new CompStmt(
                        new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp('+', new VarExp("v"), new ValueExp(new IntValue(1))))
                )), new AssignStmt("v", new ArithExp('+', new VarExp("v"), new ValueExp(new IntValue(1))))
                        )),
                new PrintStmt(new ArithExp('*',new VarExp("v"), new ValueExp(new IntValue(10))))
        ));

        //int a; int b; int c;
        //a=1;b=2;c=5;
        //(switch(a*10)
        //(case (b*c) : print(a);print(b))
        //(case (10) : print(100);print(200))
        //(default : print(300)));
        //print(300)
        IStmt ex16 = new CompStmt(new CompStmt(new CompStmt(new CompStmt(new VarDeclStmt("a", new IntType()), new VarDeclStmt("b", new IntType())),
                new VarDeclStmt("c", new IntType())),
                new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(1))), new AssignStmt("b", new ValueExp(new IntValue(2))))), new CompStmt(
                        new AssignStmt("c", new ValueExp(new IntValue(5))),
                new CompStmt(new SwitchStmt(new ArithExp('*', new VarExp("a"), new ValueExp(new IntValue(10))),
                        new ArithExp('*', new VarExp("b"), new VarExp("c")),
                        new ValueExp(new IntValue(10)),
                        new CompStmt(new PrintStmt(new VarExp("a")), new PrintStmt(new VarExp("b"))),
                        new CompStmt(new PrintStmt(new ValueExp(new IntValue(100))), new PrintStmt(new ValueExp(new IntValue(200)))),
                        new PrintStmt(new ValueExp(new IntValue(300)))
                        )
                        ,new PrintStmt(new ValueExp(new IntValue(300))))
        ));

        //v=0;
        //(repeat (fork(print(v);v=v-1);v=v+1) until v==3);
        //print(v*10)
        IStmt ex17 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(
                new RepeatUntilStmt(new RelExp("==", new VarExp("v"), new ValueExp(new IntValue(3))),
                        new CompStmt(new ForkStmt(
                                new CompStmt(
                                        new PrintStmt(new VarExp("v")),
                                        new AssignStmt("v", new ArithExp('-', new VarExp("v"), new ValueExp(new IntValue(1))))
                                )
                        ),
                                new AssignStmt("v", new ArithExp('+', new VarExp("v"), new ValueExp(new IntValue(1))))
                                )
                        ),
                new PrintStmt(new ArithExp('*', new VarExp("v"), new ValueExp(new IntValue(10))))
        ));

        //bool b;
        //int c;
        //b=true;
        //c=b?100:200;
        //print(c);
        //c= (false)?100:200;
        //print(c);
        IStmt ex18 = new CompStmt(
                new CompStmt(new VarDeclStmt("b", new BoolType()),new VarDeclStmt("c", new IntType())),
                new CompStmt(new AssignStmt("b", new ValueExp(new BoolValue(true))), new CompStmt(
                        new CompStmt(new ConditionalStmt("c", new VarExp("b"), new ValueExp(new IntValue(100)), new ValueExp(new IntValue(200))),new PrintStmt(new VarExp("c"))),
                        new CompStmt(new ConditionalStmt("c", new ValueExp(new BoolValue(false)), new ValueExp(new IntValue(100)), new ValueExp(new IntValue(200))),new PrintStmt(new VarExp("c")))
                ))
        );

        //Ref int a; Ref int b; int v;
        //new(a,0); new(b,0);
        //wh(a,1); wh(b,2);
        //v=(rh(a)<rh(b))?100:200;
        //print(v);
        //v= ((rh(b)-2)>rh(a))?100:200;
        //print(v);
        IStmt ex19 = new CompStmt(
                new CompStmt(new CompStmt(new VarDeclStmt("a", new RefType(new IntType())), new VarDeclStmt("b", new RefType(new IntType()))),
                        new VarDeclStmt("v", new IntType())
                        ),
                new CompStmt( new CompStmt(
                        new CompStmt(new NewStmt("a", new ValueExp(new IntValue(0))),
                                new NewStmt("b", new ValueExp(new IntValue(0)))
                        ),
                        new CompStmt(new WhStmt("a", new ValueExp(new IntValue(1))), new WhStmt("b", new ValueExp(new IntValue(2))))
                ),
                        new CompStmt(
                        new CompStmt(new ConditionalStmt("v",new RelExp("<",new RhExp(new VarExp("a")), new RhExp(new VarExp("b"))), new ValueExp(new IntValue(100)), new ValueExp(new IntValue(200))),new PrintStmt(new VarExp("v"))),
                        new CompStmt(new ConditionalStmt("v",new RelExp(">",new ArithExp('-',new RhExp(new VarExp("a")), new ValueExp(new IntValue(2))), new RhExp(new VarExp("a"))), new ValueExp(new IntValue(100)), new ValueExp(new IntValue(200))),new PrintStmt(new VarExp("v")))
                )
                )
        );

        // int v1;int v2;
        // v1=2;v2=3;
        // print(MUL(v1,v2))
        IStmt ex20 = new CompStmt(new CompStmt(
                new CompStmt(new VarDeclStmt("v1", new IntType()), new VarDeclStmt("v2", new IntType())),
                new CompStmt(new AssignStmt("v1", new ValueExp(new IntValue(2))), new AssignStmt("v2", new ValueExp(new IntValue(3))))
        ),
                new PrintStmt(new MULExp(new VarExp("v1"), new VarExp("v2")))
                );

        //int v; int x; int y; v=0;
        //(repeat (fork(print(v);v=v-1);v=v+1) until v==3);
        //x=1;nop;y=3;nop;
        //print(v*10)
        //
        //The final Out should be {0,1,2,30}
        IStmt ex21 = new CompStmt(new CompStmt(new CompStmt(new VarDeclStmt("v", new IntType()), new VarDeclStmt("x", new IntType())),
                new CompStmt(new VarDeclStmt("y", new IntType()), new AssignStmt("v",new ValueExp(new IntValue(0))))),
                new CompStmt(new RepeatUntilStmt(new RelExp("==", new VarExp("v"), new ValueExp(new IntValue(3))),
                        new CompStmt(new ForkStmt(
                                new CompStmt(
                                        new PrintStmt(new VarExp("v")),
                                        new AssignStmt("v", new ArithExp('-', new VarExp("v"), new ValueExp(new IntValue(1))))
                                )
                        ),
                                new AssignStmt("v", new ArithExp('+', new VarExp("v"), new ValueExp(new IntValue(1))))
                        )
                ),new CompStmt(
                        new CompStmt(new AssignStmt("x", new ValueExp(new IntValue(1))), new NopStmt()),
                        new CompStmt(new CompStmt(new AssignStmt("y", new ValueExp(new IntValue(3))), new NopStmt()),
                                new PrintStmt(new ArithExp('*', new VarExp("v"), new ValueExp(new IntValue(10))))
                                )
                )
                )

                );

        IStmt ex22 = new CompStmt(new CompStmt(new CompStmt(new VarDeclStmt("v", new IntType()), new VarDeclStmt("x", new IntType())),
                new CompStmt(new VarDeclStmt("y", new IntType()), new AssignStmt("v",new ValueExp(new IntValue(0))))),
                new CompStmt(new RepeatUntilStmt(new ArithExp('+', new VarExp("v"), new ValueExp(new IntValue(3))),
                        new CompStmt(new ForkStmt(
                                new CompStmt(
                                        new PrintStmt(new VarExp("v")),
                                        new AssignStmt("v", new ArithExp('-', new VarExp("v"), new ValueExp(new IntValue(1))))
                                )
                        ),
                                new AssignStmt("v", new ArithExp('+', new VarExp("v"), new ValueExp(new IntValue(1))))
                        )
                ),new CompStmt(
                        new CompStmt(new AssignStmt("x", new ValueExp(new IntValue(1))), new NopStmt()),
                        new CompStmt(new CompStmt(new AssignStmt("y", new ValueExp(new IntValue(3))), new NopStmt()),
                                new PrintStmt(new ArithExp('*', new VarExp("v"), new ValueExp(new IntValue(10))))
                        )
                )
                )

        );

        return new IStmt[]{ex1, ex2, ex3, ex4, ex5, ex6, ex7, ex8, ex9, ex10, ex11, ex12, ex13, ex14,ex15, ex16, ex17, ex18, ex19, ex20,ex21,ex22};
    }
}
