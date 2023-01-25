package com.example.toylanguage.Model.Statments;

import com.example.toylanguage.Exceptions.MyException;
import com.example.toylanguage.Model.ADT.Barrier.ICyclicBarrier;
import com.example.toylanguage.Model.ADT.SymTable.MyIDictionary;
import com.example.toylanguage.Model.Expressions.RelExp;
import com.example.toylanguage.Model.Expressions.ValueExp;
import com.example.toylanguage.Model.PairBarrier.PairBarrier;
import com.example.toylanguage.Model.PrgState;
import com.example.toylanguage.Model.Types.BoolType;
import com.example.toylanguage.Model.Types.IntType;
import com.example.toylanguage.Model.Types.Type;
import com.example.toylanguage.Model.Values.BoolValue;
import com.example.toylanguage.Model.Values.IntValue;
import com.example.toylanguage.Model.Values.Value;

import java.util.ArrayList;
import java.util.List;

public class AwaitStmt implements IStmt
{
    private String variable;

    public AwaitStmt(String v)
    {
        variable = v;
    }




    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        ICyclicBarrier<IntValue, PairBarrier<IntValue, List<Integer>>> barrierTable = state.getBarrier();

        if(symTbl.isDefined(variable)){
            Value foundIndex = symTbl.lookup(variable);
            Type intType = new IntType();
            if(intType.equals(foundIndex.getType())){
                IntValue foundIndexInt = (IntValue) foundIndex;
                if(barrierTable.contains(foundIndexInt)){
                    PairBarrier<IntValue, List<Integer>> pair = barrierTable.get(foundIndexInt);
                    int NLInt = pair.getSecond().size();
                    IntValue NL = new IntValue(NLInt);
                    IntValue N1 = pair.getFirst();
                    Value v = new RelExp(">",new ValueExp(N1), new ValueExp(NL)).eval(symTbl, state.getHeap());

                    Type bool = new BoolType();
                    if(bool.equals(v.getType())){
                        if(pair.getSecond().contains(state.currentId)){
                            return null;
                        }
                        else{
                            pair.getSecond().add(state.currentId);
                        }
                    }
                    else throw new MyException("Not bool");
                }
                else throw new MyException("Found Index is not in barrierTable");
            }
            else throw new MyException("Found index is not an integer");
        }
        else throw new MyException("Variable not defined");


        return null;
    }

    @Override
    public String toString()
    {
        return "Await("+variable+")";
    }

    @Override
    public IStmt deepCopy() {
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }
}
