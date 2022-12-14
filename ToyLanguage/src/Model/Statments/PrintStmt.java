package Model.Statments;

import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.List.MyIList;
import Model.Expressions.Exp;
import Model.PrgState;
import Exceptions.MyException;
import Model.Types.Type;
import Model.Values.Value;


public class PrintStmt implements IStmt {
    Exp exp;

    public PrintStmt(Exp exp){
        this.exp = exp;
    }
    public String toString(){ return "print(" +exp.toString()+")";}

    public PrgState execute(PrgState state) throws MyException{
        MyIList<Value> out = state.getIList();
        MyIDictionary<String,Value> symTable = state.getSymTable();
        Value v = this.exp.eval(symTable, state.getHeap());
        out.add(v);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new PrintStmt(this.exp);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        exp.typecheck(typeEnv);
        return typeEnv;
    }
}
