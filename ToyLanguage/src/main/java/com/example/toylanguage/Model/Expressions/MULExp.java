package com.example.toylanguage.Model.Expressions;

import com.example.toylanguage.Exceptions.MyException;
import com.example.toylanguage.Model.ADT.Heap.MyIHeap;
import com.example.toylanguage.Model.ADT.SymTable.MyIDictionary;
import com.example.toylanguage.Model.Types.IntType;
import com.example.toylanguage.Model.Types.Type;
import com.example.toylanguage.Model.Values.IntValue;
import com.example.toylanguage.Model.Values.Value;

public class MULExp implements Exp{
    private Exp e1;
    private Exp e2;


    public MULExp(Exp e1, Exp e2){
        this.e1 = e1;
        this.e2 = e2;
    }

    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer,Value> hp) throws MyException {
        Value v1,v2;
        v1= e1.eval(tbl, hp);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(tbl, hp);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue)v1;
                IntValue i2 = (IntValue)v2;
                int n1,n2;
                n1= i1.getVal();
                n2 = i2.getVal();
                return new IntValue((n1*n2)-(n1+n2));
            }else
                throw new MyException("second operand is not an integer");
        }else
            throw new MyException("first operand is not an integer");
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typ1, typ2;
        typ1=e1.typecheck(typeEnv);
        typ2=e2.typecheck(typeEnv);
        if(typ1.equals(new IntType())){
            if(typ2.equals(new IntType())){
                return new IntType();
            }
            else throw new MyException("second operand is not an integer");
        }
        else throw new MyException("first operand is not an integer");
    }

    @Override
    public String toString() {
        return "MULExp{" +
                "e1=" + e1 +
                ", e2=" + e2 +
                '}';
    }
}
