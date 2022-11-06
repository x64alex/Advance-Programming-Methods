package Model;

import Model.Statments.IStmt;

public class PrgState {
    MyIStack<IStmt> exeStack; MyIDictionary<String, Value> symTable;
    MyIList<Value> out;
    IStmt originalProgram; //optional field, but good to have
    PrgState(MyIStack<IStmt> stk, MyIDictionary<String,Value> symtbl,
             ot, Istmt prg){
        exeStack=stk;
        MyIList<Value>
                symTable=symtbl;
        out = ot;
        originalProgram=deepCopy(prg);//recreate the entire original prg
        stk.push(prg);
    }
}
