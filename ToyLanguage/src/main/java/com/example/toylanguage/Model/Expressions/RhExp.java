package com.example.toylanguage.Model.Expressions;


import com.example.toylanguage.Exceptions.MyException;
import com.example.toylanguage.Model.ADT.SymTable.MyIDictionary;
import com.example.toylanguage.Model.ADT.Heap.MyIHeap;
import com.example.toylanguage.Model.Types.RefType;
import com.example.toylanguage.Model.Types.Type;
import com.example.toylanguage.Model.Values.RefValue;
import com.example.toylanguage.Model.Values.Value;


public class RhExp implements Exp{

    Exp e;

    public RhExp(Exp e) {
        this.e = e;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer,Value> hp) throws MyException {
        Value v = this.e.eval(tbl, hp);

        //System.out.println(v.getType());
        if(v.getType() instanceof RefType){
            RefValue val = (RefValue) v;
            if(hp.isDefined(val.getAddr())){
                return hp.lookup(val.getAddr());
            }
            else throw new MyException("Address not a key in the heap\n");
        }
        else throw new MyException("Expression doesn't evaluates to a RefValue\n");
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typ=e.typecheck(typeEnv);
        if (typ instanceof RefType) {
            RefType reft =(RefType) typ;
            return reft.getInner();
        } else
            throw new MyException("the rH argument is not a Ref Type");
    }

    @Override
    public String toString() {
        return "RhExp{" +
                "e=" + e.toString() +
                '}';
    }
}
