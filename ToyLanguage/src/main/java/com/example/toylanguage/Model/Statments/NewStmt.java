package com.example.toylanguage.Model.Statments;


import com.example.toylanguage.Exceptions.MyException;
import com.example.toylanguage.Model.ADT.Dictionary.MyIDictionary;
import com.example.toylanguage.Model.ADT.Heap.MyIHeap;
import com.example.toylanguage.Model.Expressions.Exp;
import com.example.toylanguage.Model.PrgState;
import com.example.toylanguage.Model.Types.RefType;
import com.example.toylanguage.Model.Types.Type;
import com.example.toylanguage.Model.Values.RefValue;
import com.example.toylanguage.Model.Values.Value;

import java.util.Objects;

public class NewStmt implements IStmt{

    String name;
    Exp exp;

    public NewStmt(String name, Exp e) {
        this.name = name;
        this.exp = e;
    }


    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();

        if(symTbl.isDefined(this.name)){
            Type typId = (symTbl.lookup(this.name)).getType();
            if(typId instanceof RefType){
                RefType refTypeId = (RefType) typId;
                Type innerType =  refTypeId.getInner();
                Value val = exp.eval(symTbl, state.getHeap());
                if(Objects.equals(val.getType(), innerType)){
                    int addr = heap.getFreeLocation();
                    heap.initialize(val);
                    symTbl.update(this.name, new RefValue(addr, innerType));
                }
                else throw new MyException("variable " + this.name +"is not of correct inner type\n");
            }
            else throw new MyException("variable " + this.name +" is not of type RefType\n");
        }
        else throw new MyException("the used variable" + this.name + " was not declared before\n");

        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new NewStmt(this.name, this.exp);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.lookup(name);
        Type typexp = exp.typecheck(typeEnv);
        if (typevar.equals(new RefType(typexp)))
            return typeEnv;
        else
            throw new MyException("NEW stmt: right hand side and left hand side have different types ");
    }

    @Override
    public String toString() {
        return "new("+this.name+","+this.exp+")";
    }
}
