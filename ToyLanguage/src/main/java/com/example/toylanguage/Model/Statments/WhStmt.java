package com.example.toylanguage.Model.Statments;


import com.example.toylanguage.Exceptions.MyException;
import com.example.toylanguage.Model.ADT.Dictionary.MyIDictionary;
import com.example.toylanguage.Model.ADT.Heap.MyIHeap;
import com.example.toylanguage.Model.ADT.Stack.MyIStack;
import com.example.toylanguage.Model.Expressions.Exp;
import com.example.toylanguage.Model.PrgState;
import com.example.toylanguage.Model.Types.RefType;
import com.example.toylanguage.Model.Types.Type;
import com.example.toylanguage.Model.Values.RefValue;
import com.example.toylanguage.Model.Values.Value;
import com.example.toylanguage.Model.Types.*;


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
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.lookup(name);
        Type typexp = e.typecheck(typeEnv);
        if (typevar.equals(new RefType(typexp)))
            return typeEnv;
        else
            throw new MyException("Wh stmt: right hand side and left hand side have different types ");
    }

    @Override
    public String toString() {
        return "WriteHeap{"+name +"="+ e+"}";
    }
}
