package Model;


import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.List.MyIList;
import Model.ADT.Stack.MyIStack;
import Model.Statments.IStmt;
import Model.Values.Value;

public class PrgState {
    MyIStack<IStmt> exeStack;
    MyIDictionary<String, Value> symTable;
    MyIList<Value> out;
    IStmt originalProgram; //optional field, but good to have
    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String,Value> sTable, MyIList<Value> ot, IStmt prg){
        exeStack=stk;
        symTable=sTable;
        out = ot;
        originalProgram=prg.deepCopy();//recreate the entire original prg
        stk.push(prg);
    }
    public MyIStack<IStmt> getStk(){
        return this.exeStack;
    }

    public MyIDictionary<String, Value> getSymTable() {return this.symTable; }

}
