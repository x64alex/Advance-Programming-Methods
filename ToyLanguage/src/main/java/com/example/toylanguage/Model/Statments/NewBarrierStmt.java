package com.example.toylanguage.Model.Statments;

import com.example.toylanguage.Exceptions.MyException;
import com.example.toylanguage.Model.ADT.Barrier.ICyclicBarrier;
import com.example.toylanguage.Model.ADT.SymTable.MyIDictionary;
import com.example.toylanguage.Model.Expressions.Exp;
import com.example.toylanguage.Model.PairBarrier.PairBarrier;
import com.example.toylanguage.Model.PrgState;
import com.example.toylanguage.Model.Types.BoolType;
import com.example.toylanguage.Model.Types.IntType;
import com.example.toylanguage.Model.Types.Type;
import com.example.toylanguage.Model.Values.IntValue;
import com.example.toylanguage.Model.Values.Value;

import java.util.ArrayList;
import java.util.List;


public class NewBarrierStmt implements IStmt
{
    private String variable;
    private Exp exp;
    static private Integer nextFree = 0;

    public NewBarrierStmt(String v, Exp e)
    {
        variable = v;
        exp = e;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException
    {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        IntValue nextFreeInt = new IntValue(nextFree);

        Value expression = this.exp.eval(symTbl, state.getHeap());
        ICyclicBarrier<IntValue, PairBarrier<IntValue, List<Integer>>> barrierTable = state.getBarrier();

        Type integer = new IntType();
        if(integer.equals(expression.getType())){
            Value nr = (IntValue) integer;
            barrierTable.add(nextFreeInt, new PairBarrier(nr, new ArrayList<Integer>()));

            if(symTbl.isDefined(variable)){
                symTbl.update(variable, nextFreeInt);
            }else {
                symTbl.initialize(variable, nextFreeInt);
            }
        }
        else{ throw new MyException("Exp in NewBarrierStmt is not of Int type");}
        nextFree++;
        return null;
    }

    @Override
    public String toString() {
        return "NewBarrierStmt(" + variable + ", "+ exp + ")";
    }

    @Override
    public IStmt deepCopy() {
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typexp=exp.typecheck(typeEnv);
        if (typexp.equals(new IntType())) {
            return typeEnv;
        }
        else
            throw new MyException("The condition of while has not the type int");
    }
}
