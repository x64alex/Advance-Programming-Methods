package Model.Statments;

import Exceptions.MyException;
import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.Heap.MyIHeap;
import Model.ADT.Stack.MyIStack;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Types.BoolType;
import Model.Types.RefType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.RefValue;
import Model.Values.Value;

public class WhStmt implements IStmt{

    String name;
    Exp e;

    public WhStmt(String name, Exp e) {
        this.name = name;
        this.e = e;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIStack<IStmt> stk = state.getStk();
        MyIHeap<Integer, Value> heap = state.getHeap();

        if(symTbl.isDefined(this.name)){
            Value v = symTbl.lookup(this.name);
            if(v.getType() instanceof RefType){
                RefValue val = (RefValue) v;
                RefType typ = (RefType) val.getType();
                if(heap.isDefined(val.getAddr())){
                    Value expValue = e.eval(symTbl, heap);
                    if(expValue.getType().equals(typ.getInner())){
                        heap.update(val.getAddr(),expValue);
                    }
                    else throw new MyException("Trying to write different type of expression");
                }
                else throw new MyException("Variable not existent on heap");
            }
            else throw new MyException("Variable is not of type RefType");
        }
        else throw new MyException("Variable not defined in symTable");
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new WhStmt(this.name,this.e);
    }

    @Override
    public String toString() {
        return "WriteHeap{"+name +"="+ e+"}";
    }
}
