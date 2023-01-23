package com.example.toylanguage.Model.Expressions;

import com.example.toylanguage.Exceptions.MyException;
import com.example.toylanguage.Model.ADT.Heap.MyIHeap;
import com.example.toylanguage.Model.ADT.SymTable.MyIDictionary;
import com.example.toylanguage.Model.Types.BoolType;
import com.example.toylanguage.Model.Types.Type;
import com.example.toylanguage.Model.Values.BoolValue;
import com.example.toylanguage.Model.Values.Value;

public class NotExp implements Exp{
    Exp exp;

    public NotExp(Exp e){
        this.exp = e;
    }
    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer,Value> hp) throws MyException {
        Value v = this.exp.eval(tbl, hp);
        BoolType boolType = new BoolType();

        if(boolType.equals(v.getType())) {
            BoolValue b = (BoolValue) v;
            return new BoolValue(! b.getVal());
        }
        else throw new MyException("Not boolean types in logical expression.");
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typ;
        typ=exp.typecheck(typeEnv);
        if(typ.equals(new BoolType())){
                return new BoolType();
        }
        else throw new MyException("Operand is not a bool");
    }

    @Override
    public String toString() {
        return "Not(" +
                exp+
                ")"
                ;
    }
}

